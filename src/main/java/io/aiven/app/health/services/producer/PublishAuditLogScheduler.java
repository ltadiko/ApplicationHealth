package io.aiven.app.health.services.producer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;

public class PublishAuditLogScheduler {

    private static final String TIMER_INITIAL_DELAY = "publisher.scheduler.initialDelayInMilliSeconds";
    private static final String TIMER_PERIOD = "publisher.scheduler.periodInMilliSeconds";

    public static void schedule() {
        HealthCheck healthCheck = new HealthCheck();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(healthCheck::checkWebsitesAndPublishStatus, Long.valueOf(getProperty(TIMER_INITIAL_DELAY)), Long.valueOf(getProperty(TIMER_PERIOD)), TimeUnit.MILLISECONDS);
    }
}
