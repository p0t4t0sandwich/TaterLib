package dev.neuralnexus.taterlib.restapi;

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
    private final String baseUri;
    private final String dataSource;
    private final String authToken;

    /**
     * Creates a new BNGAPIHandler instance.
     *
     * @param baseURI The base URI of the API.
     */
    public APIHandler(String baseURI) {
        this.baseUri = baseURI;
        this.authToken = null;

        if (baseURI.charAt(baseURI.length() - 1) == '/') {
            this.dataSource = this.baseUri;
        } else {
            this.dataSource = this.baseUri + "/";
        }
    }

    /**
     * Creates a new BNGAPIHandler instance.
     *
     * @param baseURI The base URI of the API.
     * @param authToken The auth token to use.
     */
    public APIHandler(String baseURI, String authToken) {
        this.baseUri = baseURI;
        this.authToken = authToken;

        if (baseURI.charAt(baseURI.length() - 1) == '/') {
            this.dataSource = this.baseUri;
        } else {
            this.dataSource = this.baseUri + "/";
        }
    }

    /**
     * General API call method.
     *
     * @param endpoint The endpoint to call.
     * @param requestMethod The request method to use.
     * @param returnClass The class to use when serializing the response.
     */
    public Object APICall(String endpoint, String requestMethod, Class<?> returnClass) {
        try {
            URL url = new URL(this.dataSource + endpoint);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            // If authToken is not null, set the Authorization header.
            if (this.authToken != null) {
                con.setRequestProperty("Authorization", "Bearer " + this.authToken);
            }

            con.setRequestProperty("User-Agent", "bee-name-generator-plugin");
            con.setConnectTimeout(5000);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            Gson gson = new GsonBuilder().create();
            return gson.fromJson(br.readLine(), returnClass);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * General API call method.
     *
     * @param endpoint The endpoint to call.
     * @param requestMethod The request method to use.
     * @param data The data to send.
     */
    public Object APICall(
            String endpoint, String requestMethod, Class<?> returnClass, Map<?, ?> data) {
        try {
            URL url = new URL(this.dataSource + endpoint);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");

            // If authToken is not null, set the Authorization header.
            if (this.authToken != null) {
                con.setRequestProperty("Authorization", "Bearer " + this.authToken);
            }

            con.setRequestProperty("User-Agent", "taterlib");
            con.setConnectTimeout(5000);

            Gson gson = new GsonBuilder().create();
            String data_json = gson.toJson(data);

            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(data_json);
            osw.flush();
            osw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            return gson.fromJson(br.readLine(), returnClass);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * General API call method.
     *
     * @param endpoint The endpoint to call.
     * @param requestMethod The request method to use.
     */
    public Map<?, ?> APICall(String endpoint, String requestMethod) {
        return (Map<?, ?>) this.APICall(endpoint, requestMethod, Map.class);
    }

    /**
     * General API call method.
     *
     * @param endpoint The endpoint to call.
     * @param requestMethod The request method to use.
     * @param data The data to send.
     */
    public Map<?, ?> APICall(String endpoint, String requestMethod, Map<?, ?> data) {
        return (Map<?, ?>) this.APICall(endpoint, requestMethod, Map.class, data);
    }
}
