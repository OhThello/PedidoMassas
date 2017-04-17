/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.facede;

import br.com.massasmez.entidade.ItemCompra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcelo
 */
@Stateless
public class ItemCompraFacade extends AbstractFacade<ItemCompra> {

    @PersistenceContext(unitName = "pedidoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemCompraFacade() {
        super(ItemCompra.class);
    }

}
