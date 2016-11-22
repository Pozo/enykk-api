package com.github.pozo.parser;

import java.util.Arrays;
import java.util.List;

public class DefaultLineLineExtractor implements LineExtractor {
    @Override
    public List<String> extract(String rawMessage, String separator) {
        final String[] headerAndBody = rawMessage.split(separator);

        checkFormat(separator, headerAndBody);
        final String[] entries = Arrays.copyOfRange(headerAndBody, 1, headerAndBody.length);

        return Arrays.asList(entries);
    }

    private void checkFormat(String separator, String[] headerAndBody) {
        if (headerAndBody.length == 0) {
            throw new ParseException("rawMessage format is incorrect, there is no header separated with " + separator);
        }
    }
}
