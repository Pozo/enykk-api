package com.github.pozo.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpContentGrabber {

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String UTF_8 = "UTF-8";

    public static String get(String rawUrl) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(rawUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(GET);

        return getContent(result, conn);
    }

    public static String post(String rawUrl, String data) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(rawUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(POST);
        conn.setDoOutput(true);
        byte[] outputInBytes = data.getBytes(UTF_8);
        OutputStream os = conn.getOutputStream();
        os.write(outputInBytes);
        os.close();

        return getContent(result, conn);
    }

    private static String getContent(StringBuilder result, HttpURLConnection conn) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
            result.append('\n');
        }
        rd.close();
        return result.toString();
    }
}
