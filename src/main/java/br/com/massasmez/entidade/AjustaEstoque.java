/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "ajutaestoque")
public class AjustaEstoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "aju_id", nullable = false)
    private Long id;
    @Column(name = "aju_data", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataAjuste = new Date();
    @Column(name = "aju_tipo", nullable = false)
    private String tipo = "ENTRADA";
    @Column(name = "aju_qtdatual", nullable = false)
    private BigDecimal qtdAtual;
    @Column(name = "aju_qtdanterior", nullable = false)
    private BigDecimal qtdAnterior;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public void ajustarEstoque() throws Exception {
        this.qtdAnterior = produto.getEstoque();
        if (tipo.equals("ENTRADA")) {
            BigDecimal novoEstoque = produto.getEstoque().add(qtdAtual);
            produto.setEstoque(novoEstoque);
        } else {
            if (qtdAtual.compareTo(produto.getEstoque()) > 0) {
                throw new Exception("Quantidade insuficiente no estoque");
            }
            BigDecimal novoEstoque = produto.getEstoque().subtract(qtdAtual);
            produto.setEstoque(novoEstoque);

        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAjuste() {
        return dataAjuste;
    }

    public void setDataAjuste(Date dataAjuste) {
        this.dataAjuste = dataAjuste;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(BigDecimal qtdAtual) {
        this.qtdAtual = qtdAtual;
    }

    public BigDecimal getQtdAnterior() {
        return qtdAnterior;
    }

    public void setQtdAnterior(BigDecimal qtdAnterior) {
        this.qtdAnterior = qtdAnterior;
    }

//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
        if (!(object instanceof AjustaEstoque)) {
            return false;
        }
        AjustaEstoque other = (AjustaEstoque) object;
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
