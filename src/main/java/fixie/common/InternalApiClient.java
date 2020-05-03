package fixie.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class InternalApiClient {

    private final HttpClient httpClient;

    public InternalApiClient() {
        this.httpClient = HttpClients.custom().build();
    }

    private JSONObject getResponseBody(HttpResponse response) throws IOException, JSONException {
        String content = EntityUtils.toString(response.getEntity());
        JSONObject responseBody = new JSONObject(content);

        return responseBody;
    }

    public JSONObject decodeTokenRequest(String token) {
        String url = "https://localhost:8001/decodeToken";

        HttpUriRequest request = RequestBuilder.get()
                .setUri(url)
                .setHeader("token", token)
                .build();

        JSONObject responseBody = null;

        try {
            HttpResponse response = this.httpClient.execute(request);
            responseBody = this.getResponseBody(response);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return  responseBody;
    }
}
