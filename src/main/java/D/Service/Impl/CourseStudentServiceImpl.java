package D.Service.Impl;

import D.Entities.Course;
import D.Entities.CourseStudent;
import D.Entities.Student;
import D.Repository.Impl.CourseStudentRepositoryImpl;
import D.Repository.Impl.StudentRepositoryImpl;
import D.Service.CourseStudentService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseStudentServiceImpl implements CourseStudentService {
    private CourseStudentRepositoryImpl courseStudentRepository;

    public CourseStudentServiceImpl() {
        courseStudentRepository = new CourseStudentRepositoryImpl();

    }


    @Override
    public void unitSelecting(Student student, Course course) {
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setStudent(student.getId());
        courseStudent.setCourse(course.getId());
        courseStudent.setScore(0);
        courseStudentRepository.save(courseStudent);
    }

    @Override
    public void scoring(Student student, Course course, Integer score) {
        List<CourseStudent> courseStudents = courseStudentRepository.findAll();
        for (CourseStudent coursestudent:courseStudents
             ) {
            if(Objects.equals(coursestudent.getCourse(), course.getId()) && Objects.equals(coursestudent.getStudent(), student.getId())){
                coursestudent.setScore(score);
                courseStudentRepository.update(coursestudent);
            }
        }



    }

    @Override
    public List<Integer> courseByStudentId(Integer studentId) {
        List<CourseStudent> courseStudents = courseStudentRepository.findAll();
        List<Integer> coursesId=new ArrayList<>();
        for (CourseStudent coursestudent:courseStudents
        ) {
            if( coursestudent.getStudent()==studentId){
              coursesId.add(coursestudent.getCourse());
            }
        }
        return coursesId;
    }

    @Override
    public Integer score(Student student, Course course) {

        List<CourseStudent> courseStudents = courseStudentRepository.findAll();
        for (CourseStudent coursestudent:courseStudents
        ) {
            if(coursestudent.getCourse()==course.getId() && coursestudent.getStudent()==student.getId()){
             return coursestudent.getScore();
            }
        }
        return 0;

    }

    @Override
    public void Delete(Student student, Course course) {
        List<CourseStudent> courseStudents = courseStudentRepository.findAll();
        for (CourseStudent coursestudent:courseStudents
        ) {
            if(coursestudent.getCourse()==course.getId() && coursestudent.getStudent()==student.getId()){
              courseStudentRepository.delete(coursestudent);
            }
        }
    }


}
