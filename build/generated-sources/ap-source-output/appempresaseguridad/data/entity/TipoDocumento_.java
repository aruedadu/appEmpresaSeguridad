package appempresaseguridad.data.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoDocumento.class)
public abstract class TipoDocumento_ {

	public static volatile ListAttribute<TipoDocumento, Persona> personaList;
	public static volatile SingularAttribute<TipoDocumento, Integer> idTipoDocumento;
	public static volatile SingularAttribute<TipoDocumento, String> abreviaturaTipoDocumento;
	public static volatile SingularAttribute<TipoDocumento, String> nombreTipoDocumento;

}

