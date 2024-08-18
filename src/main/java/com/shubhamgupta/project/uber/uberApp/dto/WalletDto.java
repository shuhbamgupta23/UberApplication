package com.shubhamgupta.project.uber.uberApp.dto;

import com.shubhamgupta.project.uber.uberApp.entities.WalletTransaction;
import lombok.Data;

import java.util.List;


@Data
public class WalletDto {
    private Long id;

    private UserDto user;

    private Double balance;

    private List<WalletTransaction> transactions;
}
