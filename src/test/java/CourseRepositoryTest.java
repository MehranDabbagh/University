
import D.Entities.Course;
import D.Entities.Prof;
import D.MyConnection.SessionFactorySingleton;
import D.Repository.Impl.CourseRepositoryImpl;
import D.Repository.Impl.ProfRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseRepositoryTest {
    private CourseRepositoryImpl courseRepository= new CourseRepositoryImpl();
    private ProfRepositoryImpl profRepository= new ProfRepositoryImpl();
    @BeforeAll
    public static void setup() {
        SessionFactory sessionFactory= SessionFactorySingleton.getInstance();

    }
    @BeforeEach
    public void beforeEach()  {

    }
    @AfterEach
    public void cleanUp()  {
        List<Prof> profs=profRepository.findAll();
        if(profs!=null) {
            for (Prof prof : profs
            ) {
                profRepository.delete(prof);
            }
        }
        List<Course> courseList=courseRepository.findAll();
        if(courseList!=null) {
            for (Course course : courseList
            ) {
                courseRepository.delete(course);
            }
        }
    }
    @Test
    public void CreateTest() {
        // Arrange -->

        Course course = new Course("riazi",null,1395,1,4);
        // Act
        Integer accId = courseRepository.save(course).getId();

        // Assert
        Course loadedCourse = courseRepository.findById(accId);
        assertAll(
                () -> assertNotNull(accId),
                () -> assertNotNull(loadedCourse),
                () -> assertEquals(accId, loadedCourse.getId()),
                () -> assertEquals("riazi", loadedCourse.getName()),
                () ->  assertEquals(1395,loadedCourse.getYear()),
                () ->  assertEquals(1,loadedCourse.getTerm()),
                () ->  assertEquals(4,loadedCourse.getUnit()),
                () ->  assertEquals(null,loadedCourse.getProf())
        );
    }
    @Test
    public void findAllTest() throws SQLException {
        // Arrange
        courseRepository.save( new Course("riazi",null,1395,1,4));
        courseRepository.save( new Course("riazi2",null,1395,2,4));
        courseRepository.save( new Course("shimi",null,1395,2,3));


        // Act
        List<Course> courses = courseRepository.findAll();

        // Assert
        assertThat(courses).hasSize(3);

    }

    @Test
    public void updateTest() throws SQLException {
        // Arrange
        Integer accId = courseRepository.save( new Course("riazi",null,1395,1,4)).getId();
        Course course= courseRepository.findById(accId);
        // Act
        course.setName("newOne");
        courseRepository.update(course);
        // Assert
        Course loadedCourse = courseRepository.findById(accId);
        assertThat(loadedCourse.getName()).isEqualTo("newOne");
        assertThat(loadedCourse.getId()).isEqualTo(accId);
    }
    @Test
    public void deleteTest() throws SQLException {
        // Arrange
        Course course =new Course("riazi",null,1395,1,4);
        courseRepository.save(course);
        // Act
        courseRepository.delete(course);
        // Assert
        assertThat(courseRepository.findAll()).isEmpty();
    }


}
