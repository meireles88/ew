package entityClasses;

import entityClasses.Categoria;
import entityClasses.Grupoorcamental;
import entityClasses.Tag;
import entityClasses.User;
import entityClasses.Voto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-23T19:17:21")
@StaticMetamodel(Ideia.class)
public class Ideia_ { 

    public static volatile SingularAttribute<Ideia, String> titulo;
    public static volatile SingularAttribute<Ideia, Grupoorcamental> grupoorcamentalOid;
    public static volatile SingularAttribute<Ideia, Integer> oid;
    public static volatile SingularAttribute<Ideia, Date> dataedicao;
    public static volatile SingularAttribute<Ideia, Date> datainsercao;
    public static volatile SingularAttribute<Ideia, Integer> quantia;
    public static volatile CollectionAttribute<Ideia, Voto> votoCollection;
    public static volatile SingularAttribute<Ideia, Boolean> aprovada;
    public static volatile SingularAttribute<Ideia, Integer> rating;
    public static volatile CollectionAttribute<Ideia, Tag> tagCollection;
    public static volatile SingularAttribute<Ideia, String> descricao;
    public static volatile SingularAttribute<Ideia, Categoria> categoriaOid;
    public static volatile SingularAttribute<Ideia, User> userOid;

}