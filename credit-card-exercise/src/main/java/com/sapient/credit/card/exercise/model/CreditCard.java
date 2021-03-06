package com.sapient.credit.card.exercise.model;

import com.sapient.credit.card.exercise.validator.EnsureLuhn;
import com.sapient.credit.card.exercise.validator.EnsureNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Size(min = 12, max = 19, message = "Card number length should be between 12 to 19")
    @EnsureNumber(message = "Card number must be numeric only")
    @EnsureLuhn(message = "Card number must be Luhn 10 compliant")
    @Column(name="card_number", unique = true)
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
                "id='" + id + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", creditLimit=" + creditLimit +
                ", balance=" + balance +
                '}';
    }
}
