package com.github.lf2a.domain.enums;

/**
 * <h1>Perfil.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22/03/2021
 */
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Perfil c : Perfil.values()) {
            if (cod.equals(c.getCod())) {
                return c;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }
}
