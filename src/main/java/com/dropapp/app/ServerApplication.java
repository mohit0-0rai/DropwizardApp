package com.dropapp.app;

import com.dropapp.app.repository.UserRepository;
import com.dropapp.app.resource.AuthenticationResource;
import com.dropapp.app.resource.HelloResource;
import com.dropapp.app.resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class ServerApplication extends Application<ServerConfig> {

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServerConfig> bootstrap) {
    }

    @Override
    public void run(ServerConfig serverConfig, Environment environment) throws Exception {
        final JdbiFactory jdbiFactory = new JdbiFactory();
        final Jdbi jdbi = jdbiFactory.build(environment, serverConfig.getDataSource(), "mysql");
        //Injector injector = Guice.createInjector(new ServerModule());
        final UserRepository userRepository = jdbi.onDemand(UserRepository.class);
        environment.jersey().register(HelloResource.class);
        environment.jersey().register(AuthenticationResource.class);
        environment.jersey().register(new UserResource(userRepository));
    }
}
