package br.com.massasmez.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PessoaFisica.class)
public abstract class PessoaFisica_ extends br.com.massasmez.entidade.Pessoa_ {

	public static volatile SingularAttribute<PessoaFisica, Date> nascimento;
	public static volatile SingularAttribute<PessoaFisica, String> rg;
	public static volatile SingularAttribute<PessoaFisica, String> cpf;
	public static volatile SingularAttribute<PessoaFisica, String> sexo;

}

