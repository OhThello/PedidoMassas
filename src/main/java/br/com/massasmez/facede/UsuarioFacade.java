/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.facede;

import br.com.massasmez.entidade.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Marcelo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "pedidoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario buscarUsuario(String email, String senha) {
        String consulta
                = "FROM Usuario AS u where u.email='" + email + "' AND u.senha='" + senha + "'";

        Query q = em.createQuery(consulta);

        if (q.getResultList().size() == 1) {
            return (Usuario) q.getResultList().get(0);
        } else {
            return null;
        }
    }
}
