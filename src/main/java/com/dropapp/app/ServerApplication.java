package com.dropapp.app;

import com.dropapp.app.filter.JwtFilter;
import com.dropapp.app.health.DatabaseHealthCheck;
import com.dropapp.app.repository.UserRepository;
import com.dropapp.app.resource.AuthenticationResource;
import com.dropapp.app.resource.HelloResource;
import com.dropapp.app.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerApplication extends Application<ServerConfiguration> {

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        final JdbiFactory jdbiFactory = new JdbiFactory();
        final Jdbi jdbi = jdbiFactory.build(environment, configuration.getDataSource(), "mysql");
        final UserRepository userRepository = jdbi.onDemand(UserRepository.class);

        environment.jersey().register(new HelloResource());
        environment.jersey().register(new AuthenticationResource(userRepository));
        environment.jersey().register(new UserResource(userRepository));

        environment.healthChecks().register("database",
                new DatabaseHealthCheck(jdbi));
        environment.servlets().addFilter("JwtFilter", new JwtFilter())
                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true,"/*");
    }
}
