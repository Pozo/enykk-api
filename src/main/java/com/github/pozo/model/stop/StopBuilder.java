package com.github.pozo.model.stop;

import com.github.pozo.model.StopId;
import com.github.pozo.model.coordinate.Coordinate;

import static com.google.common.base.Preconditions.checkNotNull;

public class StopBuilder {
    private StopId stopId;
    private String name;
    private Coordinate coordinate;

    StopBuilder() {

    }
    public StopBuilder setStopId(StopId stopId) {
        this.stopId = stopId;
        return this;
    }

    public StopBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StopBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public Stop createStop() {
        checkNotNull(stopId, "stopId cant be null!");
        checkNotNull(name, "name cant be null!");
        checkNotNull(coordinate, "coordinate cant be null!");
        return new Stop(stopId, name, coordinate);
    }
}