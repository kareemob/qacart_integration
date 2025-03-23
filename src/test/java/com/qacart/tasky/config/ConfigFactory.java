package com.qacart.tasky.config;


import org.aeonbits.owner.ConfigCache;

public final class ConfigFactory {
    private ConfigFactory(){}

    public static FrameWorkConfig getConfig(){
        return ConfigCache.getOrCreate(FrameWorkConfig.class);
    }
}
