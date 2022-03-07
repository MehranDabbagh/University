package D.Repository;

import D.Entities.Course;
import D.Entities.Student;
import D.MyConnection.PostgresConnection;

import java.sql.Connection;
import java.util.List;

public interface CourseStudentRepository {
    Connection connection= PostgresConnection.getInstance().getConnection();
    void create(Student student, Course course);
    void scoring(Student student,Course course,Integer score);
    List<Integer> findCourseIdByStudentId(Integer id);
    void delete(Student student,Course course);
    Integer score(Student student,Course course);

}
