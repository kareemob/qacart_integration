package com.qacart.tasky.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}src/test/resources/env/config.properties"
})
public interface FrameWorkConfig extends Config{
    @DefaultValue("https://tasky.qacart.com")
    String url();
    @DefaultValue("8088")
    int wiremockPort();
    @DefaultValue("100")
    int wiremockThread();
    @DefaultValue("8")
    int wiremockAcceptors();
    @DefaultValue("200")
    int wiremockQueueSize();
}
