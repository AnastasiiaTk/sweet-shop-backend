package com.anastasiiat.sweetshop.util;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import java.util.Map;

public class TokenUtils {

    public static Map<String, Object> parseTokenToMap(String userToken) {
        Jwt jwtToken = JwtHelper.decode(userToken);
        String claims = jwtToken.getClaims();
        return JSONUtils.parseJSON(claims);
    }
}
