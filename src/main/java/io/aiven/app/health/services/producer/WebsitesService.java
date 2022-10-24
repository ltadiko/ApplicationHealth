package io.aiven.app.health.services.producer;

import io.aiven.app.health.models.Website;
import io.aiven.app.health.repository.ApplicationRepository;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

public class WebsitesService {
    private ApplicationRepository applicationRepository;

    public WebsitesService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Website> getWebsites() throws MalformedURLException, SQLException {
        return applicationRepository.getWebsites();
    }

}
