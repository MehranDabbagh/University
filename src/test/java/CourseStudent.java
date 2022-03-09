

import D.Repository.Impl.CourseStudentRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CourseStudent {
    private CourseStudentRepositoryImpl courseStudentRepository = new CourseStudentRepositoryImpl();
    ;

    @BeforeAll
    public static void setup() {

    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void cleanUp() {
        List<D.Entities.CourseStudent> courseStudents = courseStudentRepository.findAll();
        if (courseStudents != null) {
            for (D.Entities.CourseStudent courseStudent : courseStudents
            ) {
                courseStudentRepository.delete(courseStudent);
            }
        }
    }

    @Test
    public void CreateTest() {
        // Arrange -->

        D.Entities.CourseStudent courseStudent = new D.Entities.CourseStudent(null,null,0);
        // Act
        Integer accId = courseStudentRepository.save(courseStudent).getId();

        // Assert
        D.Entities.CourseStudent loadedCourseStudent = courseStudentRepository.findById(accId);
        assertAll(
                () -> assertNotNull(accId),
                () -> assertNotNull(loadedCourseStudent),
                () -> assertEquals(accId, loadedCourseStudent.getId())
        );
    }

    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        courseStudentRepository.save(new D.Entities.CourseStudent(null,null,0));
        courseStudentRepository.save(new D.Entities.CourseStudent(null,null,0));
        courseStudentRepository.save(new D.Entities.CourseStudent(null,null,0));


        // Act
        List<D.Entities.CourseStudent> courseStudents = courseStudentRepository.findAll();

        // Assert
        assertThat(courseStudents).hasSize(3);

    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange
        Integer accId = courseStudentRepository.save(new D.Entities.CourseStudent(null,null,0)).getId();
        D.Entities.CourseStudent courseStudent = courseStudentRepository.findById(accId);
        // Act
        courseStudent.setScore(15);
        courseStudentRepository.update(courseStudent);
        // Assert
        D.Entities.CourseStudent loadedCourseStudent = courseStudentRepository.findById(accId);
        assertThat(loadedCourseStudent.getScore()).isEqualTo(15);
        assertThat(loadedCourseStudent.getId()).isEqualTo(accId);
    }

    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        D.Entities.CourseStudent courseStudent = new D.Entities.CourseStudent(null,null,0);
        courseStudentRepository.save(courseStudent);
        // Act
        courseStudentRepository.delete(courseStudent);
        // Assert
        assertThat(courseStudentRepository.findAll()).isEmpty();
    }
}
