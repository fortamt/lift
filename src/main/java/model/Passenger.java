package model;

import lombok.Getter;
import service.Randomizer;

@Getter
public class Passenger {
    Integer wishedStage;

    public Passenger(Integer wishedStage) {
        this.wishedStage = wishedStage;
    }

    public static Passenger getPassenger(Building building, Integer currentStage) {
        var max = building.getStagesCount();
        var passWishStage = Randomizer.randomValueInRange(1, max);
        while (passWishStage.equals(currentStage)) {
            passWishStage = Randomizer.randomValueInRange(1, max);
        }
        return new Passenger(passWishStage);
    }
}
