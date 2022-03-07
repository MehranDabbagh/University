package D.Repository.Impl;

import D.Entities.Course;
import D.Repository.CourseRepository;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseRepositoryImpl implements CourseRepository {
    private List<Course> courses;
    public CourseRepositoryImpl() {
        courses=new ArrayList<>();
    }


    @Override
    public Integer create(Course course) {
        String sql="insert into course(coursename,profid,yearofcourse,term,unit) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,course.getName());
            preparedStatement.setInt(2,course.getProfid());
            preparedStatement.setInt(3,course.getYear());
            preparedStatement.setInt(4,course.getTerm());
            preparedStatement.setInt(5,course.getUnit());
            preparedStatement.execute();
            sql="select id from course where coursename=? and profid=? and yearofcourse=? and term=? ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,course.getName());
            preparedStatement.setInt(2,course.getProfid());
            preparedStatement.setInt(3,course.getYear());
            preparedStatement.setInt(4,course.getTerm());
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
            return resultSet.getInt("id");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Course findById(Integer id) {
        String sql="select * from course where id=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                Course course = new Course(id, resultSet.getString("coursename"),
                        resultSet.getInt("profid"), resultSet.getInt("yearofcourse"),
                        resultSet.getInt("term"), resultSet.getInt("unit"));
                return course;
            }
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public List<Course> findAll() {
        String sql="select * from course ";
        List<Course> courses=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                Course course = new Course(resultSet.getInt("id"), resultSet.getString("coursename"),
                        resultSet.getInt("profid"), resultSet.getInt("yearofcourse"),
                        resultSet.getInt("term"), resultSet.getInt("unit"));
             courses.add(course);
            }
            return courses;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void Update(Course course) {
String sql="update course set coursename=? ,profid=?,yearofcourse=?,term=?,unit=? where id=?";
try {
    PreparedStatement preparedStatement=connection.prepareStatement(sql);
    preparedStatement.setString(1,course.getName());
    preparedStatement.setInt(2,course.getProfid());
    preparedStatement.setInt(3,course.getYear());
    preparedStatement.setInt(4,course.getTerm());
    preparedStatement.setInt(5,course.getUnit());
    preparedStatement.setInt(6,course.getId());
    preparedStatement.execute();
}catch (SQLException e){}
    }

    @Override
    public void Delete(Integer id) {
String sql="delete  from course where id=?";
try {
    PreparedStatement preparedStatement= connection.prepareStatement(sql);
    preparedStatement.setInt(1,id);
    preparedStatement.execute();
}catch (SQLException e){}
    }
}
