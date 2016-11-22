package com.github.pozo.model.coordinate;

import static com.google.common.base.Preconditions.checkNotNull;

public class CoordinateBuilder {
    private double lat;
    private double lon;

    public CoordinateBuilder setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public CoordinateBuilder setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public Coordinate createCoordinate() {
        checkNotNull(lat, "lat cant be null!");
        checkNotNull(lon, "lon cant be null!");

        return new Coordinate(lat, lon);
    }
}