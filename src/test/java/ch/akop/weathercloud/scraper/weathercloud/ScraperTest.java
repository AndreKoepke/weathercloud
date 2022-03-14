package ch.akop.weathercloud.scraper.weathercloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class ScraperTest {

    @Test
    void manual_test() {
        var testee = new Scraper();
        var result = testee.scrape("7003523537");
        assertNotNull(result);
    }

    @Test
    @Disabled("Run manually")
    void manual_scheduler_test() throws InterruptedException {
        var testee = new Scraper();

        testee.scrape$("7003523537", Duration.of(5, ChronoUnit.MINUTES))
                .subscribe(weather -> log.info("In Oensingen, we had {}", weather.getOuterTemperatur()));

        Thread.sleep(60000);
    }

}
