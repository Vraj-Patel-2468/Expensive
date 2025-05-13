package com.expensive.api.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String email;
    private String password;
    private boolean isEnabled;
    
    private String verificationCode;
    private LocalDateTime verificationCodeExpired;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    private void setDates() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PostUpdate
    private void setUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "createdBy")
    private Set<Group> groupsCreated;

    @ManyToMany(mappedBy = "members")
    private Set<Group> groupsJoined;

    @OneToMany(mappedBy = "paidBy")
    private Set<Expense> expensesPaid;

    @OneToMany(mappedBy = "payee")
    private Set<ExpenseSplit> splitsToCollect;

    @OneToMany(mappedBy = "payer")
    private Set<ExpenseSplit> splitsToPay;

    @OneToMany(mappedBy = "payee")
    private Set<Settlement> settlementsToCollect;

    @OneToMany(mappedBy = "payer")
    private Set<Settlement> settlementsToPay;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
    
}
