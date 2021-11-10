package com.cwsdg.cwsdg.controller.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum PayMode {

    @JsonProperty("3M")
    THREEMONTHS(150, 3, "MONTHLY"),
    @JsonProperty("M")
    MONTH(34, 1, "MONTHLY"),
    @JsonProperty("D")
    DAY(5, 1, "DAIRY"),
    @JsonProperty("6D")
    SIXDAYS(5, 1, "DAIRY");


    private int ammount;
    private int duration;
    private String type;

    PayMode(int ammount, int duration, String type) {
        this.ammount = ammount;
        this.duration = duration;
        this.type = type;

    }

}
