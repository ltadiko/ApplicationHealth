package io.aiven.app.health.services.producer;

import io.aiven.app.health.models.Website;
import io.aiven.app.health.repository.ProducerApplicationRepository;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

public class WebsitesService {
    private ProducerApplicationRepository producerApplicationRepository;

    public WebsitesService(ProducerApplicationRepository producerApplicationRepository) {
        this.producerApplicationRepository = producerApplicationRepository;
    }

    public List<Website> getWebsites() throws MalformedURLException, SQLException {
        return producerApplicationRepository.getWebsites();
    }

}
