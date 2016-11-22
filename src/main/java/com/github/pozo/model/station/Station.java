package com.github.pozo.model.station;

import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.StopId;

public class Station {
    private final String name;
    private final Coordinate coordinate;
    private final StopId stopId;

    Station(String name, Coordinate coordinate, StopId stopId) {
        this.name = name;
        this.coordinate = coordinate;
        this.stopId = stopId;
    }
    public static StationBuilder builder() {
        return new StationBuilder();
    }
    public String getName() {
        return name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public StopId getStopId() {
        return stopId;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", coordinate=" + coordinate +
                ", stopId=" + stopId +
                '}';
    }
}
