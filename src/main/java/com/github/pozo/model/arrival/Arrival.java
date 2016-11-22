package com.github.pozo.model.arrival;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;
import com.github.pozo.model.VehicleType;

public class Arrival {
    private LineId lineId;
    private String lineName;
    private RouteId routeId;
    private double expectedArrival;
    private boolean arrived;
    private VehicleType vehicleType;

    Arrival(LineId lineId, String lineName, RouteId routeId, double expectedArrival, boolean arrived, VehicleType vehicleType) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.routeId = routeId;
        this.expectedArrival = expectedArrival;
        this.arrived = arrived;
        this.vehicleType = vehicleType;
    }

    public static ArrivalBuilder builder() {
        return new ArrivalBuilder();
    }

    public LineId getLineId() {
        return lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public RouteId getRouteId() {
        return routeId;
    }

    public double getExpectedArrival() {
        return expectedArrival;
    }

    public boolean isArrived() {
        return arrived;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "lineId=" + lineId +
                ", lineName='" + lineName + '\'' +
                ", routeId=" + routeId +
                ", expectedArrival=" + expectedArrival +
                ", arrived=" + arrived +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
