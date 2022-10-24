package io.aiven.app.health.services;

import io.aiven.app.health.scheduler.AuditHealthScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static io.aiven.app.health.configuration.ConfigurationProperties.getProperty;

public class PublishAuditLogScheduler {

    private static final String TIMER_INITIAL_DELAY = "publisher.scheduler.initialDelayInMilliSeconds";
    private static final String TIMER_PERIOD = "publisher.scheduler.periodInMilliSeconds";

    public void schedule() {
        AuditHealthScheduler auditHealthScheduler = new AuditHealthScheduler();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(auditHealthScheduler::checkWebsitesAndPublishStatus, Long.valueOf(getProperty(TIMER_INITIAL_DELAY)), Long.valueOf(getProperty(TIMER_PERIOD)), TimeUnit.MILLISECONDS);
    }
}
