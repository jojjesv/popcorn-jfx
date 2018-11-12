/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.backend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import se.johan.popcorn.utils.Utils;

/**
 * Able to GET/POST to a fixed backend.
 *
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

    public String origin = "http://192.168.0.3:8003/api";

    public JSONObject request(String path, Method method) throws IOException {
        return (JSONObject) request(path, method, null);
    }

    public JSONObject request(String path, Method method, Map<String, Object> body)
            throws IOException {
        return (JSONObject) request(path, method, body, false);
    }

    public JSON request(String path, Method method, Map<String, Object> body,
            boolean expectArray) throws IOException {
        String url = origin + path;

        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest request;

        HttpEntity entity = null;

        switch (method) {
            case POST:
                List<NameValuePair> pairs = new LinkedList<>();
                for (Iterator<Map.Entry<String, Object>> it = body.entrySet().iterator(); it.hasNext();) {
                    Map.Entry<String, Object> arg = it.next();
                    pairs.add(new BasicNameValuePair(arg.getKey(), arg.getValue().toString()));
                }
                entity = new UrlEncodedFormEntity(pairs);
                break;
        }

        switch (method) {
            case GET:
            default:
                request = new HttpGet(url);
                break;

            case POST:
                request = new HttpPost(url);
                ((HttpPost) request).setEntity(entity);
                break;

            case PUT:
                request = new HttpPut(url);
                break;
        }
        System.out.println("url:" + url);

        HttpResponse response = client.execute(request);
        String responseText = new BasicResponseHandler().handleEntity(
                response.getEntity()
        );

        System.out.println("responseText:" + responseText);

        if (expectArray) {
            return JSON.parseArray(responseText);
        }

        return JSON.parseObject(responseText);
    }

    /**
     * Interface for a callback to handle a JSON object response from a request.
     */
    public static interface SuccessJsonCallback {

        public void onSuccess(JSONObject data, int statusCode);
    }

    public static enum Method {
        GET, POST, PUT, DELETE;
    }
}
