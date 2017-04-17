/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.Cidade;
import br.com.massasmez.facede.CidadeFacade;
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
public class CidadeControle implements Serializable {

    private Cidade cidade;
    @EJB
    private CidadeFacade cidadeFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(cidadeFacade);
        }
        return genericConverter;
    }

    public List<Cidade> autoComplete(String query) {
        return cidadeFacade.autoComplete("nome", query);
    }

    public String novo() {
        cidade = new Cidade();
        return "editacidade?faces-redirect=true";
    }

    public String salvar() {
        cidadeFacade.salvar(cidade);
        return "listacidade?faces-redirect=true";
    }

    public void excluir(Cidade e) {
        cidadeFacade.excluir(e);
    }

    public List<Cidade> getListaCidades() {
        return cidadeFacade.listarTodos();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public boolean isEditando() {
        return this.cidade.getId() != null;
    }

}
