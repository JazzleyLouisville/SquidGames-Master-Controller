package softwaredesign;

import com.google.gson.Gson;
import org.json.JSONObject;
import softwaredesign.constants.NetworkingConstants;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;

public class API {
    HttpClient client;
    Gson g;
    final String BASE_URL;
    static enum Methods {
        GET,
        POST,
        HEAD,
        DELETE,
        PUT
    }
    API () {
       client = HttpClient.newHttpClient();
       g = new Gson();
       BASE_URL = NetworkingConstants.IS_LOCAL ? NetworkingConstants.LOCALHOST_SERVER : NetworkingConstants.BASE_URL;
    }

    private HttpRequest createRequest(String url, Methods method, HashMap... reqBody) throws Exception {
        if (url.equals("")) {
            throw new Exception(NetworkingConstants.NO_URL);
        }
        HttpRequest request = null;
        switch (method) {
            case GET:
                request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .version(HttpClient.Version.HTTP_2)
                    .GET()
                    .build();
                break;
            case POST:
                System.out.println(g.toJson(reqBody));
                request = HttpRequest.newBuilder()
                        .uri(new URI(url))
                        .version(HttpClient.Version.HTTP_2)
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(g.toJson(reqBody)))
                        .build();
                break;
            case DELETE:
                System.out.println(g.toJson(reqBody));
                request = HttpRequest.newBuilder()
                        .uri(new URI(url))
                        .version(HttpClient.Version.HTTP_2)
                        .header("Content-Type", "application/json")
                        .DELETE()
                        .build();
                break;
        }

        return request;
    }

    protected <T> T get(String path, T type) throws Exception {
        final String resBody = client.send(this.createRequest(BASE_URL + path, Methods.GET), HttpResponse.BodyHandlers.ofString()).body();
        return (T) g.fromJson(resBody, type.getClass());
    }

    protected <T> T post(String path, HashMap body, T type) throws Exception {
        final String resBody = client.send(this.createRequest(BASE_URL + path, Methods.POST, body), HttpResponse.BodyHandlers.ofString()).body();
        return (T) g.fromJson(resBody, type.getClass());
    }

    protected <T> T delete(String path, T type) throws Exception {
        final String resBody = client.send(this.createRequest(BASE_URL + path, Methods.DELETE), HttpResponse.BodyHandlers.ofString()).body();
        return (T) g.fromJson(resBody, type.getClass());
    }

}
