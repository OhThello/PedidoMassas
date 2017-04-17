package br.com.massasmez.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Compra.class)
public abstract class Compra_ {

	public static volatile SingularAttribute<Compra, String> observacao;
	public static volatile SingularAttribute<Compra, String> tipo;
	public static volatile SingularAttribute<Compra, Pessoa> pessoa;
	public static volatile SingularAttribute<Compra, BigDecimal> valorDesconto;
	public static volatile ListAttribute<Compra, ItemCompra> itenCompras;
	public static volatile ListAttribute<Compra, ContasPagar> contasPagars;
	public static volatile SingularAttribute<Compra, BigDecimal> valorTotal;
	public static volatile SingularAttribute<Compra, Usuario> usuario;
	public static volatile SingularAttribute<Compra, Long> id;
	public static volatile SingularAttribute<Compra, Boolean> finalizado;
	public static volatile SingularAttribute<Compra, Date> dataCriacao;
	public static volatile SingularAttribute<Compra, Integer> parcela;

}

