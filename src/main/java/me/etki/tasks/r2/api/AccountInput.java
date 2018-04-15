package me.etki.tasks.r2.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode
public class AccountInput {
    @NotNull
    @DecimalMin("0")
    @NonNull
    @Getter
    private BigDecimal balance = BigDecimal.ZERO;

    @JsonCreator
    public AccountInput() {}

    @JsonCreator
    public AccountInput(BigDecimal balance) {
        this.balance = balance;
    }
}
