package service;

import model.Building;
import model.Passenger;

public class StageService {

    public void fillStagesByPassengers(Building building,
                                       Integer minPassengers, Integer maxPassengers) {
        int buildingSize = building.getStages().size();
        for (int i = 0; i < buildingSize; i++) {
            int currentStage = i + 1;
            int passengerCount = generatePassengerCount(minPassengers, maxPassengers);
            for (int j = 0; j <= passengerCount; j++) {
                Passenger passenger = Passenger.getPassenger(building, currentStage);
                if (passenger.getWishedStage() > currentStage) {
                    building.getStages().get(i).getPassengersWhoWantsGoUp().add(passenger);
                }
                if (passenger.getWishedStage() < currentStage) {
                    building.getStages().get(i).getPassengersWhoWantsGoDown().add(passenger);
                }
            }
        }
    }

    private Integer generatePassengerCount(Integer minPassengers, Integer maxPassengers) {
        return Randomizer.randomValueInRange(minPassengers, maxPassengers);
    }
}
