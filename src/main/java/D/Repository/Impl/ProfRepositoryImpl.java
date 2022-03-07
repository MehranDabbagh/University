package D.Repository.Impl;

import D.Entities.Employee;
import D.Entities.Prof;
import D.Repository.ProfRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProfRepositoryImpl implements ProfRepository {

    @Override
    public Integer create(Prof prof) {
        String sql="insert into prof(firstname,lastname,username,password,jobtype) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, prof.getFirstname());
            preparedStatement.setString(2,prof.getLastname());
            preparedStatement.setString(3,prof.getUsername());
            preparedStatement.setString(4, prof.getPassword());
            preparedStatement.setString(5, prof.getType());
            preparedStatement.execute();
            sql="select id from prof where firstname=? and lastname=? and username=? and password=? and jobtype=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, prof.getFirstname());
            preparedStatement.setString(2,prof.getLastname());
            preparedStatement.setString(3,prof.getUsername());
            preparedStatement.setString(4, prof.getPassword());
            preparedStatement.setString(5, prof.getType());
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Prof findById(Integer id) {
        String sql="select * from prof where id=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
       Prof prof=new Prof(resultSet.getInt("id"),
               resultSet.getString("firstname"),resultSet.getString("lastname"),
               resultSet.getString("username"),resultSet.getString("password"),
               resultSet.getString("jobtype"));
                return prof;
            }
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public List<Prof> findAll() {
        String sql="select * from prof";
        List<Prof> profs=new ArrayList<>();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                Prof prof=new Prof(resultSet.getInt("id"),
                        resultSet.getString("firstname"),resultSet.getString("lastname"),
                        resultSet.getString("username"),resultSet.getString("password"),
                        resultSet.getString("jobtype"));
              profs.add(prof);
            }
            return profs;
        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public void Update(Prof prof) {
        String sql="update prof set firstname=? ,lastname=?,username=?,password=?, jobtype=? where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, prof.getFirstname());
            preparedStatement.setString(2,prof.getLastname());
            preparedStatement.setString(3,prof.getUsername());
            preparedStatement.setString(4, prof.getPassword());
            preparedStatement.setString(5, prof.getType());
            preparedStatement.setInt(6,prof.getId());
            preparedStatement.execute();
        }catch (SQLException e){}
    }

    @Override
    public void Delete(Integer id) {
        String sql="delete  from prof where id=?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (SQLException e){}
    }
}
