package com.github.lf2a.services;

import com.github.lf2a.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <h1>UserService.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22/03/2021
 */
public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        }catch (Exception e) {
            return null;
        }
    }
}
