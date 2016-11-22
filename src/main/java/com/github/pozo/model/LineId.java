package com.github.pozo.model;

//nyomvonal
public class LineId {
    public String getId() {
        return id;
    }

    private final String id;

    public LineId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineId lineId = (LineId) o;

        return id != null ? id.equals(lineId.id) : lineId.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LineId{" +
                "id='" + id + '\'' +
                '}';
    }
}
