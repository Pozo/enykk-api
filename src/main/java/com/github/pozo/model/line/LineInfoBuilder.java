package com.github.pozo.model.line;

import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.Direction;
import com.github.pozo.model.VehicleId;
import com.github.pozo.model.VehicleType;

import static com.google.common.base.Preconditions.checkNotNull;

public class LineInfoBuilder {
    private VehicleId vehicleId;
    private VehicleType vehicleType;
    private Direction direction;
    private Coordinate coordinate;

    LineInfoBuilder() {
    }

    public LineInfoBuilder setVehicleId(VehicleId vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public LineInfoBuilder setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public LineInfoBuilder setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public LineInfoBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public LineInfo createLineInfo() {
        checkNotNull(vehicleId, "vehicleId must be not null!");
        checkNotNull(vehicleType, "vehicleType must be not null!");
        checkNotNull(direction, "direction must be not null!");
        checkNotNull(coordinate, "coordinate must be not null!");

        return new LineInfo(vehicleId, vehicleType, direction, coordinate);
    }
}