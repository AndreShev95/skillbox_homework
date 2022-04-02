import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    private static final String pathFile = "hibernate.cfg.xml";

    public static void main(String[] args) {
        try {
            try(SessionFactory sessionFactory = createSession(pathFile)){
                try(Session session = sessionFactory.openSession()){

                      Transaction transaction = session.beginTransaction();

//                      session.createQuery("DELETE FROM LinkedPurchaseList")
//                              .executeUpdate();

                    fillLinkedPurchaseList(session);

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

    public static void fillLinkedPurchaseList (Session session){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        List<PurchaseList> listQuery = session.createQuery(query).getResultList();

        for (PurchaseList purchase : listQuery){
            LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
            linkedPurchase.setPrice(purchase.getPrice());
            linkedPurchase.setSubscriptionDate(purchase.getSubscriptionDate());
            linkedPurchase.setCourseId(getCourseId(purchase.getCourseName(), session));
            linkedPurchase.setStudentId(getStudentId(purchase.getStudentName(), session));
            linkedPurchase.setId(new KeyLinkedPurchaseList
                    (linkedPurchase.getStudentId(),linkedPurchase.getCourseId()));
            session.save(linkedPurchase);
        }
    }

    public static Integer getCourseId (String courseName, Session session){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root).where(builder.equal(root.get("name"),courseName));
        List<Course> listCourses = session.createQuery(query).getResultList();
        return listCourses.get(0).getId();
    }

    public static Integer getStudentId (String studentName, Session session){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        query.select(root).where(builder.equal(root.get("name"),studentName));
        List<Student> listCourses = session.createQuery(query).getResultList();
        return listCourses.get(0).getId();
    }
}
