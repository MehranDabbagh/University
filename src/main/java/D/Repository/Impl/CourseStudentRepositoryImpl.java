package D.Repository.Impl;

import D.Entities.CourseStudent;
import D.Entities.Employee;
import lombok.var;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CourseStudentRepositoryImpl extends GenericRepositoryImpl<CourseStudent,Integer>{
    public List<CourseStudent> findAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Employee ");
        List<CourseStudent> courseStudents = (List<CourseStudent>) q.getResultList();

        return courseStudents;
    }


    public CourseStudent findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(CourseStudent.class, id);
        }
    }
}
