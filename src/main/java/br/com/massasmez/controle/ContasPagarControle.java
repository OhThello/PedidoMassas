/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.ContasPagar;
import br.com.massasmez.facede.ContasPagarFacade;
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
public class ContasPagarControle implements Serializable {

    private ContasPagar contasPagar = new ContasPagar();
    private List<ContasPagar> listaContasPagar = new ArrayList<>();
    @EJB
    private ContasPagarFacade contasPagarFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(contasPagarFacade);
        }
        return genericConverter;
    }

    public String novo() {
        contasPagar = new ContasPagar();
        return "cadastro?faces-redirect=true";
    }

    public String salvar() {
        contasPagarFacade.salvar(contasPagar);
        return "pesquisa?faces-redirect=true";
    }

    public void excluir(ContasPagar e) {
        contasPagarFacade.excluir(e);
    }

    public List<ContasPagar> getListaContasPagars() {
        return contasPagarFacade.listarTodos();
    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public List<ContasPagar> getListaContasPagar() {
        listaContasPagar = contasPagarFacade.listarContas("(pagar_valor - pagar_valorPagamento) > 0");
        return listaContasPagar;
    }

    public List<ContasPagar> getListaContasRecebidas() {
        listaContasPagar = contasPagarFacade.listarContas("(pagar_valor - pagar_valorPagamento) <= 0");
        return listaContasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public boolean isEditando() {
        return this.contasPagar.getId() != null;
    }

}
