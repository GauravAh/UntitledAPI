package core;

import java.net.http.*;
import java.net.URI;

public class RestWrapper {

    public static String sendPostRequest(String url, String jsonPayload) throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        System.out.println(statusCode);
        return response.body();
    }

}
