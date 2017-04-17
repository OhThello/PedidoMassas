/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Min;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "compra")
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "compra_id", nullable = false)
    private Long id;

    @Column(name = "compra_dataCriacao", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();

    @Column(name = "compra_observacao", nullable = true)
    private String observacao;

    @Column(name = "compra_valorDesconto", nullable = true)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @Column(name = "compra_valorTotal", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "compra_parcela", nullable = false)
    @Min(value = 1)
    private Integer parcela = 1;

    @Column(name = "compra_tipo", nullable = false)
    private String tipo = "AVISTA";

    @Column(name = "compra_finalizado")
    private Boolean finalizado = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "compra",
            orphanRemoval = true)
    private List<ContasPagar> contasPagars = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "compra",
            orphanRemoval = true)
    private List<ItemCompra> itenCompras = new ArrayList<>();

    public void addItem(ItemCompra item) throws Exception {
        BigDecimal estoqueProd = item.getProdutosCompra().getEstoque();
        if (item.getQuantidade().compareTo(estoqueProd) <= 0) {
            item.setCompra(this);
            if (!itenCompras.contains(item)) {
                itenCompras.add(item);

            } else {
                int index = itenCompras.indexOf(item);
                ItemCompra iv = itenCompras.get(index);
                BigDecimal novaQtd = iv.getQuantidade().add(item.getQuantidade());
                iv.setQuantidade(novaQtd);
            }
            item.setValorUnitario(item.getProdutosCompra().getValorUnitario());
            item.baixaEstoque();
            calculaTotal();
        } else {
            throw new Exception("Estoque insuficiente");
        }
    }

    public void removeItem(ItemCompra item) throws Exception {
        if (itenCompras.contains(item)) {
            itenCompras.remove(item);
            calculaTotal();
            item.estornaEstoque();
        } else {
            throw new Exception("O item selecionado não contem na lista de itens");
        }
    }

    public void calculaTotal() {
        if (valorDesconto == null) {
            valorDesconto = BigDecimal.ZERO;
        }
        valorTotal = BigDecimal.ZERO;
        for (ItemCompra i : itenCompras) {
            valorTotal = valorTotal.add(i.getValorUnitario().multiply(i.getQuantidade()));

        }
        BigDecimal desconto = valorTotal.multiply(valorDesconto.divide(new BigDecimal("100")));
        valorTotal = valorTotal.subtract(valorDesconto);
    }

    public void geraParcela(Integer qtdParcela) {
        contasPagars = new ArrayList<>();
//        BigDecimal valorParcela = valorTotal.divide(new BigDecimal(parcela.toString()), BigDecimal.ROUND_HALF_UP);
        for (int i = 1; i <= parcela; i++) {
            ContasPagar cr = new ContasPagar();
            cr.setCompra(this);
//            cr.setValor(valorParcela);
            Calendar vencimento = Calendar.getInstance();
            vencimento.add(Calendar.DAY_OF_MONTH, 30 * i);
            cr.setDataVencimento(vencimento.getTime());
            cr.setValor(valorTotal.divide(new BigDecimal(qtdParcela), BigDecimal.ROUND_HALF_UP));
            contasPagars.add(cr);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<ContasPagar> getContasPagars() {
        return contasPagars;
    }

    public void setContasPagars(List<ContasPagar> contasPagars) {
        this.contasPagars = contasPagars;
    }

    public List<ItemCompra> getItenCompras() {
        return itenCompras;
    }

    public void setItenCompras(List<ItemCompra> itenCompras) {
        this.itenCompras = itenCompras;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
