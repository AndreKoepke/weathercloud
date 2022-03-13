# Java-Library to fetch Weathercloud.net data

Small and simple. Just include the lib via Maven/Gradle 
(don't forget to add "https://maven.pkg.github.com/AndreKoepke/weathercloud" as repository)

### maven

```xml

<dependency>
    <groupId>ch.akop</groupId>
    <artifactId>weathercloud</artifactId>
    <version>0.0.4</version>
</dependency> 
```

### gradle

```groovy
implementation 'ch.akop:weathercloud:0.0.4' 
```

## Usage

```java
// poll data periodically
new Scraper().scrape$("7003523537", Duration.of(5, ChronoUnit.MINUTES))
        .subscribe(weather -> log.info("In Oensingen, we had {}.", weather.getOuterTemperature()));
// example log-output: "In Oensingen, we had 15.4 °C."

// single result
var result = new Scraper().scrape("7003523537");
```

![Example response](doc/resultImage.png)
