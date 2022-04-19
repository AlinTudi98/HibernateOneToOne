package com.alint.springlearning.hibernate.mappings.onetoonedemo.main;

import com.alint.springlearning.hibernate.mappings.onetoonedemo.entities.Instructor;
import com.alint.springlearning.hibernate.mappings.onetoonedemo.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            Instructor tempInstructor =
                    new Instructor("Chad","Darby","darby@luv2code.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail(
                    "http://www.luv2code.com/youtube",
                    "Luv 2 code!!!");

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            /*Instructor tempInstructor =
                    new Instructor("Madhu","Patel","madhu@luv2code.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail(
                    "http://youtube.com",
                    "Guitar");*/

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();

            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
