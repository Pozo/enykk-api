package com.github.pozo.parser;

public interface SingleLineParser<T> {
    T parseSingleLine(String[] cells);
}
