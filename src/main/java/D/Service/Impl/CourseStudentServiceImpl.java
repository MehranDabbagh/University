package D.Service.Impl;

import D.Entities.Course;
import D.Entities.CourseStudent;
import D.Entities.Student;
import D.MyConnection.SessionFactorySingleton;
import D.Repository.Impl.CourseStudentRepositoryImpl;
import D.Repository.Impl.StudentRepositoryImpl;
import D.Service.CourseStudentService;
import lombok.var;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseStudentServiceImpl implements CourseStudentService {
    private CourseStudentRepositoryImpl courseStudentRepository;
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public CourseStudentServiceImpl() {
        courseStudentRepository = new CourseStudentRepositoryImpl();

    }


    @Override
    public void unitSelecting(Student student, Course course) {
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student.getId());
        courseStudent.setCourse(course.getId());
        courseStudent.setScore(0);
        try (var session = sessionFactory.openSession()) {
            var transaction =sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                courseStudentRepository.save(courseStudent);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public void scoring(Student student, Course course, Integer score) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                List<CourseStudent> courseStudents = courseStudentRepository.findAll();
                for (CourseStudent coursestudent : courseStudents
                ) {
                    if (Objects.equals(coursestudent.getCourse(), course.getId()) && Objects.equals(coursestudent.getStudent(), student.getId())) {
                        coursestudent.setScore(score);
                        courseStudentRepository.update(coursestudent);
                    }
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }


    }

    @Override
    public List<Integer> courseByStudentId(Integer studentId) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                List<CourseStudent> courseStudents = courseStudentRepository.findAll();
                List<Integer> coursesId = new ArrayList<>();
                for (CourseStudent coursestudent : courseStudents
                ) {
                    if (coursestudent.getStudent() == studentId) {
                        coursesId.add(coursestudent.getCourse());
                    }
                }
                transaction.commit();
                return coursesId;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public Integer score(Student student, Course course) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                List<CourseStudent> courseStudents = courseStudentRepository.findAll();
                for (CourseStudent coursestudent : courseStudents
                ) {
                    if (coursestudent.getCourse() == course.getId() && coursestudent.getStudent() == student.getId()) {
                        return coursestudent.getScore();
                    }
                }
                transaction.commit();
                return 0;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }


    }

    @Override
    public void Delete(Student student, Course course) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                List<CourseStudent> courseStudents = courseStudentRepository.findAll();
                for (CourseStudent coursestudent : courseStudents
                ) {
                    if (coursestudent.getCourse() == course.getId() && coursestudent.getStudent() == student.getId()) {
                        courseStudentRepository.delete(coursestudent);
                    }
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }


}
