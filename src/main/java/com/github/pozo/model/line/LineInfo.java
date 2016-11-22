package com.github.pozo.model.line;

import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.Direction;
import com.github.pozo.model.VehicleId;
import com.github.pozo.model.VehicleType;

public class LineInfo {
    private final VehicleId vehicleId;
    private final VehicleType vehicleType;
    private final Direction direction;
    private final Coordinate coordinate;

    LineInfo(VehicleId vehicleId, VehicleType vehicleType, Direction direction, Coordinate coordinate) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.direction = direction;
        this.coordinate = coordinate;
    }

    public static LineInfoBuilder builder() {
        return new LineInfoBuilder();
    }

    public VehicleId getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Direction getDirection() {
        return direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "LineInfo{" +
                "vehicleId=" + vehicleId +
                ", vehicleType=" + vehicleType +
                ", direction=" + direction +
                ", coordinate=" + coordinate +
                '}';
    }
}
