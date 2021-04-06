package com.example.USP.DAO;

import com.example.USP.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "CINEMA", "a123");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public List<Movie> getMovies(ResultSet rs) throws SQLException {
        List<Movie> movieList=new ArrayList<>();
        while (rs.next()){
            Movie movie=new Movie();
        }
        return movieList;
    }
    public List<Movie> selectMovies(){
        List<Movie> movies=new ArrayList<>();
        String sqlMovie="";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlMovie)){
            ResultSet resultSet=preparedStatement.executeQuery();
            movies=getMovies(resultSet);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movies;
    }
}
