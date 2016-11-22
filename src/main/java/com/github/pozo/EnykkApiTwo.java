package com.github.pozo;

import com.github.pozo.http.HttpContentGrabber;
import com.github.pozo.model.LineId;
import com.github.pozo.model.line.LineInfo;
import com.github.pozo.parser.line.LineInfoParser;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import java.util.List;

public class EnykkApiTwo {
    private static final String BASE_URL = "http://webgyor.kvrt.hu/csaoajax/sql-cs.php";

    public static void main(String[] args) {
        EnykkApiTwo api = new EnykkApiTwo();

        final Optional<List<LineInfo>> lineInfo = api.getLineInfo(new LineId("GY14"));

        if (lineInfo.isPresent()) {
            for (LineInfo info : lineInfo.get()) {
                System.out.println("info = " + info);
            }
        }

    }

    public Optional<List<LineInfo>> getLineInfo(LineId lineId) {
        final LineId line = Preconditions.checkNotNull(lineId);

        try {
            final String lineInfoRawUrl = getLineInfoUrl(line);
            final String content = HttpContentGrabber.get(lineInfoRawUrl);

            LineInfoParser lineInfoParser = new LineInfoParser();
            List<LineInfo> lineInfo = lineInfoParser.parseMultipleLines(content);
            return Optional.of(lineInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.absent();
        }
    }

    private String getLineInfoUrl(LineId line) {
        StringBuilder builder = new StringBuilder(BASE_URL)
                .append("?vonal_irany=")
                .append(line.getId())
                .append(":O,V&sep=%3Cbr/%3E");

        return builder.toString();
    }
}
