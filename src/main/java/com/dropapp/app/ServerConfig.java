package com.dropapp.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ServerConfig extends Configuration {
    @NotEmpty
    private String message;

    @Valid
    @NotNull
    private DataSourceFactory dataSource = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSource() {
        return dataSource;
    }

    @JsonProperty("database")
    public void setDataSource(DataSourceFactory dataSource) {
        this.dataSource = dataSource;
    }

    public String getMessage() {
        return this.message;
    }
}
