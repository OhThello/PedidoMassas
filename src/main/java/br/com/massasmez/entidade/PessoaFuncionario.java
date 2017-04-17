/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "pes_funcionario")
@PrimaryKeyJoinColumn(name = "pes_id")
public class PessoaFuncionario extends PessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "pes_dtAdmissao", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAdmissao;

    @Column(name = "pes_dtDemissao", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataDemissao;

    @ManyToOne
    @JoinColumn(name = "ocupacao_id")
    private Ocupacao ocupacao;

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

}
