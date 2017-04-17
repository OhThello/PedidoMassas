/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.Ocupacao;
import br.com.massasmez.facede.OcupacaoFacade;
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
public class OcupacaoControle implements Serializable {

    private Ocupacao ocupacao;
    @EJB
    private OcupacaoFacade ocupacaoFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(ocupacaoFacade);
        }
        return genericConverter;
    }
    
      public List<Ocupacao> autoComplete(String query) {
        return ocupacaoFacade.autoComplete("nome", query);
    }

    public String novo() {
        ocupacao = new Ocupacao();
        return "cadastro?faces-redirect=true";
    }

    public String salvar() {
        ocupacaoFacade.salvar(ocupacao);
        return "pesquisa?faces-redirect=true";
    }

    public void excluir(Ocupacao e) {
        ocupacaoFacade.excluir(e);
    }

    public List<Ocupacao> getListaOcupacaos() {
        return ocupacaoFacade.listarTodos();
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

    public boolean isEditando() {
        return this.ocupacao.getId() != null;
    }

}
