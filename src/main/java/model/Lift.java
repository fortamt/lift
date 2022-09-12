package model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Lift {
    private final Integer maxCapacity;
    private String liftMoveDirection = "up";
    private Integer maxRequestedStage = 1;
    private Integer currentStage = 1;
    List<Passenger> passengersOfLift = new LinkedList<>();
    public Lift(Integer capacity) {
        this.maxCapacity = capacity;
    }

    public boolean hasPlace() {
        return passengersOfLift.size() < maxCapacity;
    }

    public boolean isEmpty() {
        return passengersOfLift.isEmpty();
    }

    public void goUp() {
        currentStage++;
    }

    public void goDown() {
        currentStage--;
    }
}