package entityClasses;

import entityClasses.Ideia;
import entityClasses.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-23T19:17:21")
@StaticMetamodel(Voto.class)
public class Voto_ { 

    public static volatile SingularAttribute<Voto, Ideia> ideiaOid;
    public static volatile SingularAttribute<Voto, Integer> oid;
    public static volatile SingularAttribute<Voto, User> userOid;

}