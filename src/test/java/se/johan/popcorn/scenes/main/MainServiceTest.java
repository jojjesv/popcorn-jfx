/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

import com.alibaba.fastjson.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.johan.popcorn.movie.MoviePreview;

/**
 *
 * @author johansvensson
 */
public class MainServiceTest {
    
    public MainServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class MainService.
     */
    @org.junit.Test
    public void testFetchMoviePreviews() throws Exception {
        System.out.println("testFetchMovies");
        MoviePreview[] previews = MainService.getInstance().fetchMoviePreviews();
    
        assertTrue("There are some movie previews", previews.length > 0);
    }
}
