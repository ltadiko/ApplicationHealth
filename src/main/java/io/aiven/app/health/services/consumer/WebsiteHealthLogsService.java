package io.aiven.app.health.services.consumer;

import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.repository.ConsumerApplicationRepository;

import java.sql.SQLException;


public class WebsiteHealthLogsService {
    private ConsumerApplicationRepository consumerApplicationRepository;

    public WebsiteHealthLogsService(ConsumerApplicationRepository consumerApplicationRepository) {
        this.consumerApplicationRepository = consumerApplicationRepository;
    }

    public void addWebsiteHealthStatus(int websiteId, HealthStatus healthStatus) throws SQLException {
        consumerApplicationRepository.addWebsiteHealthStatus(websiteId, healthStatus);
    }
}
