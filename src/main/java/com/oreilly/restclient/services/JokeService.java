package com.oreilly.restclient.services;

import com.oreilly.restclient.json.JokeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JokeService {
    private static final String BASE = "http://api.icndb.com/jokes/random?exclude=[nerdy]";

    // RestTemplate used to call REST services from your application
    private RestTemplate restTemplate;

    @Autowired
    // Injecting RestTemplateBuilder into the JokeService class to configure it
    public JokeService(RestTemplateBuilder builder) {
        // Creates the restTemplate
        restTemplate = builder.build();
    }

    public String getJoke(String first, String last) {
        // Creates a string that passes in Base, first, last where there is "%s"
        String url = String.format("%s&firstName=%s&lastName=%s", BASE, first, last);
        // Uses the restTemplate object to call API by passing in the URL & object class expected in the response
        JokeResponse response = restTemplate.getForObject(url, JokeResponse.class);
        // If the return is null will return an empty string
        String output = "";
        if (response != null) {
            output = response.getValue().getJoke();
        }
        return output;
    }
}
