package org.dinesh;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dinesh.Model.Color;
import org.dinesh.Model.Direction;
import org.dinesh.Model.RequestItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String BASE_URL = "https://challenge.crossmint.io/api";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        RequestItem requestItem = new RequestItem("866a2cbf-c633-4cdb-bc0e-bfa2880d5aea");
        HttpClient client = generateClient();
        challenge1(requestItem, client);
        challenge2(requestItem, client);
    }

    private static void challenge2(RequestItem requestItem, HttpClient client) {
        try {
            String res = get(client);
            JSONObject responseObject = new JSONObject(res);
            JSONArray list = responseObject.getJSONArray("goal");
            int length = list.length();
            for (int i = 0; i < length; i++) {
                JSONArray innerList = list.getJSONArray(i);
                for (int j = 0; j < innerList.length(); j++) {
                    String temp = innerList.getString(j);
                    if (!temp.equals("SPACE")) {
                        requestItem.setRow(i);
                        requestItem.setColumn(j);
                        try {
                            Direction direction = Direction.getByDirectionValue(temp);
                            requestItem.setDirection(direction.getDirectionValue());
                            generateComeths(client, requestItem);
                        } catch (IllegalArgumentException e) {
                            try {
                                Color color = Color.getByColorValue(temp);
                                requestItem.setColor(color.getColorValue());
                                generateSoloons(client, requestItem);
                            } catch (IllegalArgumentException ex) {
                                generatePolyanets(client, requestItem);
                            }
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException | JSONException e) {
            LOGGER.log(Level.SEVERE, "Error occurred: ", e);
            throw new RuntimeException(e);
        }
    }

    private static void challenge1(RequestItem requestItem, HttpClient client) {
        for (int i = 2; i < 9; i++) {
            requestItem.setRow(i);
            requestItem.setColumn(i);
            generatePolyanets(client, requestItem);
            requestItem.setColumn(10 - i);
            generatePolyanets(client, requestItem);
        }
    }

    private static void generateComeths(HttpClient httpClient, RequestItem requestItem) {
        try {
            post(httpClient, "/comeths", requestItem);
            TimeUnit.SECONDS.sleep(1);
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while generating comeths: ", e);
            throw new RuntimeException(e);
        }
    }

    private static void generateSoloons(HttpClient httpClient, RequestItem requestItem) {
        try {
            post(httpClient, "/soloons", requestItem);
            TimeUnit.SECONDS.sleep(1);
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while generating soloons: ", e);
            throw new RuntimeException(e);
        }
    }

    private static void generatePolyanets(HttpClient httpClient, RequestItem requestItem) {
        try {
            post(httpClient, "/polyanets", requestItem);
            TimeUnit.SECONDS.sleep(1);
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while generating polyanets: ", e);
            throw new RuntimeException(e);
        }
    }

    private static HttpClient generateClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    private static void post(HttpClient httpClient, String path, RequestItem requestItem) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(requestItem);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        LOGGER.info("Response code: " + response.statusCode());
        LOGGER.info("Response body: " + response.body());
    }

    private static String get(HttpClient httpClient) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/map/866a2cbf-c633-4cdb-bc0e-bfa2880d5aea/goal"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
