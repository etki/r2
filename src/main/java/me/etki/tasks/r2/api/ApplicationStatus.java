package me.etki.tasks.r2.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

public class ApplicationStatus {
    @Getter
    private final HealthColor color;

    @JsonCreator
    public ApplicationStatus(HealthColor color) {
        this.color = color;
    }
}
