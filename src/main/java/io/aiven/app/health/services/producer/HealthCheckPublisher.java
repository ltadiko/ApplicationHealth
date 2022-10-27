package io.aiven.app.health.services.producer;

import io.aiven.app.health.exception.DatabaseGenericException;
import io.aiven.app.health.exception.InvalidURLException;
import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.models.Website;
import io.aiven.app.health.utils.URLAppStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

public class HealthCheckPublisher {
    private static final Logger logger = LogManager.getLogger(HealthCheckPublisher.class);

    private WebsitesService websitesService;
    private HealthEventKafkaProducer healthEventKafkaProducer;

    public HealthCheckPublisher(WebsitesService websitesService,
                                HealthEventKafkaProducer healthEventKafkaProducer) {
        this.websitesService = websitesService;
        this.healthEventKafkaProducer = healthEventKafkaProducer;
    }

    /**
     * Method retrieves configured websites and publishes health data
     */
    public void checkWebsitesAndPublishStatus() {
        try {
            //make parallel
            List<Website> websiteList = websitesService.getWebsites();
            websiteList.stream()
                    .forEach(this::checkWebsiteHealthAndPublishToKafka);
        } catch (MalformedURLException e) {
            throw new InvalidURLException("URL malformed exception" + e);
        } catch (SQLException e) {
            throw new DatabaseGenericException("SQL queries failed while running due to " + e);
        }
    }

    /**
     * @param website : website contains website id name and url details
     */
    private void checkWebsiteHealthAndPublishToKafka(Website website) {
        HealthStatus healthStatus = URLAppStatus.getStatus(website.getUrl());
        healthEventKafkaProducer.sendToTopic(website.getId(), healthStatus);
        logger.debug("website {} audit information sent to kafka", website.getId());
    }
}
