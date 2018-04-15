package me.etki.tasks.r2.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DecimalValue {
    @Getter
    @NotNull
    private final BigDecimal value;

    @JsonCreator
    public DecimalValue(BigDecimal value) {
        this.value = value;
    }
}
