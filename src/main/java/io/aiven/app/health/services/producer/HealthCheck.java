package io.aiven.app.health.services.producer;

import io.aiven.app.health.exception.DatabaseGenericException;
import io.aiven.app.health.exception.InvalidURLException;
import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.models.Website;
import io.aiven.app.health.utils.URLAppStatus;

import java.net.MalformedURLException;
import java.sql.SQLException;

public class HealthCheck {
    private WebsitesService websitesService;
    private HealthEventProducer healthEventProducer;

    public HealthCheck(WebsitesService websitesService,
                       HealthEventProducer healthEventProducer) {
        this.websitesService = websitesService;
        this.healthEventProducer = healthEventProducer;
    }


    public void checkWebsitesAndPublishStatus() {
        try {
            //make parallel
            websitesService.getWebsites()
                    .forEach(this::checkWebsiteHealthAndPublishToKafka);
        } catch (MalformedURLException e) {
            throw new InvalidURLException("URL malformed exception" + e);
        } catch (SQLException e) {
            throw new DatabaseGenericException("SQL queries failed while running due to " + e);
        }
    }

    private void checkWebsiteHealthAndPublishToKafka(Website website) {
        HealthStatus healthStatus = URLAppStatus.getStatus(website.getUrl());
        healthEventProducer.sendToTopic(website.getId(), healthStatus);
        System.out.println("Sent to topic");
    }
}
