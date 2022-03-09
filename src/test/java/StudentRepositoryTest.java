import D.Entities.Student;
import D.Repository.Impl.StudentRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRepositoryTest {
    private StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
    ;

    @BeforeAll
    public static void setup() {

    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void cleanUp() {
        List<Student> students = studentRepository.findAll();
        if (students != null) {
            for (Student student : students
            ) {
                studentRepository.delete(student);
            }
        }
    }

    @Test
    public void CreateTest() {
        // Arrange -->

        Student student = new Student();
        // Act
        Integer accId = studentRepository.save(student).getId();

        // Assert
        Student loadedStudent = studentRepository.findById(accId);
        assertAll(
                () -> assertNotNull(accId),
                () -> assertNotNull(loadedStudent),
                () -> assertEquals(accId, loadedStudent.getId())
        );
    }

    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        studentRepository.save(new Student());
        studentRepository.save(new Student());
        studentRepository.save(new Student());


        // Act
        List<Student> students = studentRepository.findAll();

        // Assert
        assertThat(students).hasSize(3);

    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange
        Integer accId = studentRepository.save(new Student()).getId();
        Student student = studentRepository.findById(accId);
        // Act
        student.setFirstname("newOne");
        studentRepository.update(student);
        // Assert
        Student loadedStudent = studentRepository.findById(accId);
        assertThat(loadedStudent.getFirstname()).isEqualTo("newOne");
        assertThat(loadedStudent.getId()).isEqualTo(accId);
    }

    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        Student student = new Student();
        studentRepository.save(student);
        // Act
        studentRepository.delete(student);
        // Assert
        assertThat(studentRepository.findAll()).isEmpty();
    }


}
