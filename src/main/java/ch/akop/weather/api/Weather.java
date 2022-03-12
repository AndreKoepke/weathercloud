package ch.akop.weather.api;

import ch.akop.weather.api.light.Light;
import ch.akop.weather.api.rain.Rain;
import ch.akop.weather.api.temperature.Temperature;
import ch.akop.weather.api.wind.Wind;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Weather {

    private ZonedDateTime recordedAt;
    private Temperature outerTemperatur;
    private Wind wind;
    private Light light;
    private Rain rain;

}
