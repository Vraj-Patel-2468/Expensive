package com.expensive.api.services;

import com.expensive.api.dto.GroupDto;
import com.expensive.api.dto.UserDto;
import com.expensive.api.entities.Group;
import com.expensive.api.entities.User;
import com.expensive.api.repositories.GroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, ModelMapper modelMapper) {
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }

    public GroupDto getGroupById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if(group.isEmpty()) {
            throw new RuntimeException("Group with given Id does not exist.");
        }
        return modelMapper.map(group.get(), GroupDto.class);
    }

    public List<GroupDto> getAllGroups() {
        List<GroupDto> groups = groupRepository.findAll()
                .stream()
                .map(group -> modelMapper.map(group, GroupDto.class))
                .toList();
        return groups;
    }

    public GroupDto createGroup(GroupDto groupDto) {
        Group groupToSave = modelMapper.map(groupDto, Group.class);
        groupToSave.setId(null);
        Group saved = groupRepository.save(groupToSave);
        if(saved == null)
            throw new RuntimeException("Not created due to some error.");
        return modelMapper.map(saved, GroupDto.class);
    }

    public GroupDto updateGroup(long id, GroupDto groupDto) {
        Optional<Group> group = groupRepository.findById(id);
        if(group.isEmpty()) {
            throw new RuntimeException("The Group does not exist.");
        }

        Group groupToUpdate = modelMapper.map(groupDto, Group.class);

        Group updatedGroup = groupRepository.save(groupToUpdate);
        return modelMapper.map(updatedGroup, GroupDto.class);
    }

    public GroupDto deleteUser(long id) {
        Optional<Group> group = groupRepository.findById(id);
        if(group.isEmpty()) {
            throw new RuntimeException("The Group does not exist.");
        }
        Group groupToDelete= group.get();
        groupRepository.delete(groupToDelete);
        return modelMapper.map(groupToDelete, GroupDto.class);
    }
}
