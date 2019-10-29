package appempresaseguridad.data.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, Integer> idUsuario;
	public static volatile SingularAttribute<Usuario, String> passUsuario;
	public static volatile SingularAttribute<Usuario, String> nombreUsuario;
	public static volatile SingularAttribute<Usuario, Rol> idRolUsuario;
	public static volatile SingularAttribute<Usuario, Persona> idPersonaUsuario;

}

