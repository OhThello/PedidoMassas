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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_id", nullable = false)
    private Long id;

    @Column(name = "pedido_dataCriacao", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();

    @Column(name = "pedido_observacao", nullable = true)
    private String observacao;

    @Column(name = "pedido_dataEntrega", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEntrega = new Date();

    @Column(name = "pedido_valorFrete", nullable = true)
    private BigDecimal valorFrete = BigDecimal.ZERO;

    @Column(name = "pedido_valorDesconto", nullable = true)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @Column(name = "pedido_valorTotal", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "pedido_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "pedido_numero", nullable = true, length = 10)
    private Integer numero;

    @Column(name = "pedido_complemeno", nullable = true, length = 150)
    private String complemento;

    @Column(name = "pedido_bairro", nullable = true, length = 50)
    private String bairro;

    @Column(name = "pedido_referencia", nullable = true)
    private String referencia;

    @Column(name = "pedido_parcela", nullable = false)
    @Min(value = 1)
    private Integer parcela = 1;

    @Column(name = "pedido_tipo", nullable = false)
    private String tipo = "AVISTA";

    @Column(name = "pedido_finalizado")
    private Boolean finalizado = false;

    @Column(name = "pedido_statuspedido", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Column(name = "pedido_formapagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade Cidade;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "pedido",
            orphanRemoval = true)
    private List<ContasReceber> contasReceberes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "pedido",
            orphanRemoval = true)
    private List<ItemPedido> itensPedido = new ArrayList<>();

    public void addItem(ItemPedido item) throws Exception {
        BigDecimal estoqueProd = item.getProduto().getEstoque();
        if (item.getQuantidade().compareTo(estoqueProd) <= 0) {
            item.setPedido(this);
            if (!itensPedido.contains(item)) {
                itensPedido.add(item);

            } else {
                int index = itensPedido.indexOf(item);
                ItemPedido iv = itensPedido.get(index);
                BigDecimal novaQtd = iv.getQuantidade().add(item.getQuantidade());
                iv.setQuantidade(novaQtd);
            }
            item.setValorUnitario(item.getProduto().getValorUnitario());
            item.baixaEstoque();
            calculaTotal();
        } else {
            throw new Exception("Estoque insuficiente");
        }
    }

    public void removeItem(ItemPedido item) throws Exception {
        if (itensPedido.contains(item)) {
            itensPedido.remove(item);
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
        for (ItemPedido i : itensPedido) {
            valorTotal = valorTotal.add(i.getValorUnitario().multiply(i.getQuantidade()));

        }
        BigDecimal desconto = valorTotal.multiply(valorDesconto.divide(new BigDecimal("100")));
        valorTotal = valorTotal.subtract(valorDesconto);
        valorTotal = valorTotal.add(valorFrete);
    }

    public void geraParcela(Integer qtdParcela) {
        contasReceberes = new ArrayList<>();
//        BigDecimal valorParcela = valorTotal.divide(new BigDecimal(parcela.toString()), BigDecimal.ROUND_HALF_UP);
        for (int i = 1; i <= parcela; i++) {
            ContasReceber cr = new ContasReceber();
            cr.setPedido(this);
//            cr.setValor(valorParcela);
            Calendar vencimento = Calendar.getInstance();
            vencimento.add(Calendar.DAY_OF_MONTH, 30 * i);
            cr.setDataVencimento(vencimento.getTime());
            cr.setValor(valorTotal.divide(new BigDecimal(qtdParcela), BigDecimal.ROUND_HALF_UP));
            contasReceberes.add(cr);
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

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
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

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
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

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<ContasReceber> getContasReceberes() {
        return contasReceberes;
    }

    public void setContasReceberes(List<ContasReceber> contasReceberes) {
        this.contasReceberes = contasReceberes;
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

    public Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(Cidade Cidade) {
        this.Cidade = Cidade;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
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
