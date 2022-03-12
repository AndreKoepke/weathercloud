package ch.akop.weather.api.scraper.weathercloud;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonDeserialize(using = CustomDeserializerTimestampPairType.class)
public record TimestampPair(LocalDateTime timestamp, BigDecimal value) {

}
