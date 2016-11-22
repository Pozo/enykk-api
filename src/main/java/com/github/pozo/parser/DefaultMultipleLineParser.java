package com.github.pozo.parser;

import java.util.List;

public abstract class DefaultMultipleLineParser<T> implements MultipleLineParser<T> {
    protected String DEFAULT_HEADER_BODY_SEPARATOR = "\n";
    protected String DEFAULT_CELL_SEPARATOR = "\\|";

    private final DefaultLineLineExtractor defaultLineExtractor;

    public DefaultMultipleLineParser() {
        this.defaultLineExtractor = new DefaultLineLineExtractor();
    }

    protected String getLineSeparator() {
        return DEFAULT_HEADER_BODY_SEPARATOR;
    }

    protected List<String> getEntries(String rawMessage) {
        return defaultLineExtractor.extract(rawMessage, getLineSeparator());
    }

    protected String getCellSeparator() {
        return DEFAULT_CELL_SEPARATOR;
    }

    private void checkCells(String[] cells) {
        if (cells == null || cells.length != getCellNumber()) {
            throw new ParseException(String.format("format is incorrect, there must be exactly %s cell within a row", getCellNumber()));
        }
    }

    protected String[] getCells(String entry) {
        final String[] cells = entry.split(getCellSeparator());
        checkCells(cells);
        return cells;
    }

    protected abstract int getCellNumber();
}
