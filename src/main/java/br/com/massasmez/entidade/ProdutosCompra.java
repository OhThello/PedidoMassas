/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Min;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "produtoCompra")
public class ProdutosCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prodCompra_id")
    private Long id;

    @Column(name = "prodCompra_nome", nullable = false)
    private String nome;

    @Column(name = "prodCompra_valor", nullable = false)
    private BigDecimal valorUnitario;

    @Column(name = "prodCompra_estoque", nullable = false)
    @Min(value = 0)
    private BigDecimal estoque = BigDecimal.ZERO;

    @Column(name = "prodCompra_estoquemin", nullable = false)
    @Min(value = 0)
    private BigDecimal estoqueMinimo = BigDecimal.ZERO;

    @Column(name = "prodCompra_ativo", nullable = true)
    private Boolean ativo = true;

    @Column(name = "prodCompra_dtcadastro", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCadastro = new Date();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    public BigDecimal getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
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
        if (!(object instanceof ProdutosCompra)) {
            return false;
        }
        ProdutosCompra other = (ProdutosCompra) object;
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
