package com.github.pozo.parser;

import java.util.List;

interface LineExtractor {
    List<String> extract(String rawMessage, String separator);
}
