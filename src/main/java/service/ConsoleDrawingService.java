package service;

import model.Building;
import model.Lift;
import model.Passenger;

import java.util.List;
import java.util.Optional;

public class ConsoleDrawingService {
    public void draw(Building building, Lift lift) {
        int stageCount = building.getStagesCount();
        String moveDirection = lift.getLiftMoveDirection();
        int currentLiftStage = lift.getCurrentStage();
        for (int i = stageCount; i >= 1; i--) {
            if (i+1 == currentLiftStage) {
                System.out.println(i+1 + "|" + printMoveDirection(moveDirection) + printLift(lift) + printMoveDirection(moveDirection) + "|" + printQueue(building, i));
            } else {
                System.out.println(i+1 + "| " + printWell(lift) + " |" + printQueue(building, i));
            }
        }
    }

    private String printMoveDirection(String moveDirection) {
        if(moveDirection.equals("up")) {
            return "^";
        } else if(moveDirection.equals("down")){
            return "v";
        } else {
            return null;
        }
    }

    private StringBuilder printQueue(Building building, int stageNumber) {
        List<Passenger> passengers = building.getStage(stageNumber-1).getQueue();
        StringBuilder queue = new StringBuilder();
        for (Passenger passenger : passengers) {
            queue.append(passenger.getWishedStage() + " ");
        }
        return queue;
    }

    private StringBuilder printLift(Lift lift) {
        int liftCapacity = lift.getMaxCapacity();
        StringBuilder strLiftCapacity = new StringBuilder();
        for (int i = 0; i < liftCapacity; i++) {
            if (lift.getPassengersOfLift().size() <= i && !lift.getPassengersOfLift().isEmpty()) {
                Passenger passenger = lift.getPassengersOfLift().get(i);
                strLiftCapacity.append(passenger.getWishedStage().toString());
            } else {
                strLiftCapacity.append("_");
            }
        }
        return strLiftCapacity;
    }

    private StringBuilder printWell(Lift lift) {
        int liftCapacity = lift.getMaxCapacity();
        StringBuilder strLiftCapacity = new StringBuilder();
        strLiftCapacity.append(" ".repeat(Math.max(0, liftCapacity)));
        return strLiftCapacity;
    }
}
