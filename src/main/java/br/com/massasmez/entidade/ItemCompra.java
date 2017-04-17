/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "itemCompra")
public class ItemCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(name = "item_quantidade", nullable = false)
    @Min(value = 0)
    private BigDecimal quantidade = BigDecimal.ONE;

    @Column(name = "item_valorunitario", nullable = false)
    @Min(value = 0)
    private BigDecimal valorUnitario;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prodCompra_id", nullable = false)
    private ProdutosCompra produtosCompra;

    public void baixaEstoque() {
        BigDecimal novoEstoque = produtosCompra.getEstoque().add(quantidade);
        produtosCompra.setEstoque(novoEstoque);
    }

    public void estornaEstoque() {
        BigDecimal novoEstoque = produtosCompra.getEstoque().subtract(quantidade);
        produtosCompra.setEstoque(novoEstoque);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public ProdutosCompra getProdutosCompra() {
        return produtosCompra;
    }

    public void setProdutosCompra(ProdutosCompra produtosCompra) {
        this.produtosCompra = produtosCompra;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.produtosCompra);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemCompra other = (ItemCompra) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.produtosCompra, other.produtosCompra)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
