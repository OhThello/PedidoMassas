package br.com.massasmez.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProdutosCompra.class)
public abstract class ProdutosCompra_ {

	public static volatile SingularAttribute<ProdutosCompra, BigDecimal> estoque;
	public static volatile SingularAttribute<ProdutosCompra, Boolean> ativo;
	public static volatile SingularAttribute<ProdutosCompra, String> nome;
	public static volatile SingularAttribute<ProdutosCompra, Long> id;
	public static volatile SingularAttribute<ProdutosCompra, BigDecimal> estoqueMinimo;
	public static volatile SingularAttribute<ProdutosCompra, Date> dataCadastro;
	public static volatile SingularAttribute<ProdutosCompra, BigDecimal> valorUnitario;

}

