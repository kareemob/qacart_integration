package com.qacart.tasky.server;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.qacart.tasky.config.ConfigFactory;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public final class ServerManager {
    private ServerManager(){}

    private static final WireMockServer server = new WireMockServer(
            new WireMockConfiguration()
                    .port(ConfigFactory.getConfig().wiremockPort())
                    .usingFilesUnderDirectory("src/test/resources/stubs")
                    .enableBrowserProxying(true)
                    .globalTemplating(true)
                    .containerThreads(ConfigFactory.getConfig().wiremockThread())
                    .jettyAcceptors(ConfigFactory.getConfig().wiremockAcceptors())
                    .jettyAcceptQueueSize(ConfigFactory.getConfig().wiremockQueueSize())

    );


    public static void startServer() {
        configureFor("localhost", ConfigFactory.getConfig().wiremockPort());
        server.start();

    }

    public static void stopServer() {
        if (server.isRunning()) {
            server.stop();
        }
    }
}
