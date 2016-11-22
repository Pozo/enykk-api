package com.github.pozo.parser;

import java.util.List;

public interface MultipleLineParser<T> {
    List<T> parseMultipleLines(String rawMessage);
}
