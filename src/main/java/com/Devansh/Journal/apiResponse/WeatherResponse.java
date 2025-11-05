package com.Devansh.Journal.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Data
public class WeatherResponse {

    public Current current;
@Data
    public class Current{

        public int temperature;

        @JsonProperty("weather_descriptions")
        public ArrayList<String> weatherDescriptions;

        public int feelslike;

    }


}
