package entityClasses;

import entityClasses.Module;
import entityClasses.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-23T19:17:21")
@StaticMetamodel(Group1.class)
public class Group1_ { 

    public static volatile SingularAttribute<Group1, Integer> oid;
    public static volatile SingularAttribute<Group1, Module> moduleOid;
    public static volatile CollectionAttribute<Group1, User> userCollection1;
    public static volatile SingularAttribute<Group1, String> groupname;
    public static volatile CollectionAttribute<Group1, Module> moduleCollection;
    public static volatile CollectionAttribute<Group1, User> userCollection;

}