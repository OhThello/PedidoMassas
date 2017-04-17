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
public enum StatusPedido {

    ORCAMENTO("Orçamento"),
    EMETIDO("Emetido"),
    CANCELADO("Cancelado");

    private String descricao;

    private StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
