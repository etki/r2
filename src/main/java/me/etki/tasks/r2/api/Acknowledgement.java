package me.etki.tasks.r2.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

public class Acknowledgement {
    @Getter
    private final boolean acknowledged;

    @JsonCreator
    public Acknowledgement(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }
}
