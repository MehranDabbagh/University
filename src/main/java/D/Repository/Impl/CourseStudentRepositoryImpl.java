package D.Repository.Impl;

import D.Entities.CourseStudent;
import D.Entities.Employee;
import D.Repository.CourseStudentRepository;
import lombok.var;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CourseStudentRepositoryImpl extends GenericRepositoryImpl<CourseStudent, Integer> implements CourseStudentRepository {
    @Override
    public List<CourseStudent> findAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from CourseStudent ");
        List<CourseStudent> courseStudents = (List<CourseStudent>) q.getResultList();
        return courseStudents;
    }

    @Override
    public CourseStudent findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(CourseStudent.class, id);
        }
    }
}
