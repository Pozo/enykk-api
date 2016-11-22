package com.github.pozo.model;

public class VehicleId {
    private final int id;

    public VehicleId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleId vehicleId = (VehicleId) o;

        return id == vehicleId.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "VehicleId{" +
                "id=" + id +
                '}';
    }
}
