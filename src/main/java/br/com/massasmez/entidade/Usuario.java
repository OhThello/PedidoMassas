/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.massasmez.entidade;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcelo
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;
    @Column(name = "user_nome", nullable = false)
    private String nome;
    @Column(name = "user_email", nullable = false)
    private String email;
    @Column(name = "user_senha", nullable = false)
    private String senha;
    @OneToMany(cascade = CascadeType.ALL)
  
    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private PessoaFuncionario pessoaFuncionario;

    @Column(name = "usu_permissao", nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissaoUsuario permissaoUsuario;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PessoaFuncionario getPessoaFuncionario() {
        return pessoaFuncionario;
    }

    public void setPessoaFuncionario(PessoaFuncionario pessoaFuncionario) {
        this.pessoaFuncionario = pessoaFuncionario;
    }

    public PermissaoUsuario getPermissaoUsuario() {
        return permissaoUsuario;
    }

    public void setPermissaoUsuario(PermissaoUsuario permissaoUsuario) {
        this.permissaoUsuario = permissaoUsuario;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
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
