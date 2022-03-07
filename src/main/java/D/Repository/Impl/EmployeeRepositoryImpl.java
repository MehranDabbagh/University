package D.Repository.Impl;

import D.Entities.Course;
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

public class EmployeeRepositoryImpl extends GenericRepositoryImpl<Employee, Integer> {

    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Employee ");
        List<Employee> employees = (List<Employee>) q.getResultList();

        return employees;
    }


    public Employee findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Employee.class, id);
        }
    }
}
