package com.github.lf2a.dto;

import java.io.Serializable;

/**
 * <h1>CredenciaisDTO.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22/03/2021
 */
public class CredenciaisDTO implements Serializable {

    private String email;
    private String senha;

    public CredenciaisDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
