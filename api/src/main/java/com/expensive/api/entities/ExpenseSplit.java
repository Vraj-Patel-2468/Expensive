package com.expensive.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "expenseId")
    private Expense expense;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "payeeId")
    private User payee;

    @ManyToOne
    @JoinColumn(name = "payerId")
    private User payer;

    private boolean isSettled;
}
