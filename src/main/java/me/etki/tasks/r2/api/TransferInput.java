package me.etki.tasks.r2.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

public class TransferInput {
    @Getter
    private final UUID source;
    @Getter
    private final UUID target;
    @Getter
    private final BigDecimal amount;

    @JsonCreator
    public TransferInput(UUID source, UUID target, BigDecimal amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }
}
