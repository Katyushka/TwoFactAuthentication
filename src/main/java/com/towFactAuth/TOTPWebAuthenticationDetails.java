package com.towFactAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 22.05.16.
 */

public class TOTPWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private Integer totpKey;

    public TOTPWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String totpKeyString = request.getParameter("TOTPKey");
        if (StringUtils.hasText(totpKeyString)) {
            try {
                this.totpKey = Integer.valueOf(totpKeyString);
            } catch (NumberFormatException e) {
                this.totpKey = null;
            }
        }
    }

    public Integer getTotpKey() {
        return this.totpKey;
    }


}