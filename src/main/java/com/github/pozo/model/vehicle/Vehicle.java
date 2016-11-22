package com.github.pozo.model.vehicle;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;
import com.github.pozo.model.StopId;
import com.github.pozo.model.VehicleType;
import com.github.pozo.model.coordinate.Coordinate;

public class Vehicle {
    //nyomvon_id|GH|GV|datum|vonalvezetes_leiras|ST|kocsiall_id|nyomvonal|irany|tipus
    //1000136|17.635888888|47.662488888|20:25:16|Marcalváros - Liget u. Nyár u.|524|23049|14    |0|Alacsony padlós
    private final Coordinate coordinate;
    private final String date;
    private final String routeDescription;
    private final StopId stopId;
    private final RouteId routeId;
    private final LineId lineId;
    private final int directionAngle;
    private final VehicleType vehicleType;

    Vehicle(Coordinate coordinate, String date, String routeDescription, StopId stopId, RouteId routeId, LineId lineId, int directionAngle, VehicleType vehicleType) {
        this.coordinate = coordinate;
        this.date = date;
        this.routeDescription = routeDescription;
        this.stopId = stopId;
        this.routeId = routeId;
        this.lineId = lineId;
        this.directionAngle = directionAngle;
        this.vehicleType = vehicleType;
    }

    public static VehicleBuilder builder() {
        return new VehicleBuilder();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getDate() {
        return date;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public int getDirectionAngle() {
        return directionAngle;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public StopId getStopId() {
        return stopId;
    }

    public RouteId getRouteId() {
        return routeId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "coordinate=" + coordinate +
                ", date='" + date + '\'' +
                ", routeDescription='" + routeDescription + '\'' +
                ", stopId=" + stopId +
                ", routeId=" + routeId +
                ", directionAngle=" + directionAngle +
                ", vehicleType=" + vehicleType +
                '}';
    }

    public LineId getLineId() {
        return lineId;
    }
}
