package com.github.pozo.model.station;

import com.github.pozo.model.StationId;

// the list version of station, just a holder for name and id
public class StationInfo {
    private final String name;
    private final StationId stationId;

    public StationInfo(String name, StationId stationId) {
        this.name = name;
        this.stationId = stationId;
    }

    public StationId getStationId() {
        return stationId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StationInfo{" +
                "name='" + name + '\'' +
                ", stationId=" + stationId +
                '}';
    }
}
