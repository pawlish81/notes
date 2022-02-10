package com.pwl.api.config;

import com.pwl.client.ApiClient;
import com.pwl.client.Configuration;

public class NoteApiClientConfig {

    private final static String BASE_URI = "http://localhost:8080";

    public ApiClient getClient(){
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(BASE_URI);
        return defaultClient;
    }
}
