package com.github.pozo.model;

//kocsiall_id
public class StopId {
    private final int id;

    public StopId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "StopId{" +
                "id=" + id +
                '}';
    }
}
