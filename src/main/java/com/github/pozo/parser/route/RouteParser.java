package com.github.pozo.parser.route;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;
import com.github.pozo.model.route.Route;
import com.github.pozo.model.route.RouteBuilder;
import com.github.pozo.parser.DefaultMultipleLineParser;
import com.github.pozo.parser.SearchResultSanitizer;
import com.github.pozo.parser.SingleLineParser;

import java.util.ArrayList;
import java.util.List;

public class RouteParser extends DefaultMultipleLineParser<Route> implements SingleLineParser<Route> {
    static final String LINE = "J";
    private final SearchResultSanitizer searchResultSanitizer;

    public RouteParser() {
        this.searchResultSanitizer = new SearchResultSanitizer();
    }

    @Override
    public List<Route> parseMultipleLines(String rawMessage) {
        rawMessage = searchResultSanitizer.sanitizeMessage(Route.class, rawMessage);

        final List<Route> routes = new ArrayList<Route>();
        final List<String> entries = getEntries(rawMessage);

        for (String entry : entries) {
            final String[] cells = getCells(entry);
            parseRoute(routes, cells);

        }

        return routes;
    }

    private void parseRoute(List<Route> routes, String[] cells) {
        final String rawType = cells[0].trim();

        if (LINE.equals(rawType)) {
            Route route = parseSingleLine(cells);
            routes.add(route);
        }
    }

    @Override
    protected int getCellNumber() {
        return 4;
    }

    @Override
    public Route parseSingleLine(String[] cells) {
        final RouteBuilder routeBuilder = Route.builder();

        final String rawLineId = cells[1].trim();
        final String rawRouteTrackId = cells[2].trim();
        final String rawRouteName = cells[3].trim();

        final LineId lineId = new LineId(rawLineId);
        final int id = Integer.parseInt(rawRouteTrackId);

        routeBuilder.setLineId(lineId)
                .setRouteId(new RouteId(id))
                .setName(rawRouteName);

        return routeBuilder.createRoute();
    }
}
