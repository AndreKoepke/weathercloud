package ch.akop.weathercloud.scraper.weathercloud;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CustomDeserializerTimestampPairType extends JsonDeserializer<TimestampPair> {


    @Override
    public TimestampPair deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // begin array
        p.nextToken();
        var timeStamp = Instant.ofEpochSecond(p.readValueAs(Integer.class));
        var value = p.readValueAs(BigDecimal.class);
        // end array
        p.nextToken();

        return new TimestampPair(LocalDateTime.ofInstant(timeStamp, ZoneId.systemDefault()), value);
    }
}
