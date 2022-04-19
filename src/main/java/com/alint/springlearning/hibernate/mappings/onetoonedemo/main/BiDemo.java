package com.alint.springlearning.hibernate.mappings.onetoonedemo.main;

import com.alint.springlearning.hibernate.mappings.onetoonedemo.entities.Instructor;
import com.alint.springlearning.hibernate.mappings.onetoonedemo.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BiDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
          session.beginTransaction();
          int detailId = 2999;
          InstructorDetail instructorDetail = session.get(InstructorDetail.class,detailId);

            System.out.println("Got instructor detail:\n\t" + instructorDetail);

            System.out.println("The associated instructor: \n\t" + instructorDetail.getInstructor());
          session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
