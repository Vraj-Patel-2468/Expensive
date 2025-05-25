package com.expensive.api.controllers;

import com.expensive.api.dto.GroupDto;
import com.expensive.api.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAllGroups(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GroupDto> getAllGroups(@PathVariable long id) {
        return new ResponseEntity<>(groupService.getGroupById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.createGroup(groupDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable long id, @RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.updateGroup(id, groupDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable long id) {
        GroupDto groupDto = groupService.deleteUser(id);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }
}
