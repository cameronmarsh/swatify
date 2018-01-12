package edu.swarthmore.cs.cs71.swatify.controllers.spotifyRoutes;

import com.wrapper.spotify.Api;
import edu.swarthmore.cs.cs71.swatify.errors.BaseError;
import edu.swarthmore.cs.cs71.swatify.errors.InternalServerError;
import edu.swarthmore.cs.cs71.swatify.models.User;
import edu.swarthmore.cs.cs71.swatify.util.GsonUtil;
import edu.swarthmore.cs.cs71.swatify.util.SpotifyUtil;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class BaseSpotifyRoute implements Route {
    @Override
    public String handle(Request request, Response response) throws Exception {
        User user = request.session().attribute("user");

        Object obj;
        Api api;
        if (user == null) {
            api = SpotifyUtil.getApi();
        } else {
            api = SpotifyUtil.getApi(user);
        }

        try {
            obj = this.doAction(api, request, response);
        } catch (Exception e) {
            obj = new InternalServerError("Unable to connect to Spotify");
            response.status(((BaseError) obj).getStatus());
        }

        return GsonUtil.toJson(obj);
    }

    protected abstract Object doAction(Api api, Request request, Response response) throws Exception;
}
