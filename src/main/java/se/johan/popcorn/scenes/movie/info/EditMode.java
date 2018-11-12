/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.info;

import java.util.HashMap;
import java.util.Map;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.utils.Utils;

/**
 * Handles editing a separate copy of a Movie instance.
 *
 * @author johansvensson
 */
public class EditMode {

    private Movie original;

    public Movie getOriginal() {
        return original;
    }

    public void setOriginal(Movie original) {
        this.original = original;
    }

    /**
     * This does not return a complete difference between two Movie instances,
     * it is only used to return the difference in the context of updating an
     * existing movie.
     *
     * @return A map with the differences between the original Movie instance
     * and another instance.
     */
    public Map<String, Object> getUpdateableDiff(Movie other) {
        Map<String, Object> diff = new HashMap<>();

        if (!Utils.safeString(original.getTitle()).equals(other.getTitle())) {
            diff.put("title", other.getTitle());
        }
        if (!Utils.safeString(original.getPlot()).equals(other.getPlot())) {
            diff.put("plot", other.getPlot());
        }
        if (!Utils.safeString(original.getPossessor()).equals(other.getPossessor())) {
            diff.put("possessor", other.getPossessor());
        }
        if (!Utils.safeString(original.getAgeRating()).equals(other.getAgeRating())) {
            diff.put("ageRating", other.getAgeRating());
        }
        if (original.getScore() != other.getScore()) {
            diff.put("score", other.getScore());
        }
        if (original.getRuntime() != other.getRuntime()) {
            diff.put("runtime", other.getRuntime());
        }
        if (original.getYear() != other.getYear()) {
            diff.put("year", other.getYear());
        }

        return diff;
    }
}
