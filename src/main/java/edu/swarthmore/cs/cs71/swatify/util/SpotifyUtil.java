package edu.swarthmore.cs.cs71.swatify.util;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import edu.swarthmore.cs.cs71.swatify.models.User;

import java.util.Arrays;
import java.util.List;


public class SpotifyUtil {
    private static final String redirectURI = "http://localhost:3000/callback";
    private static final String state = "iPreventCSRFAttacks";
    private static final List<String> scopes = Arrays.asList("playlist-modify-public");
    private static String clientAccessToken = null;

    public SpotifyUtil() {
    }

    /**
     * @return An API object with application-level authentication (unable to access a specific user's account).
     */
    public static Api getApi() {
        final String clientId = Secrets.getSpotifyClientId();
        final String clientSecret = Secrets.getSpotifyClientSecret();

        final Api api = Api.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();

        if (clientAccessToken == null) {
            final ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();
            final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

            Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
                @Override
                public void onSuccess(ClientCredentials clientCredentials) {
                    api.setAccessToken(clientCredentials.getAccessToken());
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("An error occurred while getting the access token. This is probably caused by the client id or client secret is invalid.");
                }
            });
        }
        else {
            api.setAccessToken(clientAccessToken);
        }

        return api;
    }

    /**
     * @param user The user for whom the API object will have access, or null if the API object is being set up for access for a user.
     * @return An API object with user-level authentication (able to access a specific user's account), or with the potential to access a user's account.
     */
    public static Api getApi(User user) {
        if (user == null) {
            final String clientId = Secrets.getSpotifyClientId();
            final String clientSecret = Secrets.getSpotifyClientSecret();

            return Api.builder()
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .redirectURI(redirectURI)
                    .build();
        } else {
            return Api.builder()
                    .accessToken(user.getSpotifyAccessToken())
                    .refreshToken(user.getSpotifyRefreshToken())
                    .build();
        }
    }

    public static String getAuthorizeUrl() {
        return getApi(null).createAuthorizeURL(scopes, state);
    }
}



