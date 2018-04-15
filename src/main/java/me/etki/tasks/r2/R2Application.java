package me.etki.tasks.r2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.etki.tasks.r2.controller.AccountController;
import me.etki.tasks.r2.controller.NotFoundExceptionMapper;
import me.etki.tasks.r2.controller.TransferController;
import me.etki.tasks.r2.misc.jackson.BigDecimalSerializer;
import org.zalando.problem.ProblemModule;

public class R2Application extends Application<R2Configuration> {
    public static void main(String[] args) throws Exception {
        new R2Application().run(args);
    }

    @Override
    public void run(R2Configuration configuration, Environment environment) {
        environment.jersey().register(new AccountController());
        environment.jersey().register(new TransferController());
        environment.jersey().register(new NotFoundExceptionMapper());
    }

    @Override
    public void initialize(Bootstrap<R2Configuration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap
                .getObjectMapper()
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
                .registerModule(new SimpleModule().addSerializer(new BigDecimalSerializer()))
                .registerModule(new ProblemModule());
    }
}
