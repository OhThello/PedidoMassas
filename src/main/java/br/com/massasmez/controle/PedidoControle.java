/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.controle;

import br.com.massasmez.converter.GenericConverter;
import br.com.massasmez.entidade.ContasPagar;
import br.com.massasmez.entidade.ContasReceber;
import br.com.massasmez.entidade.FormaPagamento;
import br.com.massasmez.entidade.ItemPedido;
import br.com.massasmez.entidade.Pedido;
import br.com.massasmez.entidade.StatusPedido;
import br.com.massasmez.facede.ContasReceberFacade;
import br.com.massasmez.facede.PedidoFacade;
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
public class PedidoControle implements Serializable {

    private Pedido pedido = new Pedido();
    private ItemPedido itemPedido = new ItemPedido();
    @EJB
    private PedidoFacade pedidoFacade;
    @EJB
    private ContasReceberFacade contasReceberFacade;
    private GenericConverter genericConverter;

    public GenericConverter converter() {
        if (genericConverter == null) {
            genericConverter = new GenericConverter(pedidoFacade);
        }
        return genericConverter;
    }

    public void addItem() {
        try {
            pedido.addItem(itemPedido);
            itemPedido = new ItemPedido();
            pedido.calculaTotal();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void removeItem() {
        try {
            pedido.removeItem(itemPedido);
            itemPedido = new ItemPedido();
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String novo() {
        pedido = new Pedido();
        itemPedido = new ItemPedido();
        return "cadastroPedido?faces-redirect=true";
    }

    public String finalizar() {
        pedido.setFinalizado(true);
        pedidoFacade.salvar(pedido);
        if (pedido.getTipo().equals("AVISTA")) {
            faturar();
        }
        return "pesquisaPedido?faces-redirect=true";
    }

    public void faturar() {
        ContasReceber c = new ContasReceber();
        c.setDataVencimento(pedido.getDataCriacao());
        c.setValor(pedido.getValorTotal());
        c.setPedido(pedido);
        contasReceberFacade.salvar(c);

    }
    
    public void avista(){
        if(pedido.getTipo().equals("AVISTA")){
            pedido.setParcela(1);
            pedido.geraParcela(1);
        }
    }

    public StatusPedido[] getStatusPeido() {
        return StatusPedido.values();
    }

    public FormaPagamento[] getFormaPagamento() {
        return FormaPagamento.values();
    }

    public String salvar() {
        pedidoFacade.salvar(pedido);

        return "pesquisaPedido?faces-redirect=true";
    }

    public List<Pedido> autoComplete(String query) {
        return pedidoFacade.autoComplete("nome", query);
    }

    public void excluir(Pedido e) {
        pedidoFacade.excluir(e);
    }

    public List<Pedido> getListaPedidos() {
        return pedidoFacade.listarTodos();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public PedidoFacade getPedidoFacade() {
        return pedidoFacade;
    }

    public void setPedidoFacade(PedidoFacade pedidoFacade) {
        this.pedidoFacade = pedidoFacade;
    }

    public GenericConverter getGenericConverter() {
        return genericConverter;
    }

    public void setGenericConverter(GenericConverter genericConverter) {
        this.genericConverter = genericConverter;
    }

    public boolean isEditando() {
        return this.pedido.getId() != null;
    }

}
