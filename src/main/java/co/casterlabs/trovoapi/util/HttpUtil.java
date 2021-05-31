package co.casterlabs.trovoapi.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.web.ApiException;
import co.casterlabs.trovoapi.TrovoApiJava;
import co.casterlabs.trovoapi.TrovoApplicationAuth;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    private static final OkHttpClient client = new OkHttpClient();

    public static JsonObject sendHttpGet(@NonNull String address, @Nullable TrovoApplicationAuth auth) throws IOException, ApiAuthException, ApiException {
        return sendHttp(null, null, address, auth);
    }

    public static JsonObject sendHttp(@NonNull String body, @NonNull String address, @Nullable TrovoApplicationAuth auth) throws IOException, ApiAuthException, ApiException {
        return sendHttp(RequestBody.create(body.getBytes(StandardCharsets.UTF_8)), "POST", address, auth);
    }

    public static JsonObject sendHttp(@Nullable RequestBody body, @Nullable String type, @NonNull String address, @Nullable TrovoApplicationAuth auth) throws IOException, ApiAuthException, ApiException {
        Request.Builder builder = new Request.Builder().url(address);

        if ((body != null) && (type != null)) {
            switch (type.toUpperCase()) {
                case "POST": {
                    builder.post(body);
                    break;
                }

                case "PUT": {
                    builder.put(body);
                    break;
                }

                case "PATCH": {
                    builder.patch(body);
                    break;
                }
            }
        }

        if (auth != null) {
            auth.authenticateRequest(builder);
        }

        builder.addHeader("x-client-type", "api");

        Request request = builder.build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            response.close();

            JsonObject json = TrovoApiJava.GSON.fromJson(responseBody, JsonObject.class);

            if (json.has("status")) {
                int status = json.get("status").getAsInt();

                String reason = String.format("%s: %s", json.get("error").getAsString(), json.get("message").getAsString());

                if (status == 11704) {
                    throw new ApiAuthException(reason);
                } else {
                    throw new ApiException(reason);
                }
            }

            return json;
        }
    }

    public static Response rawHttpGet(@NonNull String address) throws IOException {
        Request.Builder builder = new Request.Builder().url(address);

        builder.addHeader("x-client-type", "api");

        Request request = builder.build();

        Response response = client.newCall(request).execute();

        return response;
    }

    public static Response rawHttpMethod(@NonNull String address, @NonNull String method, @Nullable TrovoApplicationAuth auth) throws IOException {
        Request.Builder builder = new Request.Builder().url(address).method(method, null);

        if (auth != null) {
            auth.authenticateRequest(builder);
        }

        builder.addHeader("x-client-type", "api");

        Request request = builder.build();

        Response response = client.newCall(request).execute();

        return response;
    }

}
