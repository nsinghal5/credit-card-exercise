package com.sapient.credit.card.exercise.repository;

import com.sapient.credit.card.exercise.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

}
