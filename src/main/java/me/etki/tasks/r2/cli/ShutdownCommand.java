package me.etki.tasks.r2.cli;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutdownCommand extends Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShutdownCommand.class);

    public ShutdownCommand() {
        super("shutdown", "Tells service to shut down");
    }

    @Override
    public void configure(Subparser subparser) {
        subparser
                .addArgument("--host")
                .dest("host")
                .type(String.class)
                .required(false)
                .setDefault("localhost");
        subparser
                .addArgument("-p", "--port")
                .dest("port")
                .type(Integer.class)
                .required(false)
                .setDefault(8080);
    }

    /**
     * Using sout/.printStackTrace since logger isn't enabled in cli mode
     */
    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        HttpClient client = HttpClientBuilder
                .create()
                .build();
        HttpHost host = new HttpHost(namespace.getString("host"), namespace.getInt("port"));
        HttpRequest request = new HttpPost("/v1/_shutdown");
        try {
            StatusLine status = client.execute(host, request).getStatusLine();
            if (status.getStatusCode() < 300) {
                System.out.println("Successfully shut down application");
            } else {
                System.out.println("Failed to shut down application: received response with status " + status);
            }
        } catch (Exception e) {
            System.out.println("Failed to shut down application:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
