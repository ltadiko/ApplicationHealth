package io.aiven.app.health.services.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;

public class PublishAuditLogScheduler {
    private static final Logger logger = LogManager.getLogger(PublishAuditLogScheduler.class);

    private static final String TIMER_INITIAL_DELAY = "publisher.scheduler.initialDelayInMilliSeconds";
    private static final String TIMER_PERIOD = "publisher.scheduler.periodInMilliSeconds";

    private HealthCheck healthCheck;

    public PublishAuditLogScheduler(HealthCheck healthCheck) {
        this.healthCheck = healthCheck;
    }

    public void schedule() {
        logger.debug("Audit log publisher scheduler started");
        try (ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10)) {
            scheduledExecutorService.scheduleAtFixedRate(healthCheck::checkWebsitesAndPublishStatus, Long.valueOf(getProperty(TIMER_INITIAL_DELAY)), Long.valueOf(getProperty(TIMER_PERIOD)), TimeUnit.MILLISECONDS);
        }
    }
}
