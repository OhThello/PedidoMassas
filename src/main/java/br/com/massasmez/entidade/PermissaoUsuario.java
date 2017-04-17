/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.entidade;

/**
 *
 * @author Marcelo
 */
public enum PermissaoUsuario {

    ADMIN("Adminstrador"),
    USER("Vendedor");

    private String descricao;

    private PermissaoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
