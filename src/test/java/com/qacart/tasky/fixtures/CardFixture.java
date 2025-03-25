package com.qacart.tasky.fixtures;

import com.qacart.tasky.config.ConfigFactory;
import com.qacart.tasky.models.Card;

public class CardFixture {
    public static Card getDummyCard(){
        return Card.builder()
                .setCardNumber(ConfigFactory.getConfig().cardNumber())
                .setExpiryMonth(ConfigFactory.getConfig().expiryMonth())
                .setExpiryYear(ConfigFactory.getConfig().expiryYear())
                .setCvv(ConfigFactory.getConfig().cvv())
                .setSubscriptionType("advanced")
                .build();
    }
}
