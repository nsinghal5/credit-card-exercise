package com.sapient.credit.card.exercise.model;

import com.sapient.credit.card.exercise.validator.EnsureLuhn;
import com.sapient.credit.card.exercise.validator.EnsureNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @Size(min = 12, max = 19, message = "Card number length should be between 12 to 19")
    @EnsureNumber(message = "Card number must be numeric only")
    @EnsureLuhn(message = "Card number must be Luhn 10 compliant")
    @Column(name="card_number")
    private String cardNumber;

    @NotEmpty(message = "Name must NOT be empty")
    @Column(name="name")
    private String name;

    @Column(name="credit_limit")
    private double creditLimit = 0.0;

    @Column(name="balance")
    private double balance = 0.0;

    public CreditCard(String name, String cardNumber, double creditLimit) {
        this(name, cardNumber, creditLimit, creditLimit);
    }

    public CreditCard(String name, String cardNumber, double creditLimit, double balance) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", creditLimit=" + creditLimit +
                ", balance=" + balance +
                '}';
    }
}
