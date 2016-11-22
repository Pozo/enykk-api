package com.github.pozo.model.stop;

import com.github.pozo.model.StopId;
import com.github.pozo.model.coordinate.Coordinate;

public class Stop {
    private final StopId stopId;
    private final String name;
    private final Coordinate coordinate;

    Stop(StopId stopId, String name, Coordinate coordinate) {
        this.stopId = stopId;
        this.name = name;
        this.coordinate = coordinate;
    }
    public static StopBuilder builder() {
        return new StopBuilder();
    }

    public StopId getStopId() {
        return stopId;
    }

    public String getName() {
        return name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "stopId=" + stopId +
                ", name='" + name + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
