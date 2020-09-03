package fixie.common;

import fixie.common.exception.UnauthorizedException;
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

        return new JSONObject(content);
    }

    public JSONObject verifyToken(String token) {
        String url = GlobalConfig.AUTHORIZATION_SERVICE_URL + "/verifyToken";

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

    public String getRoleFromToken(String token) throws UnauthorizedException {
        JSONObject decodedToken = this.verifyToken(token);

        if (decodedToken == null) {
            throw new UnauthorizedException();
        }

        String role;
        try {
            role = decodedToken.getString("role");
        } catch (JSONException e) {
            throw new UnauthorizedException();
        }

        return role;
    }

    public String getUsernameFromToken(String token) throws UnauthorizedException {
        JSONObject decodedToken = this.verifyToken(token);

        if (decodedToken == null) {
            throw new UnauthorizedException();
        }

        String username;
        try {
            username = decodedToken.getString("username");
        } catch (JSONException e) {
            throw new UnauthorizedException();
        }

        return username;
    }

    public Long getIdFromToken(String token) throws UnauthorizedException {
        JSONObject decodedToken = this.verifyToken(token);

        if (decodedToken == null) {
            throw new UnauthorizedException();
        }

        Long userId;
        try {
            userId = Long.parseLong(decodedToken.getString("id"));
        } catch (JSONException e) {
            throw new UnauthorizedException();
        }

        return userId;
    }
}
