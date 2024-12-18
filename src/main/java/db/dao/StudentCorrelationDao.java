package db.dao;

import db.utils.Hibernate;
import db.entities.StudentCorrelationEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentCorrelationDao {

    public void save(StudentCorrelationEntity student) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<StudentCorrelationEntity> getAll() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("from StudentCorrelationEntity", StudentCorrelationEntity.class).list();
        }
    }

    public StudentCorrelationEntity findByName(String name) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.createQuery("from StudentCorrelationEntity where name = :name", StudentCorrelationEntity.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }
}
