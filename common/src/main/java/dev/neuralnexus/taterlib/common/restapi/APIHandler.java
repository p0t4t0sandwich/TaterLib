package dev.neuralnexus.taterlib.common.restapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class APIHandler {
    /**
     * General API call method.
     * @param endpoint The endpoint to call.
     * @param requestMethod The request method to use.
     * @param data The data to send.
     */
    public Map<?,?> APICall(String endpoint, String requestMethod, Map<?,?> data) {
        try {
            Gson gson = new GsonBuilder().create();
            String data_json = gson.toJson(data);

            URL url = new URL(endpoint);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", "tater-api");
            con.setConnectTimeout(5000);
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(data_json);
            osw.flush();
            osw.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            return gson.fromJson(br.readLine(), Map.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * General API call method.
     * @param endpoint The endpoint to call.
     * @param requestMethod The request method to use.
     */
    public Map<?,?> APICall(String endpoint, String requestMethod) {
        try {
            Gson gson = new GsonBuilder().create();

            URL url = new URL(endpoint);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", "tater-api");
            con.setConnectTimeout(5000);
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.flush();
            osw.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            return gson.fromJson(br.readLine(), Map.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
