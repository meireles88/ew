package entityClasses;

import entityClasses.Ideia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-23T19:17:21")
@StaticMetamodel(Grupoorcamental.class)
public class Grupoorcamental_ { 

    public static volatile SingularAttribute<Grupoorcamental, String> titulo;
    public static volatile SingularAttribute<Grupoorcamental, Integer> oid;
    public static volatile SingularAttribute<Grupoorcamental, Date> dataedicao;
    public static volatile SingularAttribute<Grupoorcamental, Date> datainsercao;
    public static volatile CollectionAttribute<Grupoorcamental, Ideia> ideiaCollection;
    public static volatile SingularAttribute<Grupoorcamental, String> descricao;

}