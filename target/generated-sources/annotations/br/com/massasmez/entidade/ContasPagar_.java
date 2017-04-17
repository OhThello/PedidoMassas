package br.com.massasmez.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasPagar.class)
public abstract class ContasPagar_ {

	public static volatile SingularAttribute<ContasPagar, Compra> compra;
	public static volatile SingularAttribute<ContasPagar, Boolean> ativo;
	public static volatile SingularAttribute<ContasPagar, Date> dataPagamento;
	public static volatile SingularAttribute<ContasPagar, Pessoa> pessoa;
	public static volatile SingularAttribute<ContasPagar, Date> dataVencimento;
	public static volatile SingularAttribute<ContasPagar, BigDecimal> valor;
	public static volatile SingularAttribute<ContasPagar, Long> id;
	public static volatile SingularAttribute<ContasPagar, Date> dataLancamento;
	public static volatile SingularAttribute<ContasPagar, BigDecimal> valorPagamento;

}

