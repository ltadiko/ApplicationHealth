package io.aiven.app.health.services.consumer;

import io.aiven.app.health.models.HealthStatus;
import io.aiven.app.health.repository.ApplicationRepository;

import java.sql.SQLException;


public class WebsiteHealthLogsService {
    private ApplicationRepository applicationRepository;

    public WebsiteHealthLogsService() {
        this.applicationRepository = new ApplicationRepository();
    }

    public void addWebsiteHealthStatus(int websiteId, HealthStatus healthStatus) throws SQLException {
        applicationRepository.addWebsiteHealthStatus(websiteId, healthStatus);
    }
}
