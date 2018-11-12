/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.add;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import se.johan.popcorn.backend.BackendRequest;
import se.johan.popcorn.backend.BackendRequest.Method;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MovieFormData;
import se.johan.popcorn.scenes.movie.info.MovieInfoService;

/**
 * Stateless service for the add movie form.
 *
 * @author johansvensson
 */
public class AddMovieService {

    private static AddMovieService instance = new AddMovieService();

    public static AddMovieService getInstance() {
        return instance;
    }

    public Movie fetchImdbMovie(String imdbId) throws Exception {
        JSONObject result = BackendRequest.getInstance().request(
                "/movie/imdb/" + imdbId,
                Method.GET,
                null
        );
        
        if ("noMatch".contentEquals(result.getString("result"))) {
            //  Invalid ID
            throw new IllegalArgumentException("Invalid IMdb ID: " + imdbId);
        }
        
        return MovieInfoService.getInstance().getMovieFromJson(
                result.getJSONObject("result")
        );
    }

    /**
     * Performs submit request.
     *
     * @param formData Data to submit.
     * @throws IllegalArgumentException If input validation failed server-side.
     */
    public AddMovieResult submit(MovieFormData formData) throws Exception, IOException, IllegalArgumentException {
        Map<String, Object> params = new HashMap<>();

        JSONObject result = BackendRequest.getInstance().request(
                "/movies",
                Method.POST,
                params
        );
        
        if (result.containsKey("validationError")) {
            //  Validation failed
            throw new IllegalArgumentException(result.getString("validationError"));
        }
        
        if (!result.getBoolean("ok")) {
            //  Unknown error
            throw new Exception("Unknown error");
        }
        
        return new AddMovieResult(result.getIntValue("movie_id"));
    }
    
    public static class AddMovieResult {
        private final int movieId;

        public AddMovieResult(int movieId) {
            this.movieId = movieId;
        }

        public int getMovieId() {
            return movieId;
        }
    }
}
