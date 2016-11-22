package com.github.pozo.model;

public class StationId {
    private final int id;

    public StationId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "StationId{" +
                "id=" + id +
                '}';
    }
}
