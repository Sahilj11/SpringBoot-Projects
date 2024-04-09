package com.sahil.fileupload.storageconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * StorageProperties
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
