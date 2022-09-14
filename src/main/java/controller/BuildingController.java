package controller;

import config.AppConfiguration;
import model.Building;
import service.BuildingService;
import service.LiftService;
import service.Randomizer;
import service.StageService;

public class BuildingController {

    private final AppConfiguration appConfiguration = AppConfiguration.getAppConfiguration();
    private final BuildingService buildingService = new BuildingService();
    private final StageService stageService = new StageService();
    private final LiftService liftService = new LiftService();

    public void runLift() throws InterruptedException {
        Building building = buildingService.createBuilding(
                Randomizer.randomValueInRange(appConfiguration.getMinStages(), appConfiguration.getMaxStages()));
        stageService.fillStagesByPassengers(building, appConfiguration.getMinPassengers(), appConfiguration.getMaxPassengers());
        liftService.process(building);
    }
}
