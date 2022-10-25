package io.aiven.app.health.models;

import java.net.URL;

public class Website {
    private int id;
    private URL url;
    private String name;

    public Website(int id, URL url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public URL getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", url=" + url +
                ", name='" + name + '\'' +
                '}';
    }
}
