package net.edigest.journalApp.JournalApplication.service;

import net.edigest.journalApp.JournalApplication.api_response.WeatherResponse;
import net.edigest.journalApp.JournalApplication.cache.AppCache;
import net.edigest.journalApp.JournalApplication.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apikey;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;
    @Autowired
    private AppCache appCache;
    public WeatherResponse getWeather(String city){
       WeatherResponse weatherResponse= redisService.get("weather _of_ "+city,WeatherResponse.class);
       if (weatherResponse != null){
           return weatherResponse;
       }else {
           String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apikey);
           ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
           WeatherResponse body = response.getBody();
           if (body != null){
               redisService.set("weather_of_"+city,body,300l);
           }
           return body;
       }

    }
}
