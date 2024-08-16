package com.shubhamgupta.project.uber.uberApp.dto;

import com.shubhamgupta.project.uber.uberApp.entities.Ride;
import com.shubhamgupta.project.uber.uberApp.entities.enums.TransactionMethod;
import com.shubhamgupta.project.uber.uberApp.entities.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDTO {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private Ride ride;

    private String transactionId;

    private WalletDTO wallet;

    private LocalDateTime timeStamp;

}
