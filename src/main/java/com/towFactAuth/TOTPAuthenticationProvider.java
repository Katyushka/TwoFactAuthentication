package com.towFactAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user on 22.05.16.
 */


public class TOTPAuthenticationProvider extends DaoAuthenticationProvider {
    private TOTPAuthenticator totpAuthenticator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        super.additionalAuthenticationChecks(userDetails, authentication);

        if (authentication.getDetails() instanceof TOTPWebAuthenticationDetails) {
            //String secret = ((TOTPUserDetails) userDetails).getSecret();
            String secret = "qwe";
            if (StringUtils.hasText(secret)) {
                Integer totpKey = ((TOTPWebAuthenticationDetails) authentication
                        .getDetails()).getTotpKey();
                if (totpKey != null) {
                    try {
                        if (!totpAuthenticator.verifyCode(secret, totpKey, 2)) {
                            throw new BadCredentialsException("Invalid TOTP code");
                        }
                    } catch (InvalidKeyException | NoSuchAlgorithmException e) {
                        throw new InternalAuthenticationServiceException("TOTP code verification failed", e);
                    }
                }
            }
        }
    }

    public TOTPAuthenticator getTotpAuthenticator() {
        return totpAuthenticator;
    }

    public void setTotpAuthenticator(TOTPAuthenticator totpAuthenticator) {
        this.totpAuthenticator = totpAuthenticator;
    }



}