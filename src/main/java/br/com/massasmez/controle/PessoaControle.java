/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.Pessoa;
import br.com.massasmez.entidade.PessoaFisica;
import br.com.massasmez.entidade.PessoaJuridica;
import br.com.massasmez.facede.PessoaFacade;
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
public class PessoaControle implements Serializable {

    private Pessoa pessoa;
    @EJB
    private PessoaFacade pessoaFacade;
    private GenericConverter converter;
    private String tipoPessoa;

    public GenericConverter converter() {
        if (converter == null) {
            converter = new GenericConverter(pessoaFacade);
        }
        return converter;
    }

    public String novo() {
        tipoPessoa = "PF";
        criaPessoa();
        return "cadastro?faces-redirect=true";
    }

    public List<Pessoa> autoComplete(String query) {
        return pessoaFacade.autoComplete("nome", query);
    }

    public void criaPessoa() {
        if (tipoPessoa.equals("PF")) {
            pessoa = new PessoaFisica();
        } else {
            pessoa = new PessoaJuridica();
        }
    }

    public String salvar() {
        pessoaFacade.salvar(pessoa);
        return "pesquisa?faces-redirect=true";
    }

    public void excluir(Pessoa g) {
        pessoaFacade.excluir(g);
    }

    public List<Pessoa> getListaPessoas() {
        return pessoaFacade.listarTodos();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica) {
            tipoPessoa = "PF";
        } else {
            tipoPessoa = "PJ";
        }
        this.pessoa = pessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public boolean isEditando() {
        return this.pessoa.getId() != null;
    }
}
