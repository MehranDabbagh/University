package D.Service.Impl;

import D.Entities.Employee;
import D.MyConnection.SessionFactorySingleton;
import D.Repository.Impl.EmployeeRepositoryImpl;
import D.Service.EmployeeService;
import lombok.var;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepositoryImpl employeeRepository;
    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public EmployeeServiceImpl() {
        employeeRepository = new EmployeeRepositoryImpl();
    }


    @Override
    public Integer login(Employee employee) {
        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                List<Employee> employees = employeeRepository.findAll();
                List<Employee> employees1 = employees.stream().filter(x -> Objects.equals(x.getUsername(), employee.getUsername()) && Objects.equals(x.getPassword(), employee.getPassword())).collect(Collectors.toList());
                transaction.commit();
                if (employees1.size() > 0) {
                    return employees1.get(0).getId();
                }
                return 0;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }

    }

    @Override
    public Integer create(Employee employee) {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                Integer id = employeeRepository.save(employee).getId();
                transaction.commit();
                return id;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Employee findById(Integer id) {


        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                Employee employee = employeeRepository.findById(id);
                transaction.commit();
                return employee;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Employee> findAll() {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                List<Employee> employees = employeeRepository.findAll();
                transaction.commit();
                return employees;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void Update(Employee employee) {

        try (var session = sessionFactory.openSession()) {
            var transaction = sessionFactory.getCurrentSession().getTransaction();

            try {
                transaction.begin();
                employeeRepository.update(employee);
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
                employeeRepository.deleteById(id);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
