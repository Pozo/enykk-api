package com.github.pozo.model.station;

import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.StopId;

import static com.google.common.base.Preconditions.checkNotNull;

public class StationBuilder {
    private String name;
    private Coordinate coordinate;
    private StopId stopId;

    StationBuilder() {

    }

    public StationBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StationBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public StationBuilder setStopId(StopId stopId) {
        this.stopId = stopId;
        return this;
    }

    public Station createStation() {
        checkNotNull(name, "name cant be null!");
        checkNotNull(coordinate, "coordinate cant be null!");
        checkNotNull(stopId, "stopId cant be null!");

        return new Station(name, coordinate, stopId);
    }
}