package com.github.pozo.parser.station;

import com.github.pozo.model.StopId;
import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.station.Station;
import com.github.pozo.model.station.StationBuilder;
import com.github.pozo.parser.DefaultLineLineExtractor;
import com.github.pozo.parser.DefaultMultipleLineParser;
import com.github.pozo.parser.SingleLineParser;

import java.util.ArrayList;
import java.util.List;

public class StationParser extends DefaultMultipleLineParser<Station> implements SingleLineParser<Station> {
    private static final String HEADER_BODY_SEPARATOR = "\n";
    private static final String CELL_SEPARATOR = "\\|";

    private final DefaultLineLineExtractor defaultLineExtractor;

    public StationParser() {
        this.defaultLineExtractor = new DefaultLineLineExtractor();
    }

    @Override
    protected int getCellNumber() {
        return 4;
    }

    @Override
    public List<Station> parseMultipleLines(String rawMessage) {
        final List<Station> stations = new ArrayList<Station>();
        final List<String> entries = defaultLineExtractor.extract(rawMessage, HEADER_BODY_SEPARATOR);

        for (String entry : entries) {
            final String[] cells = entry.split(CELL_SEPARATOR);

            Station station = parseSingleLine(cells);
            stations.add(station);
        }
        return stations;
    }

    @Override
    public Station parseSingleLine(String[] cells) {
        StationBuilder builder = Station.builder();

        final String rawName = cells[0].trim();
        final String rawLatitude = cells[1].trim();
        final String rawLongitude = cells[2].trim();
        final String rawStopId = cells[3].trim();

        final Coordinate coordinate = Coordinate.builder()
                .setLat(Double.parseDouble(rawLatitude))
                .setLon(Double.parseDouble(rawLongitude))
                .createCoordinate();

        final StopId stopId = new StopId(Integer.parseInt(rawStopId));

        builder.setName(rawName)
                .setCoordinate(coordinate)
                .setStopId(stopId);

        return builder.createStation();
    }
}
