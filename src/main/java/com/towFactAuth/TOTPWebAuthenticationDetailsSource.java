package com.towFactAuth;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 22.05.16.
 */

public class TOTPWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

    @Override
    public TOTPWebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new TOTPWebAuthenticationDetails(request);
    }

}