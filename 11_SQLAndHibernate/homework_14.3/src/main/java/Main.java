import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    private static final String pathFile = "hibernate.cfg.xml";
    private static int courseNum = 10;

    public static void main(String[] args) {
        try {
            try(SessionFactory sessionFactory = createSession(pathFile)){
                try(Session session = sessionFactory.openSession()){
                    Transaction transaction = session.beginTransaction();

                    Course course = session.get(Course.class,courseNum);
                    List<Student> studentList = course.getStudents();

                    System.out.printf("Данные по курсу %s:%n",course.getName());
                    System.out.printf("Имя преподавателя - %s%n",course.getTeacher().getName());
                    System.out.println("Список студентов с их подписками:");
                    studentList.forEach(student -> {
                        List<Subscription> subscriptions = student.getSubscriptions();
                        System.out.println(student.getName());
                        subscriptions.forEach(subscription -> {
                            System.out.print(subscription.getSubscriptionDate() + " - ");
                            System.out.println(subscription.getCourse().getName());
                        });
                    });

                    KeyPurchaseList purchaseKey = new KeyPurchaseList();
                    purchaseKey.setStudentName("Гудков Харитон");
                    purchaseKey.setCourseName("Управление продуктом");

                    PurchaseList purchaseList = session.get(PurchaseList.class, purchaseKey);
                    System.out.println(purchaseList.getPrice());

                    transaction.commit();
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
