package com.qacart.tasky.fixtures;


import com.qacart.tasky.models.User;
import com.qacart.tasky.utils.DataUtils;

public final class UserFixtures {


    public static User getDefaultUser(){
        return User.builder()
                .setFirstName("new")
                .setLastName("user")
                .setEmail(DataUtils.generateUniqueEmail())
                .setPassword("Test123!")
                .build();
    }

    public static User getDefaultUser(String email, String password){
        return User.builder()
                .setFirstName("new")
                .setLastName("user")
                .setEmail(email)
                .setPassword(password)
                .build();
    }

    public static User getInvalidUser(){
        return User.builder()
                .setFirstName("new")
                .setLastName("user")
                .setEmail("invalid$email")
                .setPassword("Test123!")
                .build();
    }

}
