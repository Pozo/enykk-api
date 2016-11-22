package com.github.pozo.model.nearstation;

import com.github.pozo.model.FoldhelyId;
import com.github.pozo.model.station.Station;

import static com.google.common.base.Preconditions.checkNotNull;

public class NearStationBuilder {
    private Station station;
    private double distance;
    private FoldhelyId foldhelyId;

    NearStationBuilder() {

    }

    public NearStationBuilder setStation(Station station) {
        this.station = station;
        return this;
    }

    public NearStationBuilder setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public NearStationBuilder setFoldhelyId(FoldhelyId foldhelyId) {
        this.foldhelyId = foldhelyId;
        return this;
    }

    public NearStation createNearStation() {
        checkNotNull(station, "station cant be null!");
        checkNotNull(distance, "distance cant be null!");
        checkNotNull(foldhelyId, "foldhelyId cant be null!");
        return new NearStation(station, distance, foldhelyId);
    }


}