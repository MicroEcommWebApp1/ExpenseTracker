package com.dxc.expense.model;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "expense")
public class Expense {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id", updatable = false, nullable = false)
    private Integer expenseId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "description")
    private String description;
    
    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
    
    @Lob
    @Column(name = "receipt")
    @Basic(fetch = FetchType.LAZY)
    private byte[] receipt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
