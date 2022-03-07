package D.Service.Impl;

import D.Entities.Course;
import D.Entities.Student;
import D.Repository.Impl.CourseStudentRepositoryImpl;
import D.Service.CourseStudentService;

import java.util.List;

public class CourseStudentServiceImpl implements CourseStudentService {
    private CourseStudentRepositoryImpl courseStudentRepository;

    public CourseStudentServiceImpl() {
        courseStudentRepository=new CourseStudentRepositoryImpl();
    }


    @Override
    public void unitSelecting(Student student, Course course) {
        courseStudentRepository.create(student,course);
    }

    @Override
    public void scoring(Student student, Course course,Integer score) {
courseStudentRepository.scoring(student,course,score);
    }

    @Override
    public List<Integer> courseByStudentId( Integer studentId) {
        return courseStudentRepository.findCourseIdByStudentId(studentId);
    }

    @Override
    public Integer score(Student student, Course course) {
        return courseStudentRepository.score(student,course);
    }

    @Override
    public void Delete(Student student, Course course) {
        courseStudentRepository.delete(student,course);
    }


}
