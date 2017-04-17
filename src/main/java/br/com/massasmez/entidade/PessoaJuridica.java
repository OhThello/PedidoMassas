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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "pes_juridica")
@PrimaryKeyJoinColumn(name = "pes_id")
public class PessoaJuridica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "pes_cnpj", nullable = false, length = 20)
    private String cnpj;

    @Column(name = "pes_ie", nullable = true, length = 20)
    private String ie;

    @Column(name = "pes_razaosocial", nullable = false, length = 100)
    private String razaoSocial;

    @Column(name = "pes_dtabertura", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAbertura;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    @Override
    public String getDocumentoFederal() {
        return cnpj;
    }

    @Override
    public String getDocumentoEstadual() {
        return ie;
    }

}
