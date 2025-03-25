package com.qacart.tasky.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}src/test/resources/env/config.properties",
        "file:${user.dir}src/test/resources/env/cards.properties"
})
public interface FrameWorkConfig extends Config{
    @DefaultValue("https://tasky.qacart.com")
    String url();
    @DefaultValue("https://tasky-be.qacart.com/api")
    @Key("api.uri")
    String apiUri();
    @DefaultValue("8088")
    int wiremockPort();
    @DefaultValue("100")
    int wiremockThread();
    @DefaultValue("8")
    int wiremockAcceptors();
    @DefaultValue("200")
    int wiremockQueueSize();
    @DefaultValue("4111111111111111")
    String cardNumber();
    @DefaultValue("07")
    String expiryMonth();
    @DefaultValue("2027")
    String expiryYear();
    @DefaultValue("333")
    String cvv();
}
