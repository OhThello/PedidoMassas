package br.com.massasmez.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, BigDecimal> estoque;
	public static volatile SingularAttribute<Produto, Boolean> ativo;
	public static volatile SingularAttribute<Produto, Categoria> categoria;
	public static volatile SingularAttribute<Produto, String> nome;
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, BigDecimal> estoqueMinimo;
	public static volatile SingularAttribute<Produto, Date> dataCadastro;
	public static volatile SingularAttribute<Produto, BigDecimal> valorUnitario;

}

