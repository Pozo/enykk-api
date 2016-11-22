package com.github.pozo.model.nearstation;

import com.github.pozo.model.FoldhelyId;
import com.github.pozo.model.station.Station;

public class NearStation {
    private final Station station;
    private final double distance;
    private final FoldhelyId foldhelyId;


    NearStation(Station station, double distance, FoldhelyId foldhelyId) {
        this.station = station;
        this.distance = distance;
        this.foldhelyId = foldhelyId;
    }

    public static NearStationBuilder builder() {
        return new NearStationBuilder();
    }

    public Station getStation() {
        return station;
    }

    public double getDistance() {
        return distance;
    }

    public FoldhelyId getFoldhelyId() {
        return foldhelyId;
    }

    @Override
    public String toString() {
        return "NearStation{" +
                "station=" + station +
                ", distance=" + distance +
                ", foldhelyId=" + foldhelyId +
                '}';
    }
}
