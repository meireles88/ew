package entityClasses;

import entityClasses.Ideia;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-23T19:17:21")
@StaticMetamodel(Tag.class)
public class Tag_ { 

    public static volatile SingularAttribute<Tag, Integer> oid;
    public static volatile CollectionAttribute<Tag, Ideia> ideiaCollection;
    public static volatile SingularAttribute<Tag, String> nome;

}