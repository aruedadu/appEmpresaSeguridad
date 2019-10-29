package appempresaseguridad.data.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rol.class)
public abstract class Rol_ {

	public static volatile SingularAttribute<Rol, Integer> idRol;
	public static volatile ListAttribute<Rol, Usuario> usuarioList;
	public static volatile SingularAttribute<Rol, String> nombreRol;

}

