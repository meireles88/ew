package entityClasses;

import entityClasses.Ideia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-23T19:17:21")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> titulo;
    public static volatile SingularAttribute<Categoria, Integer> oid;
    public static volatile SingularAttribute<Categoria, Date> dataedicao;
    public static volatile SingularAttribute<Categoria, Date> datainsercao;
    public static volatile CollectionAttribute<Categoria, Ideia> ideiaCollection;
    public static volatile SingularAttribute<Categoria, String> descricao;

}