package br.com.massasmez.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AjustaEstoque.class)
public abstract class AjustaEstoque_ {

	public static volatile SingularAttribute<AjustaEstoque, String> tipo;
	public static volatile SingularAttribute<AjustaEstoque, Produto> produto;
	public static volatile SingularAttribute<AjustaEstoque, Date> dataAjuste;
	public static volatile SingularAttribute<AjustaEstoque, BigDecimal> qtdAtual;
	public static volatile SingularAttribute<AjustaEstoque, Long> id;
	public static volatile SingularAttribute<AjustaEstoque, BigDecimal> qtdAnterior;

}

