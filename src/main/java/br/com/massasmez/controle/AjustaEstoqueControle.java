/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.AjustaEstoque;
import br.com.massasmez.facede.AjustaEstoqueFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Marcelo
 */
@ManagedBean
@SessionScoped
public class AjustaEstoqueControle implements Serializable {

    private AjustaEstoque ajustaEstoque;
    @EJB
    private AjustaEstoqueFacade ajustaEstoqueFacade;
    private GenericConverter converter;
//    @Inject
//    private LoginControle loginControle;

    public AjustaEstoqueControle() {
        ajustaEstoque = new AjustaEstoque();
    }

    public GenericConverter converter() {
        if (converter == null) {
            converter = new GenericConverter(ajustaEstoqueFacade);
        }
        return converter;
    }

    public List<AjustaEstoque> autoComplete(String query) {
        return ajustaEstoqueFacade.autoComplete("nome", query);
    }

    public void salvar() {
        try {
//            ajustaEstoque.setUsuario(loginControle.getUsuario());
            ajustaEstoque.ajustarEstoque();
            ajustaEstoqueFacade.salvar(ajustaEstoque);
            ajustaEstoque = new AjustaEstoque();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Ajuste salvo com sucesso", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List<AjustaEstoque> getListaAjusteEstoques() {
        return ajustaEstoqueFacade.listarTodos();
    }

    public AjustaEstoque getAjusteEstoque() {
        return ajustaEstoque;
    }

    public void setAjusteEstoque(AjustaEstoque ajusteEstoque) {
        this.ajustaEstoque = ajusteEstoque;
    }

}
