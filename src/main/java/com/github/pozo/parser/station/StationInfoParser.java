package com.github.pozo.parser.station;

import com.github.pozo.model.StationId;
import com.github.pozo.model.station.StationInfo;
import com.github.pozo.parser.DefaultMultipleLineParser;
import com.github.pozo.parser.SearchResultSanitizer;
import com.github.pozo.parser.SingleLineParser;

import java.util.ArrayList;
import java.util.List;

public class StationInfoParser extends DefaultMultipleLineParser<StationInfo> implements SingleLineParser<StationInfo> {
    static final String STATION = "M";

    private final SearchResultSanitizer searchResultSanitizer;

    public StationInfoParser() {
        this.searchResultSanitizer = new SearchResultSanitizer();
    }

    @Override
    public List<StationInfo> parseMultipleLines(String rawMessage) {
        final List<StationInfo> stations = new ArrayList<StationInfo>();
        rawMessage = searchResultSanitizer.sanitizeMessage(StationInfo.class, rawMessage);

        final List<String> entries = getEntries(rawMessage);

        for (String entry : entries) {
            final String[] cells = getCells(entry);

            final String rawType = cells[0].trim();

            if (STATION.equals(rawType)) {
                StationInfo stationInfo = parseSingleLine(cells);
                stations.add(stationInfo);
            }
        }

        return stations;
    }

    @Override
    public StationInfo parseSingleLine(String[] cells) {
        final String rawType = cells[0].trim();

        final String rawLineId = cells[1].trim();
        final String rawRouteTrackId = cells[2].trim();

        final int id = Integer.parseInt(rawRouteTrackId);
        final StationId stationId = new StationId(id);
        return new StationInfo(rawLineId, stationId);
    }

    @Override
    protected int getCellNumber() {
        return 4;
    }


}
