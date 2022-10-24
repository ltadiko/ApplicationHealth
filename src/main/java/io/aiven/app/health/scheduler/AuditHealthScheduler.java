package io.aiven.app.health.scheduler;

import io.aiven.app.health.exception.DatabaseGenericException;
import io.aiven.app.health.exception.InvalidURLException;
import io.aiven.app.health.kafka.HealthEventProducer;
import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.repository.ApplicationRepository;
import io.aiven.app.health.services.WebsitesService;
import io.aiven.app.health.utils.URLAppStatus;

import java.net.MalformedURLException;
import java.sql.SQLException;

public class AuditHealthScheduler {
    private ApplicationRepository applicationRepository;

    public AuditHealthScheduler() {
        this.applicationRepository = new ApplicationRepository();
    }


    public void checkWebsitesAndPublishStatus() {
        WebsitesService websitesService = new WebsitesService(applicationRepository);
        try {
            websitesService.getWebsites().forEach(website -> {
                HealthStatus healthStatus = URLAppStatus.getStatus(website.getUrl());
                HealthEventProducer appHealthEventProducer = new HealthEventProducer();
                appHealthEventProducer.sendToTopic(website.getId(), healthStatus);
                System.out.println("Sent to topic " + healthStatus);
            });
        } catch (MalformedURLException e) {
            throw new InvalidURLException("URL malformed exception" + e);
        } catch (SQLException e) {
            throw new DatabaseGenericException("SQL queries failed while running due to " + e);
        }
    }
}
