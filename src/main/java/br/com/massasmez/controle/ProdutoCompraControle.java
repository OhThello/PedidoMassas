/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.ProdutosCompra;
import br.com.massasmez.facede.ProdutoCompraFacade;
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
public class ProdutoCompraControle implements Serializable {

    private ProdutosCompra produtoCompra;
    @EJB
    private ProdutoCompraFacade produtoCompraFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(produtoCompraFacade);
        }
        return genericConverter;
    }

    public String novo() {
        produtoCompra = new ProdutosCompra();
        return "cadastro?faces-redirect=true";
    }

    public String salvar() {
        produtoCompraFacade.salvar(produtoCompra);
        return "pesquisa?faces-redirect=true";
    }

    public void excluir(ProdutosCompra e) {
        produtoCompraFacade.excluir(e);
    }

    public List<ProdutosCompra> getListaProdutoCompras() {
        return produtoCompraFacade.listarTodos();
    }

    public ProdutosCompra getProdutoCompra() {
        return produtoCompra;
    }

    public void setProdutoCompra(ProdutosCompra produtoCompra) {
        this.produtoCompra = produtoCompra;
    }

    public boolean isEditando() {
        return this.produtoCompra.getId() != null;
    }

    public List<ProdutosCompra> autoComplete(String query) {
        return produtoCompraFacade.autoComplete("nome", query);
    }

}
