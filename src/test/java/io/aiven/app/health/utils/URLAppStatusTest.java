package io.aiven.app.health.utils;

import io.aiven.app.health.models.HealthStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class URLAppStatusTest {

    //should be improved by passing mock HTTP connection. it fails when google is down
    @Test
    @DisplayName("SHOULD return list of websites WHEN repository returns websites")
    void testSuccess() throws MalformedURLException {
        //given
        URL url = new URL("https://google.com");
        HealthStatus result = URLAppStatus.getStatus(url);

        //verification
        assertEquals(HealthStatus.HEALTHY, result);
    }

    @Test
    @DisplayName("SHOULD return list of websites WHEN repository returns websites")
    void testUnHealthy() throws MalformedURLException {
        URL url = new URL("https://neverwork.website.com");
        HealthStatus result = URLAppStatus.getStatus(url);
        assertEquals(HealthStatus.UNHEALTHY, result);
    }
}
