package com.example.USP.DAO;

import com.example.USP.model.Movie;
import sun.jvm.hotspot.runtime.ResultTypeFinder;

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
            movie.setName_movie(rs.getString("MOVIE_NAME"));
        }
        return movieList;
    }
    public List<Movie> getCities(ResultSet rs) throws SQLException{
        List<Movie> citylist=new ArrayList<>();
        String city=null;
        while(rs.next()){
            Movie movie=new Movie();
            movie.setCity_movie(rs.getString("NAME_CITY"));
            citylist.add(movie);
        }
        return citylist;
    }
    public List<Movie> getInfomationOfMovie(ResultSet rs)throws SQLException{ // Shte ni posluji za rezultata na informaciqta za filma po zaqvka
        List<Movie> infoList=new ArrayList<>();
        while (rs.next()){
            Movie movie=new Movie();
            movie.setName_movie(rs.getString("MOVIE_NAME"));
            movie.setDirector_movie(rs.getString("DIRECTOR"));
            movie.setLenght_movie(rs.getTimestamp("LENGHT"));
            movie.setActors_movie(rs.getString("ACTORS"));
            movie.setSummary_movie(rs.getString("SUMMARY"));
            movie.setGenre_movie(rs.getString("GENRE"));
            movie.setRating_movie(rs.getString("RATING"));
            infoList.add(movie);
        }
        return infoList;
    }
    public List<Movie> selectMoviesOfReservation(){
        List<Movie> movies=new ArrayList<>();
        String sqlMovie="SELECT M.MOVIE_NAME \n"+
               "FROM MOVIE M \n";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlMovie)){
            ResultSet resultSet=preparedStatement.executeQuery();
            movies=getMovies(resultSet);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movies;
    }
    public List<Movie> selectCityOfReservation() throws SQLException,ClassNotFoundException{
        String sqlCity="SELECT C.NAME_CITY \n"+
                "FROM CITY C \n";
        List<Movie > listCity = new ArrayList<>();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlCity)){
            ResultSet resultSet=preparedStatement.executeQuery();
            listCity=getCities(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return listCity;
    }
}
