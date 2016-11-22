package com.github.pozo.parser.nearstation;

import com.github.pozo.model.FoldhelyId;
import com.github.pozo.model.StopId;
import com.github.pozo.model.coordinate.Coordinate;
import com.github.pozo.model.nearstation.NearStation;
import com.github.pozo.model.nearstation.NearStationBuilder;
import com.github.pozo.model.station.Station;
import com.github.pozo.model.station.StationBuilder;
import com.github.pozo.parser.DefaultMultipleLineParser;
import com.github.pozo.parser.ParseException;
import com.github.pozo.parser.SingleLineParser;

import java.util.ArrayList;
import java.util.List;

public class NearStationParser extends DefaultMultipleLineParser<NearStation> implements SingleLineParser<NearStation> {
    @Override
    public List<NearStation> parseMultipleLines(String rawMessage) {
        List<NearStation> nearStations = new ArrayList<NearStation>();
        List<String> entries = getSanitizedRows(rawMessage);

        for (String entry : entries) {
            final String[] cells = getCells(entry);

            final NearStation nearStation = parseSingleLine(cells);

            nearStations.add(nearStation);
        }

        return nearStations;
    }

    @Override
    public NearStation parseSingleLine(String[] cells) {
        final NearStationBuilder nearStationBuilder = NearStation.builder();
        final StationBuilder stationBuilder = Station.builder();

        final String rawName = cells[0].trim();
        final String rawLatitude = cells[1].trim();
        final String rawLongitude = cells[2].trim();
        final String rawStopId = cells[3].trim();

        final String rawDistance = cells[4].trim();
        final String rawXyId = cells[5].trim();

        final Coordinate coordinate = Coordinate.builder()
                .setLat(Double.parseDouble(rawLatitude))
                .setLon(Double.parseDouble(rawLongitude))
                .createCoordinate();

        final StopId stopId = new StopId(Integer.parseInt(rawStopId));

        Station station = stationBuilder.setStopId(stopId)
                .setCoordinate(coordinate)
                .setName(rawName)
                .createStation();

        final double distance = Double.parseDouble(rawDistance);

        return nearStationBuilder.setStation(station)
                .setDistance(distance)
                .setFoldhelyId(new FoldhelyId())
                .createNearStation();
    }

    private List<String> getSanitizedRows(String rawMessage) {
        List<String> rawRows = getEntries(rawMessage);
        List<String> rows = new ArrayList<String>();

        String rowChunk = "";
        for (String rawRow : rawRows) {
            final String[] cells = rawRow.split(DEFAULT_CELL_SEPARATOR);

            if (cells.length == 1) {
                rowChunk = rawRow;
            } else if (cells.length < getCellNumber()) {
                final String fixedRow = rowChunk + rawRow;
                rows.add(fixedRow);
            } else if (cells.length == getCellNumber()) {
                rows.add(rawRow);
            } else {
                throw new ParseException("there must be exactly " + getCellNumber() + " cell in a row!");
            }
        }
        return rows;
    }

    @Override
    protected int getCellNumber() {
        return 6;
    }
}
