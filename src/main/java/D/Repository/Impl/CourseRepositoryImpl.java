package D.Repository.Impl;

import D.Entities.Course;
import D.Repository.CourseRepository;
import lombok.var;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseRepositoryImpl extends GenericRepositoryImpl<Course, Integer> implements CourseRepository {
    @Override
    public List<Course> findAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Course ");
        List<Course> courses = (List<Course>) q.getResultList();
        return courses;
    }

    @Override
    public Course findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Course.class, id);
        }
    }
}
