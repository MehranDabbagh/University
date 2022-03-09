package D.Repository.Impl;

import D.Entities.Course;
import D.Entities.CourseStudent;
import D.Entities.Employee;
import D.Repository.EmployeeRepository;
import lombok.var;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeRepositoryImpl extends GenericRepositoryImpl<Employee, Integer> implements EmployeeRepository {
    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Employee ",Employee.class);
        return q.getResultList();
    }

    @Override
    public Employee findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Employee.class, id);
        }
    }

}
