/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.backend;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Able to GET/POST to a fixed backend.
 * @author Johan Svensson
 */
public class BackendRequest {
    private static BackendRequest instance;
    public static BackendRequest getInstance() {
        if (instance == null) {
            instance = new BackendRequest();
        }
        
        return instance;
    }
    
    public String origin = "http://localhost:8085";
    
    public void request(String query, Map<String, Object> body,
            SuccessJsonCallback successJsonCallback) throws IOException {
        String url = String.format("%s?%s", origin, query);
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest request;
        
        if (body != null && !body.isEmpty()) {
            //  Has body, make POST request
            request = new HttpPost(url);
        } else {
            //  No body, make GET request
            request = new HttpGet(url);
        }
        
        HttpResponse response = client.execute(request);
    }
    
    /**
     * Interface for a callback to handle a JSON object response from a request.
     */
    static interface SuccessJsonCallback {
        public void onSuccess(JSONObject data, int statusCode);
    }
}
