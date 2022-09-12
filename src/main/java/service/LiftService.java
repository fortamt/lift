package service;

import config.AppConfiguration;
import model.Building;
import model.Lift;
import model.Passenger;
import model.Stage;

import java.util.List;
import java.util.Objects;

public class LiftService {

    private final AppConfiguration appConfiguration = new AppConfiguration();
    private final ConsoleDrawingService consoleDrawingService = new ConsoleDrawingService();

    public void process(Building building) {
        Lift lift = new Lift(appConfiguration.getLiftCapacity());
        while(true) {
            getOutPassengers(lift, building);
            fillLift(building, lift);
            consoleDrawingService.draw(building, lift);
        }
    }

    private void getOutPassengers(Lift lift, Building building) {
        if (!lift.isEmpty()) {
            List<Passenger> passengers = lift.getPassengersOfLift();
            for (Passenger passenger : passengers) {
                if (Objects.equals(passenger.getWishedStage(), lift.getCurrentStage())) {
                    passengers.remove(passenger);
                    Stage stage = building.getStage(lift.getCurrentStage()-1);
                    Passenger newPassenger = regeneratePassenger(lift.getCurrentStage(), building.getStagesCount());
                    stage.addPassengerToQueue(newPassenger);
                }
            }
        }
    }

    private void fillLift(Building building, Lift lift) {
        int currentStage = lift.getCurrentStage();
        Stage stage = building.getStages().get(currentStage - 1);
        if (lift.isEmpty()) {
            stage.setDirectionForEmptyLift(lift, building);
        }
        if (stage.isEmpty()) {
            moveLift(lift);
        } else {
            while (lift.hasPlace() && stage.isEqualsQueueAndDirection(lift)) {
                lift.getPassengersOfLift().add(stage.getCompanionPassenger(lift));
            }
            moveLift(lift);
        }
    }

    private void moveLift(Lift lift) {
        if (lift.getLiftMoveDirection().equals("up")) {
            lift.goUp();
        } else if (lift.getLiftMoveDirection().equals("down")) {
            lift.goDown();
        }
    }

    private Passenger regeneratePassenger(Integer currentStage, Integer maxStage) {
        int wishStage = Randomizer.randomValueInRange(1, maxStage);
        while (wishStage == currentStage) {
            wishStage = Randomizer.randomValueInRange(1, maxStage);
        }
        return new Passenger(wishStage);
    }
}
