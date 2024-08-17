package com.shubhamgupta.project.uber.uberApp.repositories;

import com.shubhamgupta.project.uber.uberApp.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
}