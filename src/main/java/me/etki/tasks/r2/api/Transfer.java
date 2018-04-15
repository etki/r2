package me.etki.tasks.r2.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(of = "id")
public class Transfer {
    @Getter
    private final UUID id;
    @Getter
    private final UUID source;
    @Getter
    private final UUID target;
    @Getter
    private final BigDecimal balance;

    @JsonCreator
    public Transfer(UUID id, UUID source, UUID target, BigDecimal balance) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.balance = balance;
    }
}
