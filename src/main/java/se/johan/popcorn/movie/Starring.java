/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.movie;

/**
 * Represents a starring relation between a movie and an actor.
 * @author Johan Svensson
 */
public class Starring {
    private Actor actor;
    private Movie movie;
    private Role role;

    public Starring(Actor actor, Movie movie, Role role) {
        this.actor = actor;
        this.movie = movie;
        this.role = role;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
