package com.github.pozo.parser;

import com.github.pozo.model.route.Route;
import com.github.pozo.model.station.StationInfo;

public class SearchResultSanitizer {
    public String sanitizeMessage(Class clazz, String rawMessage) {
        rawMessage = rawMessage.replaceAll("\n", "");
        rawMessage = rawMessage.replaceAll("J\\|", "\nJ\\|");
        rawMessage = rawMessage.replaceAll("M\\|", "\nM\\|");

        // every line staring with M and the newline
        final String stationLinePattern = getPattern(clazz);
        rawMessage = rawMessage.replaceAll(stationLinePattern, "");

        return rawMessage; // end with | so we have to do this
    }

    private String getPattern(Class clazz) {
        if (clazz.equals(StationInfo.class)) {
            return "(?m)^J.*$(\\r?\\n|\\r)?";
        } else if (clazz.equals(Route.class)) {
            return "(?m)^M.*$(\\r?\\n|\\r)?";
        } else {
            return "";
        }
    }
}
