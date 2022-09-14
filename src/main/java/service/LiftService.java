package service;

import config.AppConfiguration;
import model.Building;
import model.Lift;
import model.Passenger;
import model.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LiftService {

    private final AppConfiguration appConfiguration = AppConfiguration.getAppConfiguration();
    private final ConsoleDrawingService consoleDrawingService = new ConsoleDrawingService();

    public void process(Building building) throws InterruptedException {
        Lift lift = new Lift(appConfiguration.getLiftCapacity());
        while(true) {
            getOutPassengers(lift, building);
            fillLift(building, lift);
            Thread.sleep(500);
        }
    }

    private void getOutPassengers(Lift lift, Building building) {
        if (!lift.isEmpty()) {
            List<Passenger> passengers = lift.getPassengersOfLift();
            List<Passenger> passengerListWhoLeft = new LinkedList<>();
            for (Passenger passenger : passengers) {
                if (Objects.equals(passenger.getWishedStage(), lift.getCurrentStage())) {
                    passengerListWhoLeft.add(passenger);
                    Stage stage = building.getStage(lift.getCurrentStage()-1);
                    if (stage.getPassengersCount() < appConfiguration.getMaxPassengers()){
                        Passenger newPassenger = Passenger.getPassenger(building, lift.getCurrentStage());
                        stage.addPassengerToQueue(newPassenger);
                    }
                }
            }
            passengers.removeAll(passengerListWhoLeft);
        }
    }

    private void fillLift(Building building, Lift lift) {
        int currentStage = lift.getCurrentStage();
        Stage stage = building.getStages().get(currentStage - 1);
        if (lift.isEmpty()) {
            stage.setDirectionForEmptyLift(lift, building);
        }
        if (stage.isEmpty()) {
            consoleDrawingService.draw(building, lift);
            moveLift(lift);
        } else {
            consoleDrawingService.draw(building, lift);
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
}
