package D.Service.Impl;

import D.Entities.Employee;
import D.Entities.Prof;
import D.Repository.Impl.EmployeeRepositoryImpl;
import D.Service.EmployeeService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepositoryImpl employeeRepository;

    public EmployeeServiceImpl() {
        employeeRepository=new EmployeeRepositoryImpl();
    }


    @Override
    public Integer login(Employee employee) {
        List<Employee> employees =  employeeRepository.findAll();
        List<Employee> employees1=      employees.stream().filter(x-> Objects.equals(x.getUsername(), employee.getUsername()) && Objects.equals(x.getPassword(), employee.getPassword())).collect(Collectors.toList());
        if(employees1.size()>0){
            return employees1.get(0).getId();
        }
        return 0;
    }

    @Override
    public Integer create(Employee employee) {
        return employeeRepository.create(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void Update(Employee employee) {
employeeRepository.Update(employee);
    }

    @Override
    public void Delete(Integer id) {
employeeRepository.Delete(id);
    }
}
