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
@Table(name = "pes_fisica")
@PrimaryKeyJoinColumn(name = "pes_id")
public class PessoaFisica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "pes_dtnasc", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date nascimento;

    @Column(name = "pes_sexo", nullable = false)
    private String sexo = "Masculino";

    @Column(name = "pes_cpf", nullable = false, length = 20)
    private String cpf;

    @Column(name = "pes_rg", nullable = false, length = 20)
    private String rg;

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Override
    public String getDocumentoFederal() {
        return cpf;
    }

    @Override
    public String getDocumentoEstadual() {
        return rg;
    }

}
