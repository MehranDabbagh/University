package D.Repository.Impl;

import D.Entities.Prof;
import D.Entities.Student;
import D.Repository.StudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public Integer create(Student student) {
        String sql="insert into student(firstname,lastname,username,password) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2,student.getLastname());
            preparedStatement.setString(3,student.getUsername());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.execute();
            sql="select id from student where firstname=? and lastname=? and username=? and password=? ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2,student.getLastname());
            preparedStatement.setString(3,student.getUsername());
            preparedStatement.setString(4, student.getPassword());
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student findById(Integer id) {
        String sql="select * from student where id=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
             Student student=new Student(resultSet.getInt("id"),resultSet.getString("firstname"),
                     resultSet.getString("lastname"),resultSet.getString("username"),
                     resultSet.getString("password"));
             return student;
            }
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        String sql="select * from student ";
        List<Student> students=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                Student student=new Student(resultSet.getInt("id"),resultSet.getString("firstname"),
                        resultSet.getString("lastname"),resultSet.getString("username"),
                        resultSet.getString("password"));
              students.add(student);
            }
            return students;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void Update(Student student) {
        String sql="update student set firstname=? ,lastname=?,username=?,password=? where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2,student.getLastname());
            preparedStatement.setString(3,student.getUsername());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setInt(5,student.getId());
            preparedStatement.execute();
        }catch (SQLException e){}
    }

    @Override
    public void Delete(Integer id) {
        String sql="delete  from student where id=?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (SQLException e){}
    }
}
