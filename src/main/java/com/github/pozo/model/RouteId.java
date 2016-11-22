package com.github.pozo.model;

//nyomvon_id
//Vonalvezetes_id
public class RouteId {
    private final int id;

    public RouteId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteId that = (RouteId) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "RouteId{" +
                "id=" + id +
                '}';
    }
}
