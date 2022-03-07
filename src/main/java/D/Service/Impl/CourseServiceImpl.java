package D.Service.Impl;

import D.Entities.Course;
import D.Repository.Impl.CourseRepositoryImpl;
import D.Service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseRepositoryImpl courseRepository;

    public CourseServiceImpl() {
        courseRepository=new CourseRepositoryImpl();
    }


    @Override
    public Integer create(Course course) {
        return courseRepository.create(course);
    }

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void Update(Course course) {
courseRepository.Update(course);
    }

    @Override
    public void Delete(Integer id) {
courseRepository.Delete(id);
    }
}
