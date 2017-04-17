package br.com.massasmez.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaFuncionario.class)
public abstract class PessoaFuncionario_ extends br.com.massasmez.entidade.PessoaFisica_ {

	public static volatile SingularAttribute<PessoaFuncionario, Date> dataDemissao;
	public static volatile SingularAttribute<PessoaFuncionario, Ocupacao> ocupacao;
	public static volatile SingularAttribute<PessoaFuncionario, Date> dataAdmissao;

}

