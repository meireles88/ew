package entityClasses;

import entityClasses.Group1;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-23T19:17:21")
@StaticMetamodel(Module.class)
public class Module_ { 

    public static volatile SingularAttribute<Module, String> modulename;
    public static volatile CollectionAttribute<Module, Group1> group1Collection1;
    public static volatile SingularAttribute<Module, Integer> oid;
    public static volatile SingularAttribute<Module, String> moduleid;
    public static volatile CollectionAttribute<Module, Group1> group1Collection;

}