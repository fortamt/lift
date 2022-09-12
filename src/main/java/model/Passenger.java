package model;

import lombok.Getter;

@Getter
public class Passenger {
    Integer wishedStage;

    public Passenger(Integer wishedStage) {
        this.wishedStage = wishedStage;
    }
}
