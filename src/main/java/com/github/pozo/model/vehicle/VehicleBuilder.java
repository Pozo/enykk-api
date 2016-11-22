package com.github.pozo.model.vehicle;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;
import com.github.pozo.model.StopId;
import com.github.pozo.model.VehicleType;
import com.github.pozo.model.coordinate.Coordinate;

import static com.google.common.base.Preconditions.checkNotNull;

public class VehicleBuilder {
    private Coordinate coordinate;
    private String date;
    private String routeDescription;
    private int directionAngle;
    private VehicleType vehicleType;
    private StopId stopId;
    private RouteId routeId;
    private LineId lineId;

    VehicleBuilder() {

    }

    public VehicleBuilder setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public VehicleBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public VehicleBuilder setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
        return this;
    }

    public VehicleBuilder setDirectionAngle(int direction) {
        this.directionAngle = direction;
        return this;
    }

    public VehicleBuilder setStopId(StopId stopId) {
        this.stopId = stopId;
        return this;
    }

    public VehicleBuilder setRouteId(RouteId routeId) {
        this.routeId = routeId;
        return this;
    }
    public VehicleBuilder setLineId(LineId lineId) {
        this.lineId = lineId;
        return this;
    }
    public VehicleBuilder setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public Vehicle createVehicle() {
        checkNotNull(coordinate, "coordinate must be not null!");
        checkNotNull(date, "date must be not null!");
        checkNotNull(routeDescription, "routeDescription must be not null!");
        checkNotNull(stopId, "stopId must be not null!");
        checkNotNull(routeId, "routeId must be not null!");
        checkNotNull(directionAngle, "directionAngle must be not null!");
        checkNotNull(lineId, "lineId must be not null!");
        checkNotNull(vehicleType, "vehicleType must be not null!");

        return new Vehicle(coordinate, date, routeDescription, stopId, routeId, lineId, directionAngle, vehicleType);
    }
}