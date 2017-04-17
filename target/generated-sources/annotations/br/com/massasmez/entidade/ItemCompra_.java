package br.com.massasmez.entidade;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemCompra.class)
public abstract class ItemCompra_ {

	public static volatile SingularAttribute<ItemCompra, Compra> compra;
	public static volatile SingularAttribute<ItemCompra, Long> id;
	public static volatile SingularAttribute<ItemCompra, ProdutosCompra> produtosCompra;
	public static volatile SingularAttribute<ItemCompra, BigDecimal> quantidade;
	public static volatile SingularAttribute<ItemCompra, BigDecimal> valorUnitario;

}

