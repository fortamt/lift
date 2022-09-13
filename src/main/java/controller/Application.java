package controller;


public class Application {
    public static void main(String[] args) throws InterruptedException {

        BuildingController buildingController = new BuildingController();

        buildingController.runLift();
    }
}
