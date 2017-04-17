/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.ContasReceber;
import br.com.massasmez.entidade.ContasReceber;
import br.com.massasmez.facede.ContasReceberFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ContasReceberControle implements Serializable {

    private ContasReceber contasReceber = new ContasReceber();
    private List<ContasReceber> listaContasReceber = new ArrayList<>();
    @EJB
    private ContasReceberFacade contasReceberFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(contasReceberFacade);
        }
        return genericConverter;
    }

    public String novo() {
        contasReceber = new ContasReceber();
        return "cadastro?faces-redirect=true";
    }

    public String salvar() {
        contasReceberFacade.salvar(contasReceber);
        return "pesquisa?faces-redirect=true";
    }

    public void excluir(ContasReceber e) {
        contasReceberFacade.excluir(e);
    }

    public List<ContasReceber> getListaContasRecebers() {
        return contasReceberFacade.listarTodos();
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public List<ContasReceber> getListaContasReceber() {
        listaContasReceber = contasReceberFacade.listarContas("(receber_valor - receber_valorPagamento) > 0");
        return listaContasReceber;
    }

    public List<ContasReceber> getListaContasRecebidas() {
        listaContasReceber = contasReceberFacade.listarContas("(receber_valor - receber_valorPagamento) <= 0");
        return listaContasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public boolean isEditando() {
        return this.contasReceber.getId() != null;
    }

}
