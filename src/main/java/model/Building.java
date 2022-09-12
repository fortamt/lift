package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Building {
    private List<Stage> stages = new ArrayList<>();

    public int getStagesCount() {
        return stages.size();
    }

    public Stage getStage(Integer number) {
        return stages.get(number);
    }
}