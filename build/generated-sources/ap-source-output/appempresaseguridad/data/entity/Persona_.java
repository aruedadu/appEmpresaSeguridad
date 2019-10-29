package appempresaseguridad.data.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Persona.class)
public abstract class Persona_ {

	public static volatile SingularAttribute<Persona, String> nombresPersona;
	public static volatile SingularAttribute<Persona, String> primerApellidoPersona;
	public static volatile ListAttribute<Persona, Usuario> usuarioList;
	public static volatile SingularAttribute<Persona, TipoDocumento> idTipoDocumentoPersona;
	public static volatile SingularAttribute<Persona, String> segundoApellidoPersona;
	public static volatile SingularAttribute<Persona, String> numeroDocumentoPersona;
	public static volatile SingularAttribute<Persona, Integer> idPersona;

}

