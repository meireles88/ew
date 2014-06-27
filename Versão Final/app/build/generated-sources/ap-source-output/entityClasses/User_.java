package entityClasses;

import entityClasses.Group1;
import entityClasses.Ideia;
import entityClasses.Voto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-23T19:17:21")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> morada;
    public static volatile SingularAttribute<User, Group1> groupOid;
    public static volatile SingularAttribute<User, Date> datanascimento;
    public static volatile CollectionAttribute<User, Ideia> ideiaCollection;
    public static volatile CollectionAttribute<User, Voto> votoCollection;
    public static volatile SingularAttribute<User, Date> dataultimoacesso;
    public static volatile SingularAttribute<User, Date> dataultimaedicao;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> ip;
    public static volatile SingularAttribute<User, Date> datacriacao;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Integer> oid;
    public static volatile CollectionAttribute<User, Group1> group1Collection;
    public static volatile SingularAttribute<User, byte[]> foto;

}