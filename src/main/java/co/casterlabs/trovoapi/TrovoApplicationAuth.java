package co.casterlabs.trovoapi;

import co.casterlabs.apiutil.auth.ApiAuthException;
import co.casterlabs.apiutil.auth.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import okhttp3.Request.Builder;

@Getter
@AllArgsConstructor
public class TrovoApplicationAuth implements AuthProvider {
    protected @NonNull String clientId;

    @Override
    public void authenticateRequest(@NonNull Builder request) {
        request.header("Client-ID", this.clientId);
    }

    @Override
    public void refresh() throws ApiAuthException {
        // NOP
    }

}
