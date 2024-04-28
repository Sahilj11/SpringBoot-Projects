package com.sahil.fileupload.storageconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

/**
 * StorageProperties
 */
@ConfigurationProperties(prefix = "storage")
@Component
@AllArgsConstructor
public class StorageProperties {

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
