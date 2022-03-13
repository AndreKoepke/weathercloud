package ch.akop.weathercloud.scraper.weathercloud;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
class ScraperTest {

    // run manually
    //@Test
    void manual_test() {
        var testee = new Scraper();

        var result = testee.scrape("7003523537");

    }

    //@Test
    void manual_scheduler_test() throws InterruptedException {
        var testee = new Scraper();

        new Scraper().scrape$("7003523537", Duration.of(5, ChronoUnit.MINUTES))
                .subscribe(weather -> log.info("In Oensingen, we had {}", weather.getOuterTemperatur()));

        Thread.sleep(60000);
    }

}
