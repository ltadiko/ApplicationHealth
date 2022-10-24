package io.aiven.app.health.services;

import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.repository.ApplicationRepository;

import java.sql.SQLException;


public class WebsiteHealthLogsService {
    private ApplicationRepository applicationRepository;

    public WebsiteHealthLogsService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void addWebsiteHealthStatus(int websiteId, HealthStatus healthStatus) throws SQLException {
        applicationRepository.addWebsiteHealthStatus(websiteId, healthStatus);
    }
}
