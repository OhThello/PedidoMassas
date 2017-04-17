/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.Categoria;
import br.com.massasmez.facede.CategoriaFacade;
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
public class CategoriaControle implements Serializable {

    private Categoria categoria;
    @EJB
    private CategoriaFacade categoriaFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(categoriaFacade);
        }
        return genericConverter;
    }
    
    public List<Categoria> autoComplete(String query) {
        return categoriaFacade.autoComplete("nome", query);
    }

    public String novo() {
        categoria = new Categoria();
        return "cadastro?faces-redirect=true";
    }

    public String salvar() {
        categoriaFacade.salvar(categoria);
        return "pesquisa?faces-redirect=true";
    }

    public void excluir(Categoria e) {
        categoriaFacade.excluir(e);
    }

    public List<Categoria> getListaCategorias() {
        return categoriaFacade.listarTodos();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isEditando() {
        return this.categoria.getId() != null;
    }

}
