package softwaredesign;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
       BASE_URL = "https://squidgameserver.herokuapp.com";
    }

    private HttpRequest createRequest(String url, Methods method) throws Exception {
        if (url.equals("")) {
            throw new Exception("No url provided");
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
        }

        return request;
    }

    protected  MainResponse get(String url, Class mr) throws Exception {
        final String resBody = client.send(this.createRequest(url, Methods.GET), HttpResponse.BodyHandlers.ofString()).body();
        return (MainResponse) g.fromJson(resBody, mr);
    }

}
