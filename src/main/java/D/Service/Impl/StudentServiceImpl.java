package D.Service.Impl;

import D.Entities.Student;
import D.MyConnection.SessionFactorySingleton;
import D.Repository.Impl.StudentRepositoryImpl;
import D.Service.StudentService;
import lombok.var;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private StudentRepositoryImpl studentRepository;
    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public StudentServiceImpl() {
        studentRepository = new StudentRepositoryImpl();
    }

    @Override
    public Integer login(Student student) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                List<Student> students = studentRepository.findAll();
                List<Student> students1 = students.stream().filter(x -> Objects.equals(x.getUsername(), student.getUsername()) && Objects.equals(x.getPassword(), student.getPassword())).collect(Collectors.toList());
                transaction.commit();
                if (students1.size() > 0) {
                    return students1.get(0).getId();
                }
                return 0;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public Integer create(Student student) {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                Integer id = studentRepository.save(student).getId();
                transaction.commit();
                return id;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Student findById(Integer id) {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                Student student = studentRepository.findById(id);
                transaction.commit();
                return student;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Student> findAll() {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                List<Student> students = studentRepository.findAll();
                transaction.commit();
                return students;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void Update(Student student) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();
            try {
                transaction.begin();
                studentRepository.update(student);
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
                studentRepository.deleteById(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }


}
