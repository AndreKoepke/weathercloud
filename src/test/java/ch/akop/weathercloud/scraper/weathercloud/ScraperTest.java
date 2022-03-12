package ch.akop.weathercloud.scraper.weathercloud;

import org.junit.jupiter.api.Test;

class ScraperTest {

    // run manually
    @Test
    void manual_test() {
        var testee = new Scraper();

        var result = testee.scrape("7003523537");


    }

}
