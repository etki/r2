package me.etki.tasks.r2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.etki.tasks.r2.cli.AwaitCommand;
import me.etki.tasks.r2.cli.ShutdownCommand;
import me.etki.tasks.r2.controller.AccountController;
import me.etki.tasks.r2.controller.HealthController;
import me.etki.tasks.r2.controller.NotFoundExceptionMapper;
import me.etki.tasks.r2.controller.ShutdownController;
import me.etki.tasks.r2.controller.TransferController;
import me.etki.tasks.r2.misc.LazyContainer;
import me.etki.tasks.r2.misc.jackson.BigDecimalSerializer;
import org.eclipse.jetty.server.Server;
import org.zalando.problem.ProblemModule;

public class R2Application extends Application<R2Configuration> {
    public static void main(String[] args) throws Exception {
        new R2Application().run(args);
    }

    @Override
    public void run(R2Configuration configuration, Environment environment) {
        LazyContainer<Server> container = new LazyContainer<>();
        environment.lifecycle().addServerLifecycleListener(container::set);
        JerseyEnvironment jersey = environment.jersey();
        jersey.register(new AccountController());
        jersey.register(new TransferController());
        jersey.register(new HealthController());
        jersey.register(new ShutdownController(container));
        jersey.register(new NotFoundExceptionMapper());
    }

    @Override
    public void initialize(Bootstrap<R2Configuration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap
                .getObjectMapper()
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
                .registerModule(new SimpleModule().addSerializer(new BigDecimalSerializer()))
                .registerModule(new ProblemModule());
        bootstrap.addCommand(new AwaitCommand());
        bootstrap.addCommand(new ShutdownCommand());
    }
}
