/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.PessoaFuncionario;
import br.com.massasmez.facede.PessoaFuncionarioFacade;
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
public class PessoaFuncionarioControle implements Serializable {

    private PessoaFuncionario pessoaFuncionario;
    @EJB
    private PessoaFuncionarioFacade pessoaFuncionarioFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(pessoaFuncionarioFacade);
        }
        return genericConverter;
    }
    
    public List<PessoaFuncionario> autoComplete(String query) {
        return pessoaFuncionarioFacade.autoComplete("nome", query);
    }

    public String novo() {
        pessoaFuncionario = new PessoaFuncionario();
        return "cadastro?faces-redirect=true";
    }

    public String salvar() {
        pessoaFuncionarioFacade.salvar(pessoaFuncionario);
        return "pesquisa?faces-redirect=true";
    }

    public void excluir(PessoaFuncionario e) {
        pessoaFuncionarioFacade.excluir(e);
    }

    public List<PessoaFuncionario> getListaPessoaFuncionarios() {
        return pessoaFuncionarioFacade.listarTodos();
    }

    public PessoaFuncionario getPessoaFuncionario() {
        return pessoaFuncionario;
    }

    public void setPessoaFuncionario(PessoaFuncionario pessoaFuncionario) {
        this.pessoaFuncionario = pessoaFuncionario;
    }

    public boolean isEditando() {
        return this.pessoaFuncionario.getId() != null;
    }

}
