package ch.akop.weathercloud;

import ch.akop.weathercloud.light.Light;
import ch.akop.weathercloud.rain.Rain;
import ch.akop.weathercloud.temperature.Temperature;
import ch.akop.weathercloud.wind.Wind;
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
