package io.aiven.app.health.utils;

import io.aiven.app.health.models.HealthStatus;

import java.net.HttpURLConnection;
import java.net.URL;

public final class URLAppStatus {
    private URLAppStatus() {

    }

    public static HealthStatus getStatus(final URL url) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            // Set connection timeout
            con.setConnectTimeout(3000);
            con.connect();

            int code = con.getResponseCode();
            if (code == 200) {
                return HealthStatus.HEALTHY;
            } else {
                return HealthStatus.UNHEALTHY;
            }
        } catch (Exception e) {
            return HealthStatus.UNHEALTHY;
        }
    }
}
