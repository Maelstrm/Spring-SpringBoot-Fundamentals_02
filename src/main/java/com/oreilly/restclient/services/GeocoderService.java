package com.oreilly.restclient.services;

import com.oreilly.restclient.entities.Site;
import com.oreilly.restclient.json.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeocoderService {
    // Content negotiation = JSON
    private static final String BASE = "https://maps.googleapis.com/maps/api/geocode/json";
    // New property for restTemplate
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(GeocoderService.class);
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

    // Constructor
   @Autowired
    public GeocoderService(RestTemplateBuilder builder) {
        // assign restTemplate
        restTemplate = builder.build();
    }

    private String encodeString(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    public Site getLatLng(String... address) {
        String encodedAddress = Stream.of(address)
                .map(this::encodeString)
                .collect(Collectors.joining(","));
        String url = String.format("%s?address=%s&key=%s", BASE, encodedAddress, KEY);
        Response response = restTemplate.getForObject(url, Response.class);
        Site site = new Site(response.getFormattedAddress(),
                response.getLocation().getLat(),
                response.getLocation().getLng());

        logger.info(site.toString());
        return site;
    }
}
