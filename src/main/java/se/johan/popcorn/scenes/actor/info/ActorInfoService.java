/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.actor.info;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import se.johan.popcorn.backend.BackendRequest;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.MoviePreview;
import se.johan.popcorn.scenes.main.MainService;
import se.johan.popcorn.utils.Utils;

/**
 *
 * @author johansvensson
 */
public class ActorInfoService {

    private static ActorInfoService instance = new ActorInfoService();

    public static ActorInfoService getInstance() {
        return instance;
    }

    /**
     * Fetches previews for movies associated with this actor.
     *
     * @return
     */
    public MoviePreview[] fetchMoviePreviews(int actorId) throws IOException {
        JSONObject result = (JSONObject) BackendRequest.getInstance().request(
                "/actor/" + actorId,
                BackendRequest.Method.GET,
                null
        );
        
        JSONArray arr = result.getJSONArray("movies");

        return MainService.getInstance().getMoviePreviewsFromJsonArray(arr);
    }
}
