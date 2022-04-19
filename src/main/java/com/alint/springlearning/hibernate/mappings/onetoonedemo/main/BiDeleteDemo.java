package com.alint.springlearning.hibernate.mappings.onetoonedemo.main;

import com.alint.springlearning.hibernate.mappings.onetoonedemo.entities.Instructor;
import com.alint.springlearning.hibernate.mappings.onetoonedemo.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BiDeleteDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
          session.beginTransaction();
          int detailId = 3;
          InstructorDetail instructorDetail = session.get(InstructorDetail.class,detailId);

          Instructor instructor = instructorDetail.getInstructor();
          instructor.setInstructorDetail(null);

          session.delete(instructorDetail);
          session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
