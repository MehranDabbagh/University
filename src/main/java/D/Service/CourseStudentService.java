package D.Service;

import D.Entities.Course;
import D.Entities.Student;

import java.util.List;

public interface CourseStudentService {
    void unitSelecting(Student student, Course course);
    void scoring(Student student, Course course,Integer score);
    List<Integer> courseByStudentId( Integer studentId);
    Integer score(Student student,Course course);
    void Delete(Student student,Course course);

}
