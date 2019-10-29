package appempresaseguridad.data.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TurnoUsuarioEmpresaPK.class)
public abstract class TurnoUsuarioEmpresaPK_ {

	public static volatile SingularAttribute<TurnoUsuarioEmpresaPK, Integer> idUsuario;
	public static volatile SingularAttribute<TurnoUsuarioEmpresaPK, Date> fehcaInicioTurno;
	public static volatile SingularAttribute<TurnoUsuarioEmpresaPK, Date> fechaFinTurno;
	public static volatile SingularAttribute<TurnoUsuarioEmpresaPK, Integer> idEmpresa;
	public static volatile SingularAttribute<TurnoUsuarioEmpresaPK, Integer> idTurno;

}

