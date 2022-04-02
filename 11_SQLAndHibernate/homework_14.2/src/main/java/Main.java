import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    private static final String pathFile = "hibernate.cfg.xml";

    public static void main(String[] args) {
        try {
            try(SessionFactory sessionFactory = createSession(pathFile)){
                try(Session session = sessionFactory.openSession()){
                    for(int i = 1; i <= 45; i++){
                        Course course = session.get(Course.class, i);
                        System.out.println(i + ". На курсе " + course.getName() + " - " +
                                course.getStudentsCount() + " студентов.");
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static SessionFactory createSession (String pathFile){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(pathFile).build();

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }
}
