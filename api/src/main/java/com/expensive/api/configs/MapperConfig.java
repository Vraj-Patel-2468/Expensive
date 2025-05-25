package com.expensive.api.configs;

import com.expensive.api.dto.*;
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

        TypeMap<Group, GroupDto> groupGroupDtoTypeMap = modelMapper.createTypeMap(Group.class, GroupDto.class);

        groupGroupDtoTypeMap.addMappings(mapper -> {
           mapper.map(Group::getId, GroupDto::setId);
           mapper.map(Group::getName, GroupDto::setName);
           mapper.map(Group::getCreatedAt, GroupDto::setCreatedAt);
           mapper.using(
                   src -> ((User) src.getSource())
                           .getId()
           ).map(Group::getCreatedBy, GroupDto::setCreatedBy);
           mapper.using(
                   src -> ((Set<User>) src.getSource())
                           .stream()
                           .map(user -> user.getId())
                           .collect(Collectors.toSet())
           ).map(Group::getMembers, GroupDto::setMembers);
           mapper.using(
                   src -> ((Set<Expense>) src.getSource())
                           .stream()
                           .map(expense -> expense.getId())
                           .collect(Collectors.toSet())
           ).map(Group::getExpenses, GroupDto::setExpenses);
        });

        TypeMap<GroupDto, Group> groupDtoGroupTypeMap = modelMapper.createTypeMap(GroupDto.class, Group.class);

        groupDtoGroupTypeMap.addMappings(mapper -> {
            mapper.map(GroupDto::getId, Group::setId);
            mapper.map(GroupDto::getName, Group::setName);
            mapper.map(GroupDto::getCreatedAt, Group::setCreatedAt);
            mapper.using(
                    src -> {
                        long id = ((Long) src.getSource());
                        return userRepository.findById(id).orElse(null);
                    }
            ).map(GroupDto::getCreatedBy, Group::setCreatedBy);
            mapper.using(
                    src -> ((Set<Long>) src.getSource())
                            .stream()
                            .map(userId -> userRepository.findById(userId).orElse(null))
                            .collect(Collectors.toSet())
            ).map(GroupDto::getMembers, Group::setMembers);
            mapper.using(
                    src -> ((Set<Long>) src.getSource())
                            .stream()
                            .map(expenseId -> expenseRepository.findById(expenseId).orElse(null))
                            .collect(Collectors.toSet())
            ).map(GroupDto::getExpenses, Group::setExpenses);
        });

        TypeMap<Expense, ExpenseDto> expenseExpenseDtoTypeMap = modelMapper.createTypeMap(Expense.class, ExpenseDto.class);

        expenseExpenseDtoTypeMap.addMappings(mapper -> {
            mapper.map(Expense::getId, ExpenseDto::setId);
            mapper.map(Expense::getTitle, ExpenseDto::setTitle);
            mapper.map(Expense::getDescription, ExpenseDto::setDescription);
            mapper.map(Expense::getAmount, ExpenseDto::setAmount);
            mapper.map(Expense::getCreatedAt, ExpenseDto::setCreatedAt);
            mapper.using(
                    src -> ((User) src.getSource()).getId()
            ).map(Expense::getPaidBy, ExpenseDto::setPaidById);
            mapper.using(
                    src -> ((Group) src.getSource()).getId()
            ).map(Expense::getGroup, ExpenseDto::setGroupId);
            mapper.using(
                    src -> ((Set<ExpenseSplit>) src.getSource())
                            .stream()
                            .map(expenseSplit -> expenseSplit.getId())
                            .collect(Collectors.toSet())
            ).map(Expense::getSplits, ExpenseDto::setSplits);
        });

        TypeMap<ExpenseDto, Expense> expenseDtoExpenseTypeMap = modelMapper.createTypeMap(ExpenseDto.class, Expense.class);

        expenseDtoExpenseTypeMap.addMappings(mapper -> {
            mapper.map(ExpenseDto::getId, Expense::setId);
            mapper.map(ExpenseDto::getTitle, Expense::setTitle);
            mapper.map(ExpenseDto::getDescription, Expense::setDescription);
            mapper.map(ExpenseDto::getAmount, Expense::setAmount);
            mapper.map(ExpenseDto::getCreatedAt, Expense::setCreatedAt);
            mapper.using(
                    src -> {
                        long id = (Long) src.getSource();
                        return userRepository.findById(id).orElse(null);
                    }
            ).map(ExpenseDto::getPaidById, Expense::setPaidBy);
            mapper.using(
                    src -> {
                        long id = (Long) src.getSource();
                        return groupRepository.findById(id).orElse(null);
                    }
            ).map(ExpenseDto::getGroupId, Expense::setGroup);
            mapper.using(
                    src -> ((Set<Long>) src.getSource())
                            .stream()
                            .map(expenseSplitId -> expenseSplitRepository.findById(expenseSplitId).orElse(null))
                            .collect(Collectors.toSet())
            ).map(ExpenseDto::getSplits, Expense::setSplits);
        });

        TypeMap<ExpenseSplit, ExpenseSplitDto> expenseSplitExpenseSplitDtoTypeMap = modelMapper.createTypeMap(ExpenseSplit.class, ExpenseSplitDto.class);
        expenseSplitExpenseSplitDtoTypeMap.addMappings(mapper -> {
            mapper.map(ExpenseSplit::getId, ExpenseSplitDto::setId);
            mapper.using(
                    src -> ((Expense) src.getSource()).getId()
            ).map(ExpenseSplit::getExpense, ExpenseSplitDto::setExpenseId);
            mapper.map(ExpenseSplit::getAmount, ExpenseSplitDto::setAmount);
            mapper.using(
                    src -> ((User) src.getSource()).getId()
            ).map(ExpenseSplit::getPayee, ExpenseSplitDto::setPayeeId);
            mapper.using(
                    src -> ((User) src.getSource()).getId()
            ).map(ExpenseSplit::getPayer, ExpenseSplitDto::setPayerId);
            mapper.map(ExpenseSplit::isSettled, ExpenseSplitDto::setSettled);
        });

        TypeMap<ExpenseSplitDto, ExpenseSplit> expenseSplitDtoExpenseSplitTypeMap = modelMapper.createTypeMap(ExpenseSplitDto.class, ExpenseSplit.class);
        expenseSplitDtoExpenseSplitTypeMap.addMappings(mapper -> {
            mapper.map(ExpenseSplitDto::getId, ExpenseSplit::setId);
            mapper.using(
                    src -> {
                        long id = (Long) src.getSource();
                        return expenseRepository.findById(id).orElse(null);
                    }
            ).map(ExpenseSplitDto::getExpenseId, ExpenseSplit::setExpense);
            mapper.map(ExpenseSplitDto::getAmount, ExpenseSplit::setAmount);
            mapper.using(
                    src -> {
                        long id = (Long) src.getSource();
                        return userRepository.findById(id).orElse(null);
                    }
            ).map(ExpenseSplitDto::getPayeeId, ExpenseSplit::setPayee);
            mapper.using(
                    src -> {
                        long id = (Long) src.getSource();
                        return userRepository.findById(id).orElse(null);
                    }
            ).map(ExpenseSplitDto::getPayerId, ExpenseSplit::setPayer);
            mapper.map(ExpenseSplitDto::isSettled, ExpenseSplit::setSettled);
        });

        TypeMap<Settlement, SettlementDto> settlementSettlementDtoTypeMap = modelMapper.createTypeMap(Settlement.class, SettlementDto.class);

        settlementSettlementDtoTypeMap.addMappings(mapper -> {
            mapper.map(Settlement::getId, SettlementDto::setId);
            mapper.map(Settlement::getAmount, SettlementDto::setAmount);
            mapper.using(
                    src -> ((User) src.getSource()).getId()
            ).map(Settlement::getPayee, SettlementDto::setPayeeId);
            mapper.using(
                    src -> ((User) src.getSource()).getId()
            ).map(Settlement::getPayer, SettlementDto::setPayerId);
        });

        TypeMap<SettlementDto, Settlement> settlementDtoSettlementTypeMap = modelMapper.createTypeMap(SettlementDto.class, Settlement.class);

        settlementDtoSettlementTypeMap.addMappings(mapper -> {
            mapper.map(SettlementDto::getId, Settlement::setId);
            mapper.map(SettlementDto::getAmount, Settlement::setAmount);
            mapper.using(
                    src -> {
                        long id = (Long) src.getSource();
                        return userRepository.findById(id).orElse(null);
                    }
            ).map(SettlementDto::getPayeeId, Settlement::setPayee);
            mapper.using(
                    src -> {
                        long id = (Long) src.getSource();
                        return userRepository.findById(id).orElse(null);
                    }
            ).map(SettlementDto::getPayerId, Settlement::setPayer);
        });

        return modelMapper;
    }
}
