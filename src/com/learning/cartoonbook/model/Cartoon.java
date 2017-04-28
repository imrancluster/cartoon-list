package com.learning.cartoonbook.model;

/**
 * Cartoon model to manage cartoon
 */
public class Cartoon {
    private String name;
    private String provider;
    private String videoUrl;

    public Cartoon(String name, String provider, String videoUrl) {
        this.name = name;
        this.provider = provider;
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public String getProvider() {
        return provider;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    @Override
    public String toString() {
        return String.format("Cartoon: %s by %s", getName(), getProvider());
    }
}