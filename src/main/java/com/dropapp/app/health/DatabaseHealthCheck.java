package com.dropapp.app.health;

import com.codahale.metrics.health.HealthCheck;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

public class DatabaseHealthCheck extends HealthCheck {

    private final Jdbi jdbi;

    public DatabaseHealthCheck(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    protected Result check() {
        try {
            Handle handle = jdbi.open();
            handle.execute("SELECT 1");

            handle.close();
        } catch(Exception e) {
            return Result.unhealthy("DB is not running, please check!");
        }
        return Result.healthy();
    }
}
