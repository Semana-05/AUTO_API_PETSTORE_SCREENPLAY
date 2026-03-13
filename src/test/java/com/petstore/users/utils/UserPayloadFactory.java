package com.petstore.users.utils;

import java.util.HashMap;
import java.util.Map;

public final class UserPayloadFactory {

    private UserPayloadFactory() {
    }

    public static Map<String, Object> forCreate(
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            int userStatus
    ) {
        return buildPayload(0, username, firstName, lastName, email, password, phone, userStatus);
    }

    public static Map<String, Object> forUpdate(
            long id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            int userStatus
    ) {
        return buildPayload(id, username, firstName, lastName, email, password, phone, userStatus);
    }

    private static Map<String, Object> buildPayload(
            long id,
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            int userStatus
    ) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", id);
        payload.put("username", username);
        payload.put("firstName", firstName);
        payload.put("lastName", lastName);
        payload.put("email", email);
        payload.put("password", password);
        payload.put("phone", phone);
        payload.put("userStatus", userStatus);
        return payload;
    }
}
