/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.movie;

import java.awt.image.BufferedImage;

/**
 * Represents a preview of a movie.
 *
 * @author Johan Svensson
 */
public class MoviePreview {

    private String title;
    private String imageUri;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public MoviePreview(String title, String imageUri) {
        this.title = title;
        this.imageUri = imageUri;
    }

    public MoviePreview(String title) {
        this.title = title;
    }
}
