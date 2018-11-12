/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.net.URLEncoder;
import se.johan.popcorn.backend.BackendRequest;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MoviePreview;

/**
 * Service for the main
 *
 * @author johansvensson
 */
public class MainService {

    private static MainService instance = new MainService();

    public static MainService getInstance() {
        return instance;
    }

    /**
     * Fetches movie previews.
     *
     * @return
     * @throws IOException Likely backend communication error.
     */
    public MoviePreview[] fetchMoviePreviews() throws IOException {
        return fetchMoviePreviews(null, null);
    }

    /**
     * Fetches movie previews with a search query.
     *
     * @return
     * @throws IOException Likely backend communication error.
     */
    public MoviePreview[] fetchMoviePreviews(String search, MovieSearchQuery.Scope scope) throws IOException {
        String query = "";

        if (search != null && search.length() > 0 && scope != null) {
            query = String.format(
                    "?q=%s&scope=%s",
                    URLEncoder.encode(search, "UTF8"),
                    scope.toString()
            );
        }

        JSONArray arr = (JSONArray) BackendRequest.getInstance().request(
                "/movies" + query,
                BackendRequest.Method.GET,
                null,
                true
        );

        return getMoviePreviewsFromJsonArray(arr);
    }

    public MoviePreview[] getMoviePreviewsFromJsonArray(JSONArray arr) {
        MoviePreview[] result = new MoviePreview[arr.size()];
        for (int i = 0, n = result.length; i < n; i++) {
            JSONObject obj = arr.getJSONObject(i);
            result[i] = new MoviePreview(
                    obj.getIntValue("id"),
                    obj.getString("title"),
                    obj.getString("pictureUri")
            );
        }

        return result;
    }
}
