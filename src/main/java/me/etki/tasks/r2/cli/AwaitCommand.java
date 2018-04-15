package me.etki.tasks.r2.cli;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class AwaitCommand extends Command {
    public AwaitCommand() {
        super("await", "Waits until service is up");
    }

    @Override
    public void configure(Subparser subparser) {
        subparser
                .addArgument("-t", "--timeout")
                .dest("timeout")
                .type(Integer.class)
                .required(false)
                .setDefault(10)
                .help("Maximum number of seconds to wait");
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
     * Using sout since logger isn't enabled in cli mode
     */
    @Override
    public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
        long start = System.currentTimeMillis();
        int timeout = namespace.getInt("timeout");
        HttpClient client = HttpClientBuilder
                .create()
                .build();
        HttpHost host = new HttpHost(namespace.getString("host"), namespace.getInt("port"));
        HttpRequest request = new HttpGet("/v1/_health");
        while (System.currentTimeMillis() - start < timeout * 1000) {
            System.out.println("Pinging application `" + host.toHostString() + "`...");
            try {
                StatusLine status = client.execute(host, request).getStatusLine();
                if (status.getStatusCode() < 300) {
                    System.out.println("Got successful response, application is alive");
                    return;
                }
                System.out.println("Got response with unexpected status: " + status);
            } catch (Exception e) {
                System.out.println("Unsuccessful attempt: " + e.getMessage());
            }
            Thread.sleep(1000);
        }
        System.out.println("Failed to ping application");
        System.exit(1);
    }
}
