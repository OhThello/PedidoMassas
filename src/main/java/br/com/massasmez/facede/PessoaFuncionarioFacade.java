/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.facede;

import br.com.massasmez.entidade.PessoaFisica;
import br.com.massasmez.entidade.PessoaFuncionario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcelo
 */
@Stateless
public class PessoaFuncionarioFacade extends AbstractFacade<PessoaFuncionario> {

    @PersistenceContext(unitName = "pedidoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFuncionarioFacade() {
        super(PessoaFuncionario.class);
    }

}
