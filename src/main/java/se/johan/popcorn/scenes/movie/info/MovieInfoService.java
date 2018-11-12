/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.info;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.Arrays;
import se.johan.popcorn.backend.BackendRequest;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MoviePreview;

/**
 *
 * @author johansvensson
 */
public class MovieInfoService {

    private static MovieInfoService instance = new MovieInfoService();

    public static MovieInfoService getInstance() {
        return instance;
    }

    public Movie fetchMovie(int id) throws IOException {
        JSONObject result = BackendRequest.getInstance().request(
                "/movie/" + id,
                BackendRequest.Method.GET,
                null
        );

        try {
            return getMovieFromJson(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Movie getMovieFromJson(JSONObject obj) {

        JSONArray actorsJson = obj.getJSONArray("cast");
        Actor[] actors = new Actor[actorsJson.size()];

        for (int i = 0, n = actors.length; i < n; i++) {
            JSONObject actorObj = actorsJson.getJSONObject(i);
            actors[i] = new Actor(
                    actorObj.getIntValue("id"),
                    actorObj.getString("name"),
                    actorObj.getString("pictureUri")
            );
        }

        Movie m = new Movie();

        m.setId(obj.getIntValue("id"));
        m.setTitle(obj.getString("title"));
        m.setYear(obj.getIntValue("year"));
        JSONArray categories = obj.getJSONArray("categories");
        m.setCategories(Arrays.asList(categories.toArray(new String[categories.size()])));
        m.setScore(obj.getDoubleValue("score"));
        m.setAgeRating(obj.getString("ageRating"));
        m.setRuntime(obj.getIntValue("runtime"));
        m.setPlot(obj.getString("plot"));
        m.setPictureUri(obj.getString("pictureUri"));
        m.setActors(Arrays.asList(actors));

        return m;
    }

    /**
     * Saves the changes made to a movie.
     */
    public void saveChanges(Movie m, EditMode em) throws IOException {
        BackendRequest.getInstance().request(
                "/movie/" + m.getId(),
                BackendRequest.Method.PUT,
                em.getUpdateableDiff(m)
        );
    }

    /**
     * Requests to delete a movie.
     *
     * @param movieId ID of movie to delete.
     */
    public void deleteMovie(int movieId) throws IOException {
        BackendRequest.getInstance().request(
                "/movie/" + movieId, BackendRequest.Method.DELETE
        );
    }
}
