package br.com.massasmez.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, FormaPagamento> formaPagamento;
	public static volatile ListAttribute<Pedido, ItemPedido> itensPedido;
	public static volatile SingularAttribute<Pedido, String> observacao;
	public static volatile SingularAttribute<Pedido, String> tipo;
	public static volatile SingularAttribute<Pedido, Pessoa> pessoa;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorDesconto;
	public static volatile SingularAttribute<Pedido, Integer> numero;
	public static volatile SingularAttribute<Pedido, String> bairro;
	public static volatile SingularAttribute<Pedido, StatusPedido> statusPedido;
	public static volatile ListAttribute<Pedido, ContasReceber> contasReceberes;
	public static volatile SingularAttribute<Pedido, Integer> parcela;
	public static volatile SingularAttribute<Pedido, Cidade> Cidade;
	public static volatile SingularAttribute<Pedido, String> complemento;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorFrete;
	public static volatile SingularAttribute<Pedido, String> logradouro;
	public static volatile SingularAttribute<Pedido, Date> dataEntrega;
	public static volatile SingularAttribute<Pedido, BigDecimal> valorTotal;
	public static volatile SingularAttribute<Pedido, Usuario> usuario;
	public static volatile SingularAttribute<Pedido, Long> id;
	public static volatile SingularAttribute<Pedido, Boolean> finalizado;
	public static volatile SingularAttribute<Pedido, Date> dataCriacao;
	public static volatile SingularAttribute<Pedido, String> referencia;

}

