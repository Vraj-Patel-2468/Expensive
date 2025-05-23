package com.expensive.api.configs;

import com.expensive.api.dto.UserDto;
import com.expensive.api.entities.*;
import com.expensive.api.repositories.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.internal.TypeMapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MapperConfig {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;
    private final SettlementRepository settlementRepository;

    @Autowired
    public MapperConfig(UserRepository userRepository,
                        GroupRepository groupRepository,
                        ExpenseRepository expenseRepository,
                        ExpenseSplitRepository expenseSplitRepository,
                        SettlementRepository settlementRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.expenseSplitRepository = expenseSplitRepository;
        this.settlementRepository = settlementRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<User, UserDto> userUserDtoTypeMap = modelMapper.createTypeMap(User.class, UserDto.class);

        userUserDtoTypeMap.addMappings(mapper -> {
            mapper.map(User::getId, UserDto::setId);
            mapper.map(User::getUsername, UserDto::setUsername);
            mapper.map(User::getEmail, UserDto::setEmail);
            mapper.map(User::getPassword, UserDto::setPassword);
            mapper.map(User::isEnabled, UserDto::setEnabled);
            mapper.map(User::getCreatedAt, UserDto::setCreatedAt);
            mapper.map(User::getUpdatedAt, UserDto::setUpdatedAt);

            mapper.using(
                    src -> ((Set<Group>) src.getSource())
                            .stream()
                            .map(group -> group.getId())
                            .collect(Collectors.toSet())
            ).map(User::getGroupsCreated, UserDto::setGroupsCreated);
            mapper.using(
                    src -> ((Set<Group>) src.getSource())
                            .stream()
                            .map(group -> group.getId())
                            .collect(Collectors.toSet())
            ).map(User::getGroupsJoined, UserDto::setGroupsJoined);
            mapper.using(
                    src -> ((Set<Expense>) src.getSource())
                            .stream()
                            .map(expense -> expense.getId())
                            .collect(Collectors.toSet())
            ).map(User::getExpensesPaid, UserDto::setExpensesPaid);
            mapper.using(
                    src -> ((Set<ExpenseSplit>) src.getSource())
                            .stream()
                            .map(expenseSplit -> expenseSplit.getId())
                            .collect(Collectors.toSet())
            ).map(User::getSplitsToPay, UserDto::setSplitsToPay);
            mapper.using(
                    src -> ((Set<ExpenseSplit>) src.getSource())
                            .stream()
                            .map(expenseSplit -> expenseSplit.getId())
                            .collect(Collectors.toSet())
            ).map(User::getSplitsToCollect, UserDto::setSplitsToCollect);
            mapper.using(
                    src -> ((Set<Settlement>) src.getSource())
                            .stream()
                            .map(settlement -> settlement.getId())
                            .collect(Collectors.toSet())
            ).map(User::getSettlementsToCollect, UserDto::setSettlementsToCollect);
            mapper.using(
                    src -> ((Set<Settlement>) src.getSource())
                            .stream()
                            .map(settlement -> settlement.getId())
                            .collect(Collectors.toSet())
            ).map(User::getSettlementsToPay, UserDto::setSettlementsToPay);
        });

        TypeMap<UserDto, User> userDtoUserTypeMap = modelMapper.createTypeMap(UserDto.class, User.class);

        userDtoUserTypeMap.addMappings(mapper -> {
            mapper.map(UserDto::getId, User::setId);
            mapper.map(UserDto::getUsername, User::setUsername);
            mapper.map(UserDto::getEmail, User::setEmail);
            mapper.map(UserDto::getPassword, User::setPassword);
            mapper.map(UserDto::isEnabled, User::setEnabled);
            mapper.map(UserDto::getCreatedAt, User::setCreatedAt);
            mapper.map(UserDto::getUpdatedAt, User::setUpdatedAt);

            mapper.using(
                src -> ((Set<Long>) src.getSource())
                        .stream()
                        .map(groupId -> groupRepository.findById(groupId).orElse(null))
                        .collect(Collectors.toSet())
            ).map(UserDto::getGroupsCreated, User::setGroupsCreated);
            mapper.using(
                src -> ((Set<Long>) src.getSource())
                        .stream()
                        .map(groupId -> groupRepository.findById(groupId).orElse(null))
                        .collect(Collectors.toSet())
            ).map(UserDto::getGroupsJoined, User::setGroupsJoined);
            mapper.using(
                src -> ((Set<Long>) src.getSource())
                        .stream()
                        .map(expenseId -> expenseRepository.findById(expenseId).orElse(null))
                        .collect(Collectors.toSet())
            ).map(UserDto::getExpensesPaid, User::setExpensesPaid);
            mapper.using(
                src -> ((Set<Long>) src.getSource())
                        .stream()
                        .map(expenseSplitId -> expenseSplitRepository.findById(expenseSplitId).orElse(null))
                        .collect(Collectors.toSet())
            ).map(UserDto::getSplitsToPay, User::setSplitsToPay);
            mapper.using(
                src -> ((Set<Long>) src.getSource())
                        .stream()
                        .map(expenseSplitId -> expenseSplitRepository.findById(expenseSplitId).orElse(null))
                        .collect(Collectors.toSet())
            ).map(UserDto::getSplitsToCollect, User::setSplitsToCollect);
            mapper.using(
                src -> ((Set<Long>) src.getSource())
                        .stream()
                        .map(settlementId -> settlementRepository.findById(settlementId).orElse(null))
                        .collect(Collectors.toSet())
            ).map(UserDto::getSettlementsToCollect, User::setSettlementsToCollect);
            mapper.using(
                src -> ((Set<Long>) src.getSource())
                        .stream()
                        .map(settlementId -> settlementRepository.findById(settlementId).orElse(null))
                        .collect(Collectors.toSet())
            ).map(UserDto::getSettlementsToPay, User::setSettlementsToPay);
        });

        return modelMapper;
    }
}
