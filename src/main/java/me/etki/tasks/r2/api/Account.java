package me.etki.tasks.r2.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
public class Account {
    @Getter
    private final UUID id;
    @Getter
    private final BigDecimal balance;

    public Account(UUID id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }
}
