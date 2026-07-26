package net.edigest.journalApp.JournalApplication.api_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse{
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private int dt;
    private int timezone;


    @Data
    public static class Main{
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
    }

    @Data
    public static class Weather{
        private String main;
        private String description;
    }

}

