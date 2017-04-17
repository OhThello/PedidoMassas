/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.ItemCompra;
import br.com.massasmez.entidade.Compra;
import br.com.massasmez.entidade.ContasPagar;
import br.com.massasmez.facede.CompraFacade;
import br.com.massasmez.facede.ContasPagarFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcelo
 */
@ManagedBean
@SessionScoped
public class CompraControle implements Serializable {

    private Compra compra = new Compra();
    private ItemCompra itemCompra = new ItemCompra();
    @EJB
    private CompraFacade compraFacade;
    @EJB
    private ContasPagarFacade contasPagarFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(compraFacade);
        }
        return genericConverter;
    }

    public void addItem() {
        try {
            compra.addItem(itemCompra);
            itemCompra = new ItemCompra();
            compra.calculaTotal();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void removeItem() {
        try {
            compra.removeItem(itemCompra);
            itemCompra = new ItemCompra();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String novo() {
        compra = new Compra();
        itemCompra = new ItemCompra();
        return "cadastroCompra?faces-redirect=true";
    }

    public String finalizar() {
        compra.setFinalizado(true);
        compraFacade.salvar(compra);
        if (compra.getTipo().equals("AVISTA")) {
            faturar();
        }
        return "pesquisaCompra?faces-redirect=true";
    }

    public void faturar() {
        ContasPagar c = new ContasPagar();
        c.setDataVencimento(compra.getDataCriacao());
        c.setValor(compra.getValorTotal());
        c.setCompra(compra);
        contasPagarFacade.salvar(c);

    }
    
    public void avista(){
        if(compra.getTipo().equals("AVISTA")){
            compra.setParcela(1);
            compra.geraParcela(1);
        }
    }

  
    public String salvar() {
        compraFacade.salvar(compra);

        return "pesquisaCompra?faces-redirect=true";
    }

    public List<Compra> autoComplete(String query) {
        return compraFacade.autoComplete("nome", query);
    }

    public void excluir(Compra e) {
        compraFacade.excluir(e);
    }

    public List<Compra> getListaCompras() {
        return compraFacade.listarTodos();
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public ItemCompra getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(ItemCompra itemCompra) {
        this.itemCompra = itemCompra;
    }

    public CompraFacade getCompraFacade() {
        return compraFacade;
    }

    public void setCompraFacade(CompraFacade compraFacade) {
        this.compraFacade = compraFacade;
    }

    public GenericConverter getGenericConverter() {
        return genericConverter;
    }

    public void setGenericConverter(GenericConverter genericConverter) {
        this.genericConverter = genericConverter;
    }

    public boolean isEditando() {
        return this.compra.getId() != null;
    }

}
