package D.Service.Impl;

import D.Entities.Course;
import D.MyConnection.SessionFactorySingleton;
import D.Repository.Impl.CourseRepositoryImpl;
import D.Service.CourseService;
import lombok.var;
import org.hibernate.SessionFactory;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseRepositoryImpl courseRepository;
    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    public CourseServiceImpl() {
        courseRepository=new CourseRepositoryImpl();
    }


    @Override
    public Integer create(Course course) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                Integer id=courseRepository.save(course).getId();
                transaction.commit();
                return id;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public Course findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                Course course = courseRepository.findById(id);
                transaction.commit();
                return course;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Course> findAll() {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                List<Course> courses = courseRepository.findAll();
                transaction.commit();
                return courses;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void Update(Course course) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                courseRepository.update(course);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void Delete(Integer id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                courseRepository.deleteById(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
