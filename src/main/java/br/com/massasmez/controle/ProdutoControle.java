/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.Produto;
import br.com.massasmez.facede.ProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marcelo
 */
@ManagedBean
@SessionScoped
public class ProdutoControle implements Serializable {

    private Produto produto;
    @EJB
    private ProdutoFacade produtoFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(produtoFacade);
        }
        return genericConverter;
    }

    public String novo() {
        produto = new Produto();
        return "cadastro?faces-redirect=true";
    }

    public String salvar() {
        produtoFacade.salvar(produto);
        return "pesquisa?faces-redirect=true";
    }

    public void excluir(Produto e) {
        produtoFacade.excluir(e);
    }

    public List<Produto> getListaProdutos() {
        return produtoFacade.listarTodos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean isEditando() {
        return this.produto.getId() != null;
    }

    public List<Produto> autoComplete(String query) {
        return produtoFacade.autoComplete("nome", query);
    }

}
