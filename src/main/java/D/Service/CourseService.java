package D.Service;

import D.Entities.Course;

import java.util.List;

public interface CourseService {
    Integer create (Course course);
    Course findById(Integer id);
    List<Course> findAll();
    void Update(Course course);
    void Delete(Integer id);
}
