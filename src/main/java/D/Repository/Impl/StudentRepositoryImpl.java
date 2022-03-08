package D.Repository.Impl;

import D.Entities.Course;
import D.Entities.Prof;
import D.Entities.Student;
import D.Repository.StudentRepository;

import lombok.var;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentRepositoryImpl extends GenericRepositoryImpl<Student,Integer> implements StudentRepository{
    @Override
    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Student ");
        List<Student> students = (List<Student>) q.getResultList();

        return students;
    }

    @Override
    public Student findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Student.class, id);
        }
    }
}
