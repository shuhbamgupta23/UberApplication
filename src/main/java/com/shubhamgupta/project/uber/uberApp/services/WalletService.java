package com.shubhamgupta.project.uber.uberApp.services;

import com.shubhamgupta.project.uber.uberApp.entities.Ride;
import com.shubhamgupta.project.uber.uberApp.entities.User;
import com.shubhamgupta.project.uber.uberApp.entities.Wallet;
import com.shubhamgupta.project.uber.uberApp.entities.enums.TransactionMethod;

public interface WalletService {


    Wallet addMoneyToWallet(User user, Double amount,String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);


    void withDrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long id);

    Wallet findWalletByUser(User user);


    Wallet createNewWallet(User user);
}
