package com.github.pozo.parser.line;

import com.github.pozo.model.Direction;
import com.github.pozo.model.VehicleId;
import com.github.pozo.model.VehicleType;
import com.github.pozo.model.coordinate.CoordinateBuilder;
import com.github.pozo.model.line.LineInfo;
import com.github.pozo.model.line.LineInfoBuilder;
import com.github.pozo.parser.DefaultMultipleLineParser;
import com.github.pozo.parser.SingleLineParser;

import java.util.ArrayList;
import java.util.List;

public class LineInfoParser extends DefaultMultipleLineParser<LineInfo> implements SingleLineParser<LineInfo> {
    public List<LineInfo> parseMultipleLines(String rawMessage) {
        final List<LineInfo> lineInfos = new ArrayList<LineInfo>();
        final List<String> entries = getEntries(rawMessage);

        for (String entry : entries) {
            final String[] cells = getCells(entry);
            final LineInfo lineInfo = parseSingleLine(cells);

            lineInfos.add(lineInfo);
        }

        return lineInfos;
    }

    @Override
    protected int getCellNumber() {
        return 7;
    }

    @Override
    public LineInfo parseSingleLine(String[] cells) {
        final LineInfoBuilder lineInfoBuilder = LineInfo.builder();

        // final String rawLineId = cells[0].trim(); unused
        final String rawDirection = cells[1].trim();
        // final String rawDestinationName = cells[2].trim(); unused
        final String rawLatitude = cells[3].trim();
        final String rawLongitude = cells[4].trim();
        final String rawVehicleId = cells[5].trim();
        final String rawVehicleType = cells[6].trim();

        if ("O".equals(rawDirection)) {
            lineInfoBuilder.setDirection(Direction.TO);
        } else if ("V".equals(rawDirection)) {
            lineInfoBuilder.setDirection(Direction.BACK);
        }
        final CoordinateBuilder coordinateBuilder = new CoordinateBuilder();
        coordinateBuilder.setLat(Double.parseDouble(rawLatitude));
        coordinateBuilder.setLon(Double.parseDouble(rawLongitude));
        lineInfoBuilder.setCoordinate(coordinateBuilder.createCoordinate());

        final VehicleId vehicleId = new VehicleId(Integer.parseInt(rawVehicleId));
        lineInfoBuilder.setVehicleId(vehicleId);

        if ("1".equals(rawVehicleType)) {
            lineInfoBuilder.setVehicleType(VehicleType.NORMAL);
        } else if ("2".equals(rawVehicleType)) {
            lineInfoBuilder.setVehicleType(VehicleType.LOW_FLOOR);
        }
        return lineInfoBuilder.createLineInfo();
    }
}
