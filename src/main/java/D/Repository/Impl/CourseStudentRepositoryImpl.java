package D.Repository.Impl;

import D.Entities.Course;
import D.Entities.Student;
import D.Repository.CourseStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseStudentRepositoryImpl implements CourseStudentRepository {
    @Override
    public void create(Student student, Course course) {
        String sql="insert into studentscourses (courseid,studentid,score) values (?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,course.getId());
            preparedStatement.setInt(2,student.getId());
            preparedStatement.setInt(3,0);
            preparedStatement.execute();
        }catch (SQLException e){}
    }

    @Override
    public void scoring(Student student, Course course, Integer score) {
        String sql="update  studentscourses set score=? where courseid=? and studentid=? ";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,score);
            preparedStatement.setInt(2,course.getId());
            preparedStatement.setInt(3,student.getId());
            preparedStatement.execute();
        }catch (SQLException e){}
    }

    @Override
    public List<Integer> findCourseIdByStudentId(Integer id) {
        List<Integer> coursesId=new ArrayList<>();
        String sql="select * from studentscourses where studentid=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                coursesId.add(resultSet.getInt("courseid"));
            }
            return coursesId;
        }catch (SQLException e){}
        return null;
    }

    @Override
    public void delete(Student student, Course course) {
        String sql="delete from studentscourses where courseid=? and studentid=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,course.getId());
            preparedStatement.setInt(2,student.getId());
         preparedStatement.execute();
        }catch (SQLException e){}

    }

    @Override
    public Integer score(Student student, Course course) {
        String sql="select score from studentscourses where studentid=? and courseid=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setInt(2,course.getId());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("score");
            }

        }catch (SQLException e){}
        return 0;
    }
}
