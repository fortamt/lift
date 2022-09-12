package model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Stage {
    private Integer stageLevel;
    private List<Passenger> passengersWhoWantsGoUp = new LinkedList<>();
    private List<Passenger> passengersWhoWantsGoDown = new LinkedList<>();

    public Stage(Integer stageLevel) {
        this.stageLevel = stageLevel;
    }

    public Integer getPassengersCount() {
        return this.passengersWhoWantsGoDown.size() + this.passengersWhoWantsGoUp.size();
    }

    public void setDirectionForEmptyLift(Lift lift, Building building) {
        if (passengersWhoWantsGoUp.size() > passengersWhoWantsGoDown.size()) {
            lift.setLiftMoveDirection("up");
        } else if (passengersWhoWantsGoDown.size() > passengersWhoWantsGoUp.size()) {
            lift.setLiftMoveDirection("down");
        } else if (lift.getCurrentStage() == building.getStagesCount()) {
            lift.setLiftMoveDirection("down");
        } else if (lift.getCurrentStage() == 1) {
            lift.setLiftMoveDirection("up");
        }
    }

    public boolean isEmpty() {
        return getPassengersCount() == 0;
    }

    public boolean isEqualsQueueAndDirection(Lift lift) {
        if (lift.getLiftMoveDirection().equals("up") && !passengersWhoWantsGoUp.isEmpty()) {
            return true;
        } else {
            return lift.getLiftMoveDirection().equals("down") && !passengersWhoWantsGoDown.isEmpty();
        }
    }

    public Passenger getCompanionPassenger(Lift lift) {
        if (lift.getLiftMoveDirection().equals("up")) {
            return passengersWhoWantsGoUp.remove(1);
        } else if (lift.getLiftMoveDirection().equals("down")) {
            return passengersWhoWantsGoDown.remove(1);
        } else return null;
    }

    public void addPassengerToQueue(Passenger newPassenger) {
        if (newPassenger.getWishedStage() > stageLevel) {
            this.passengersWhoWantsGoUp.add(newPassenger);
        } else if (newPassenger.getWishedStage() < stageLevel) {
            this.passengersWhoWantsGoDown.add(newPassenger);
        }
    }

    public List<Passenger> getQueue() {
        List<Passenger> queue = this.passengersWhoWantsGoDown;
        queue.addAll(passengersWhoWantsGoUp);
        return queue;
    }

}
