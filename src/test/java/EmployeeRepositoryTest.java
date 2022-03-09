import D.Entities.Course;
import D.Entities.Employee;
import D.Repository.Impl.EmployeeRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeRepositoryTest {
    private EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl();;
    @BeforeAll
    public static void setup() {

    }
    @BeforeEach
    public void beforeEach()  {

    }
    @AfterEach
    public void cleanUp()  {
        List<Employee> employees = employeeRepository.findAll();
        if(employees !=null) {
            for (Employee employee : employees
            ) {
                employeeRepository.delete(employee);
            }
        }
    }
    @Test
    public void CreateTest() {
        // Arrange -->

        Employee employee = new Employee();
        // Act
        Integer accId = employeeRepository.save(employee).getId();

        // Assert
        Employee loadedEmployee = employeeRepository.findById(accId);
        assertAll(
                () -> assertNotNull(accId),
                () -> assertNotNull(loadedEmployee),
                () -> assertEquals(accId, loadedEmployee.getId())
        );
    }
    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        employeeRepository.save( new Employee());
        employeeRepository.save( new Employee());
        employeeRepository.save( new Employee());


        // Act
        List<Employee> employees = employeeRepository.findAll();

        // Assert
        assertThat(employees).hasSize(3);

    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange
        Integer accId = employeeRepository.save( new Employee()).getId();
        Employee employee = employeeRepository.findById(accId);
        // Act
        employee.setUsername("newOne");
        employeeRepository.update(employee);
        // Assert
        Employee loadedEmployee = employeeRepository.findById(accId);
        assertThat(loadedEmployee.getUsername()).isEqualTo("newOne");
        assertThat(loadedEmployee.getId()).isEqualTo(accId);
    }
    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        Employee employee =new Employee();
        employeeRepository.save(employee);
        // Act
        employeeRepository.delete(employee);
        // Assert
        assertThat(employeeRepository.findAll()).isEmpty();
    }


}
