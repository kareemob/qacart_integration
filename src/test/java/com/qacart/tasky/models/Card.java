package com.qacart.tasky.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
public class Card {
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    private String subscriptionType;
}
