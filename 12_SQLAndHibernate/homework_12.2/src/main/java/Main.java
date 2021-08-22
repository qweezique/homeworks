import Hibernate.LinkedPurchaseList;
import Hibernate.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession()) {

            String sql = "insert into linkedpurchaselist(student_id, course_id, course_price, purchase_date)\n" +
                    "select students.id,courses.id,courses.price\n, purchaselist.subscription_date" +
                    " from courses \n" +
                    " join purchaselist on courses.name = course_name\n" +
                    " join students on students.name = student_name;";

            session.beginTransaction();
            int str = session.createNativeQuery(sql).executeUpdate();
            System.out.println(str);

            Student student1 = session.get(Student.class, 1);
            List <LinkedPurchaseList> linkedPurchaseList_st1 =
            student1.getPurchaseList();

            System.out.println(student1.getName() + " подписан на курсы:");
            linkedPurchaseList_st1.forEach(System.out::println);
            System.out.println("*************");
            System.out.println("Стоимость всех купленных курсов: \n" +
                    linkedPurchaseList_st1.stream().mapToInt(LinkedPurchaseList::getPrice).sum());
            session.getTransaction().commit();
            session.close();
        }
    }
}