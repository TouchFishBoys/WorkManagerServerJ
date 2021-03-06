package com.my.workmanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "workmanager.storage")
public class StorageConfiguration {
    private String rootDirectory;
    private String qaTableTemplateLocation;

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public String getQaTableTemplateLocation() {
        return qaTableTemplateLocation;
    }

    public void setQaTableTemplateLocation(String qaTableTemplateLocation) {
        this.qaTableTemplateLocation = qaTableTemplateLocation;
    }
}
