package br.com.massasmez.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasReceber.class)
public abstract class ContasReceber_ {

	public static volatile SingularAttribute<ContasReceber, Boolean> ativo;
	public static volatile SingularAttribute<ContasReceber, Date> dataPagamento;
	public static volatile SingularAttribute<ContasReceber, Pessoa> pessoa;
	public static volatile SingularAttribute<ContasReceber, Date> dataVencimento;
	public static volatile SingularAttribute<ContasReceber, BigDecimal> valor;
	public static volatile SingularAttribute<ContasReceber, Pedido> pedido;
	public static volatile SingularAttribute<ContasReceber, Long> id;
	public static volatile SingularAttribute<ContasReceber, Date> dataLancamento;
	public static volatile SingularAttribute<ContasReceber, BigDecimal> valorPagamento;

}

