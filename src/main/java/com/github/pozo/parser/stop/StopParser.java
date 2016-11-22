package com.github.pozo.parser.stop;

import com.github.pozo.model.StopId;
import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.stop.Stop;
import com.github.pozo.model.stop.StopBuilder;
import com.github.pozo.parser.DefaultMultipleLineParser;
import com.github.pozo.parser.SingleLineParser;

import java.util.ArrayList;
import java.util.List;

public class StopParser extends DefaultMultipleLineParser<Stop> implements SingleLineParser<Stop> {
    @Override
    protected int getCellNumber() {
        return 4;
    }

    @Override
    public List<Stop> parseMultipleLines(String row) {
        List<Stop> stops = new ArrayList<Stop>();
        List<String> entries = getEntries(row);

        for (String entry : entries) {
            final String[] cells = getCells(entry);

            Stop stop = parseSingleLine(cells);
            stops.add(stop);
        }

        return stops;
    }
    @Override
    public Stop parseSingleLine(String[] cells) {
        final StopBuilder builder = Stop.builder();
        //computed|kocsiall_gps_x|kocsiall_gps_y|kocsiall_id
        final String rawStopName = cells[0].trim();
        final String rawLatitude = cells[1].trim();
        final String rawLongitude = cells[2].trim();
        final String rawStopId = cells[3].trim();

        final Coordinate coordinate = Coordinate.builder()
                .setLat(Double.parseDouble(rawLatitude))
                .setLon(Double.parseDouble(rawLongitude))
                .createCoordinate();

        final int stopIdNumeric = Integer.parseInt(rawStopId);
        final StopId stopId = new StopId(stopIdNumeric);

        builder.setCoordinate(coordinate)
                .setStopId(stopId)
                .setName(rawStopName);

        return builder.createStop();
    }
}
