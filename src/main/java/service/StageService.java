package service;

import model.Building;
import model.Passenger;

public class StageService {

    public void fillStagesByPassengers(Building building,
                                       Integer minPassengers, Integer maxPassengers,
                                       Integer minStage, Integer maxStage) {
        int buildingSize = building.getStages().size();
        for (int i = 0; i < buildingSize; i++) {
            int currentStage = i + 1;
            int passengerCount = generatePassengerCount(minPassengers, maxPassengers);
            for (int j = 0; j <= passengerCount; j++) {
                Passenger passenger = generatePassenger(currentStage, minStage, maxStage);
                if (passenger.getWishedStage() > currentStage) {
                    building.getStages().get(i).getPassengersWhoWantsGoUp().add(passenger);
                }
                if (passenger.getWishedStage() < currentStage) {
                    building.getStages().get(i).getPassengersWhoWantsGoDown().add(passenger);
                }
            }
        }
    }

    private Passenger generatePassenger(Integer currentStage, Integer minStage, Integer maxStage) {
        int wishStage = Randomizer.randomValueInRange(minStage, maxStage);
        while (wishStage == currentStage) {
            wishStage = Randomizer.randomValueInRange(minStage, maxStage);
        }
        return new Passenger(wishStage);
    }

    private Integer generatePassengerCount(Integer minPassengers, Integer maxPassengers) {
        return Randomizer.randomValueInRange(minPassengers, maxPassengers);
    }
}
