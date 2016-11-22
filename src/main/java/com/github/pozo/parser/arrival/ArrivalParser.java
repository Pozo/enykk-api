package com.github.pozo.parser.arrival;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;
import com.github.pozo.model.VehicleType;
import com.github.pozo.model.arrival.Arrival;
import com.github.pozo.model.arrival.ArrivalBuilder;
import com.github.pozo.parser.DefaultMultipleLineParser;
import com.github.pozo.parser.SingleLineParser;

import java.util.ArrayList;
import java.util.List;

public class ArrivalParser extends DefaultMultipleLineParser<Arrival> implements SingleLineParser<Arrival> {
    @Override
    protected int getCellNumber() {
        return 7;
    }

    @Override
    public List<Arrival> parseMultipleLines(String rawMessage) {
        List<Arrival> arrivals = new ArrayList<Arrival>();

        List<String> entries = getEntries(rawMessage);

        for (String entry : entries) {
            String[] cells = getCells(entry);
            Arrival arrival = parseSingleLine(cells);
            arrivals.add(arrival);
        }
        return arrivals;
    }

    @Override
    public Arrival parseSingleLine(String[] cells) {
            /*
    nyomvonal|nyomvon_nev|nyomvonal_id|erkezes|megalloban|menetrendi|alacsony_tipus
1     |Gyirmót - Újváros, Nép u.                         |1003734|13.845166666667|0|0|2
1     |Újváros, Nép u. - Gyirmót                         |1003735|44.711833333333|0|1|1
    nyomvonal|nyomvon_nev|nyomvonal_id|erkezes|megalloban|menetrendi|alacsony_tipus
    11    |Marcalváros - Egyetem - Zempl.u. - Bácsa          |1004328|6.7881666666667|0|0|2
    8     |Révai M. u. - Pinnyéd                             |1003200|9.5215|0|1|1
    14    |Marcalváros - Liget u. Nyár u.                    |1000136|12.2715|0|0|1
    *
    * */
        final ArrivalBuilder builder = Arrival.builder();

        final String rawLineId = cells[0].trim();
        final String rawLineName = cells[1].trim();
        final String rawRouteId = cells[2].trim();
        final String rawExpectedArrival = cells[3].trim();
        final String rawArrived = cells[4].trim();
        final String rawIsInTime = cells[5].trim();
        final String rawVehicleType = cells[6].trim();

        final LineId lineId = new LineId(rawLineId);

        final int id = Integer.parseInt(rawRouteId);
        final RouteId routeId = new RouteId(id);

        final double expectedArrival = Double.parseDouble(rawExpectedArrival);

        boolean arrived = false;
        if ("1".equals(rawArrived)) {
            arrived = true;
        }

        builder.setLineId(lineId)
                .setLineName(rawLineName)
                .setRouteId(routeId)
                .setExpectedArrival(expectedArrival)
                .setArrived(arrived)
                .setVehicleType(VehicleType.NORMAL);


        return builder.createArrival();
    }
}
