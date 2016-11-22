package com.github.pozo;

import com.github.pozo.http.HttpContentGrabber;
import com.github.pozo.model.RouteId;
import com.github.pozo.model.arrival.Arrival;
import com.github.pozo.model.Schedule;
import com.github.pozo.model.StationId;
import com.github.pozo.model.StopId;
import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.nearstation.NearStation;
import com.github.pozo.model.route.Route;
import com.github.pozo.model.station.Station;
import com.github.pozo.model.station.StationInfo;
import com.github.pozo.model.stop.Stop;
import com.github.pozo.model.vehicle.Vehicle;
import com.github.pozo.parser.arrival.ArrivalParser;
import com.github.pozo.parser.nearstation.NearStationParser;
import com.github.pozo.parser.route.RouteParser;
import com.github.pozo.parser.station.StationInfoParser;
import com.github.pozo.parser.station.StationParser;
import com.github.pozo.parser.stop.StopParser;
import com.github.pozo.parser.vehicle.VehicleDefaultMultipleLineParser;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class EnykkApi {
    private static final String BASE_SQL_URL = "http://webgyor.enykk.hu/php/sql.php";
    private static final int CITY_GYOR = 1;

    //private static final String STATION_PARAMETER = "kocsiallas=";


    public Optional<List<Station>> getStationInfo(StationId rawStationId) {
        final StationId stationId = checkNotNull(rawStationId);

        try {
            final String parameter = "varos=" + CITY_GYOR + "&id=mod_megallo|" + stationId.getId();
            final String content = HttpContentGrabber.post(BASE_SQL_URL, parameter);

            StationParser stationParser = new StationParser();
            List<Station> station = stationParser.parseMultipleLines(content);
            return Optional.of(station);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    //http://webgyor.kvrt.hu/csaoajax/sql-cs.php?vonal_irany=GY14:O,V&sep=%3Cbr/%3E
    public Optional<List<Route>> getRoutes() {
        try {
            final String parameter = "varos=" + CITY_GYOR + "&id=keres| ";
            final String content = HttpContentGrabber.post(BASE_SQL_URL, parameter);

            RouteParser routeParser = new RouteParser();
            List<Route> lineInfo = routeParser.parseMultipleLines(content);
            return Optional.of(lineInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    public Optional<List<NearStation>> getNearStations(Coordinate rawCoorinate) {
        final Coordinate coordinate = checkNotNull(rawCoorinate);
        //varos=1&id=gmaps|17.614388|47.682437
        try {
            String parameter = "varos=" + CITY_GYOR + "&id=gmaps|%s|%s";
            final String latitude = Double.toString(coordinate.getLat());
            final String longitude = Double.toString(coordinate.getLon());
            parameter = String.format(parameter, latitude, longitude);

            final String content = HttpContentGrabber.post(BASE_SQL_URL, parameter);

            NearStationParser nearStationParser = new NearStationParser();
            List<NearStation> nearStations = nearStationParser.parseMultipleLines(content);
            return Optional.of(nearStations);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    public Optional<List<StationInfo>> getStations() {
        try {
            final String parameter = "varos=" + CITY_GYOR + "&id=keres| ";
            final String content = HttpContentGrabber.post(BASE_SQL_URL, parameter);

            StationInfoParser stationInfoParser = new StationInfoParser();
            List<StationInfo> lineInfo = stationInfoParser.parseMultipleLines(content);
            return Optional.of(lineInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    public Optional<List<Arrival>> getArrivals(StopId stopId) {
        try {
            final String rawParameter = "varos=" + CITY_GYOR + "&id=online_erkezes|%s";
            final String parameter = String.format(rawParameter, stopId.getId());
            final String content = HttpContentGrabber.post(BASE_SQL_URL, parameter);

            ArrivalParser stationInfoParser = new ArrivalParser();
            List<Arrival> lineInfo = stationInfoParser.parseMultipleLines(content);
            return Optional.of(lineInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    public Optional<List<Schedule>> getSchedules() {
        //varos=1&id=kocsiall_menetrend|23016|2016-11-20
//        varos:1&id:kocsiall_menetrend|20859|2016-11-22
        final List<Schedule> reference = new ArrayList<Schedule>();
        return Optional.of(reference);
    }

    public Optional<List<Stop>> getStops(StationId stationId) {
        try {
            final String rawParameter = "varos=" + CITY_GYOR + "&id=mod_megallo|%d";
            final String parameter = String.format(rawParameter, stationId.getId());
            final String content = HttpContentGrabber.post(BASE_SQL_URL, parameter);

            StopParser stationInfoParser = new StopParser();
            List<Stop> lineInfo = stationInfoParser.parseMultipleLines(content);
            return Optional.of(lineInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    public Optional<List<Vehicle>> getVehicles(RouteId routeId) {
        final ArrayList<RouteId> routeIds = new ArrayList<RouteId>();
        routeIds.add(routeId);

        return getVehicles(routeIds);
    }

    public Optional<List<Vehicle>> getVehicles(List<RouteId> routeIds) {
        checkNotNull(routeIds);
        checkArgument(routeIds.size() > 0, "routeIds must contains at least one element!");

        try {
            final String parameter = getVehicleParameters(routeIds);
            final String content = HttpContentGrabber.post(BASE_SQL_URL, parameter);

            VehicleDefaultMultipleLineParser vehicleParser = new VehicleDefaultMultipleLineParser();
            List<Vehicle> lineInfo = vehicleParser.parseMultipleLines(content);
            return Optional.of(lineInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    private String getVehicleParameters(List<RouteId> routeIds) {
        StringBuilder builder = new StringBuilder("varos=" + CITY_GYOR + "&id=online_buszok|");
        for (int i = 0; i < routeIds.size(); i++) {
            final RouteId routeId = routeIds.get(i);
            builder.append(routeId.getId());
            if (i != routeIds.size() - CITY_GYOR) {
                builder.append(',');
            }
        }
        return builder.toString();
    }
}
