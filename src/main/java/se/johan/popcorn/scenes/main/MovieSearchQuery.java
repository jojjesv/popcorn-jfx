/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

/**
 * Query when searching for movies.
 * @author johansvensson
 */
public class MovieSearchQuery {
    public Scope scope;
    public String query;

    public MovieSearchQuery(Scope scope, String query) {
        this.scope = scope;
        this.query = query;
    }
    
    public enum Scope {
        MOVIES("movies"),
        CAST("cast"),
        CATEGORY("category");
        
        private String strVal;
        
        private Scope(String strVal) {
            this.strVal = strVal;
        }

        @Override
        public String toString() {
            return strVal;
        }
    }
}
