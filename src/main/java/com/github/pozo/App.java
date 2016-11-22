package com.github.pozo;

import com.github.pozo.model.RouteId;
import com.github.pozo.model.StationId;
import com.github.pozo.model.StopId;
import com.github.pozo.model.arrival.Arrival;
import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.nearstation.NearStation;
import com.github.pozo.model.route.Route;
import com.github.pozo.model.station.Station;
import com.github.pozo.model.station.StationInfo;
import com.github.pozo.model.stop.Stop;
import com.github.pozo.model.vehicle.Vehicle;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        final EnykkApi api = new EnykkApi();

//        testGetStations(api);
//        testGetStationInfo(api);
//        testGetNearStations(api);
//        testGetRoutes(api);
//        testGetVehicles(api);
//        testGetArrivals(api);
//        testGetStationsStopsAndArrivals(api); // very slow!

        Optional<List<Stop>> stops = api.getStops(new StationId(20675));

        if (stops.isPresent()) {
            for (Stop stop : stops.get()) {
                api.getArrivals(stop.getStopId());
                System.out.println("stop = " + stop);
            }
        }
    }

    // very slow!
    private static void testGetStationsStopsAndArrivals(EnykkApi api) {
        Optional<List<StationInfo>> stations = api.getStations();

        if (stations.isPresent()) {
            for (StationInfo station : stations.get()) {
                System.out.println("station = " + station);
                Optional<List<Stop>> stops = api.getStops(station.getStationId());

                if (stops.isPresent()) {
                    for (Stop stop : stops.get()) {
                        System.out.println("stop = " + stop);
                        Optional<List<Arrival>> arrivals = api.getArrivals(stop.getStopId());

                        if (arrivals.isPresent()) {
                            for (Arrival arrival : arrivals.get()) {
                                System.out.println("arrival = " + arrival);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void testGetArrivals(EnykkApi api) {
        final StopId stopId = new StopId(23016);
        Optional<List<Arrival>> arrivals = api.getArrivals(stopId);

        if (arrivals.isPresent()) {
            for (Arrival arrival : arrivals.get()) {
                System.out.println("arrival1 = " + arrival);
            }
        }
    }

    private static void testGetVehicles(EnykkApi api) {
        final ArrayList<RouteId> routeIds = getRouteTrackIds();
        Optional<List<Vehicle>> vehicles = api.getVehicles(routeIds);

        if (vehicles.isPresent()) {
            for (Vehicle vehicle : vehicles.get()) {
                System.out.println("vehicle = " + vehicle);
            }
        }
    }

    private static ArrayList<RouteId> getRouteTrackIds() {
        ArrayList<RouteId> routeIds = new ArrayList<RouteId>();
        routeIds.add(new RouteId(1003734));
        routeIds.add(new RouteId(1000088));
        routeIds.add(new RouteId(1000168));
        routeIds.add(new RouteId(1000183));
        routeIds.add(new RouteId(1001256));
        return routeIds;
    }

    private static void testGetRoutes(EnykkApi api) {
        final Optional<List<Route>> routes = api.getRoutes();

        if (routes.isPresent()) {
            for (Route route : routes.get()) {
                System.out.println("route = " + route);
            }
        }
    }

    private static void testGetNearStations(EnykkApi api) {
        Coordinate coordinate = Coordinate.builder()
                .setLat(17.614388)
                .setLon(47.682437)
                .createCoordinate();

        final Optional<List<NearStation>> nearStations = api.getNearStations(coordinate);

        if (nearStations.isPresent()) {
            for (NearStation nearStation : nearStations.get()) {
                System.out.println("nearStation = " + nearStation);
            }
        }
    }

    private static void testGetStationInfo(EnykkApi api) {
        final StationId stationId = new StationId(20505);
        Optional<List<Station>> stations = api.getStationInfo(stationId);

        if (stations.isPresent()) {
            for (Station station : stations.get()) {
                System.out.println("station = " + station);
            }

        }
    }

    private static void testGetStations(EnykkApi api) {
        final Optional<List<StationInfo>> stations = api.getStations();

        if (stations.isPresent()) {
            for (StationInfo stationInfo : stations.get()) {
                System.out.println("stationInfo = " + stationInfo);
            }
        }

    }
}
