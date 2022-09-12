package service;

import model.Building;
import model.Stage;

public class BuildingService {

    public Building createBuilding(Integer stages) {
        Building building = new Building();
        fillBuildingByStages(building, stages);
        return building;
    }

    public void fillBuildingByStages(Building building, Integer stagesCount) {
        for (int i = 0; i <= stagesCount; i++) {
            building.getStages().add(i, new Stage(i+1));
        }
    }
}