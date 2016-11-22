package com.github.pozo.parser.vehicle;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;
import com.github.pozo.model.StopId;
import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.vehicle.Vehicle;
import com.github.pozo.model.vehicle.VehicleBuilder;
import com.github.pozo.model.VehicleType;
import com.github.pozo.parser.DefaultMultipleLineParser;

import java.util.ArrayList;
import java.util.List;

public class VehicleDefaultMultipleLineParser extends DefaultMultipleLineParser<Vehicle> {
    @Override
    public List<Vehicle> parseMultipleLines(String rawMessage) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        List<String> entries = getEntries(rawMessage);

        for (String entry : entries) {
            final String[] cells = getCells(entry);

            Vehicle vehicle = parseVehicle(cells);
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    private Vehicle parseVehicle(String[] cells) {
        final VehicleBuilder builder = Vehicle.builder();

        final String rawRouteTrackId = cells[0].trim();
        final String rawLatitude = cells[1].trim();
        final String rawLongitude = cells[2].trim();
        final String rawDate = cells[3].trim();

        final String rawRouteDescription = cells[4].trim();
        final String rawXyId = cells[5].trim();
        final String rawStopId = cells[6].trim();
        final String rawLineId = cells[7].trim();
        final String rawDirectionAngle = cells[8].trim();
        final String rawVehicleId = cells[9].trim();

        final Coordinate coordinate = Coordinate.builder()
                .setLat(Double.parseDouble(rawLatitude))
                .setLon(Double.parseDouble(rawLongitude))
                .createCoordinate();

        final RouteId routeId = new RouteId(Integer.parseInt(rawRouteTrackId));
        final String rawStopId1 = getRawStopId(rawStopId);
        final StopId stopId = new StopId(Integer.parseInt(rawStopId1));
        final int directionAngle = Integer.parseInt(rawDirectionAngle);
        final LineId lineId = new LineId(rawLineId);
        final VehicleType vehicleType = getVehicleType(rawVehicleId);

        builder.setRouteDescription(rawRouteDescription)
                .setRouteId(routeId)
                .setStopId(stopId)
                .setCoordinate(coordinate)
                .setDate(rawDate)
                .setDirectionAngle(directionAngle)
                .setLineId(lineId)
                .setVehicleType(vehicleType);

        return builder.createVehicle();
    }

    private String getRawStopId(String rawStopId) {
        if (rawStopId.equals("")) {
            return "0";
        } else {
            return rawStopId;
        }
    }

    private VehicleType getVehicleType(String rawVehicleType) {
        if ("Alacsony padlós".equals(rawVehicleType)) {
            return VehicleType.LOW_FLOOR;
        } else {
            return VehicleType.NORMAL;
        }

    }

    @Override
    protected int getCellNumber() {
        return 10;
    }
}
