package D.Repository.Impl;

import D.Entities.Course;
import D.Entities.Employee;
import D.Repository.EmployeeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public Integer create(Employee employee) {
        String sql="insert into employee(firstname,lastname,username,password) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2,employee.getLastname());
            preparedStatement.setString(3,employee.getUsername());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.execute();
            sql="select id from employee where firstname=? and lastname=? and username=? and password=? ";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2,employee.getLastname());
            preparedStatement.setString(3,employee.getUsername());
            preparedStatement.setString(4, employee.getPassword());
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee findById(Integer id) {
        String sql="select * from employee where id=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                Employee employee=new Employee(resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                return employee;
            }
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        String sql="select * from employee ";
        List<Employee> employees=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee=new Employee(resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("username"),
                        resultSet.getString("password"));
                employees.add(employee);
            }
            return employees;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void Update(Employee employee) {
        String sql="update employee set firstname=? ,lastname=?,username=?,password=? where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstname());
            preparedStatement.setString(2,employee.getLastname());
            preparedStatement.setString(3,employee.getUsername());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.setInt(5,employee.getId());
            preparedStatement.execute();
        }catch (SQLException e){}
    }

    @Override
    public void Delete(Integer id) {
        String sql="delete  from employee where id=?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (SQLException e){}
    }
}
