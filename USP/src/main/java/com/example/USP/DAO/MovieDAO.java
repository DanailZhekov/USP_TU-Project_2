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
    public int getIdCity(ResultSet rs) throws SQLException{
        int id_city=0;
        while(rs.next()){
            id_city=rs.getInt("ID_CITY");
        }
        return id_city;
    }
    public int selectIdFromMovie(String name_city){
        String sqlCity="SELECT C.ID_CITY \n"+
                "FROM CITY C \n"+
                "WHERE C.NAME_CITY='"+name_city+"' \n";
        int id_city = 0;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlCity)){
            ResultSet resultSet=preparedStatement.executeQuery();
            id_city=getIdCity(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id_city;
    }
    public Movie getInfomationOfMovie(ResultSet rs)throws SQLException{ // Shte ni posluji za rezultata na informaciqta za filma po zaqvka
        Movie movie=null;
        while (rs.next()){
            movie=new Movie();
            movie.setName_movie(rs.getString("MOVIE_NAME"));
            movie.setDirector_movie(rs.getString("DIRECTOR"));
            movie.setLenght_movie(rs.getTimestamp("LENGHT"));
            movie.setActors_movie(rs.getString("ACTORS"));
            movie.setSummary_movie(rs.getString("SUMMARY"));
            movie.setGenre_movie(rs.getString("GENRE"));
            movie.setRating_movie(rs.getString("RATING"));
        }
        return movie;
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
    //update 15.4.21
    public Movie searchMovieOfName(String MovieName)  throws SQLException,ClassNotFoundException{ // Zaqvka po tursene na ime na film.
        String sqlSearch="SELECT M.MOVIE_NAME,M.SUMMARY,M.LENGHT,M.ACTORS,G.GENRE,R.RATING \n"+
                "FROM MOVIE M \n" +
                "INNER JOIN GENRE G ON M.GENRE_ID_GENRE=M.ID_GENRE \n"+
                "INNER JOIN RATING R ON M.RATINGS_ID_RATING=R.ID_RATIONG \n"+
                "WHERE M.MOVIE_NAME='"+MovieName+"'; \n";
        Movie recieveMovie=new Movie();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlSearch)){
            ResultSet resultSet=preparedStatement.executeQuery();
            recieveMovie=getInfomationOfMovie(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recieveMovie;
    }
   public Movie searchMovie(String MovieName)  throws SQLException,ClassNotFoundException{ // Trqbva da se opravi datata, zadavam q za sega kato string
        String sqlSearch="SELECT M.MOVIE_NAME,M.SUMMARY,M.LENGHT,M.ACTORS,G.GENRE,R.RATING \n"+
                "FROM MOVIE M \n" +
                "INNER JOIN GENRE G ON M.GENRE_ID_GENRE=M.ID_GENRE \n"+
                "INNER JOIN RATING R ON M.RATINGS_ID_RATING=R.ID_RATIONG \n"+
                "INNER JOIN PROJECTION P ON P.MOVIE_ID_MOVIE=M.ID_MOVIE \n"+
                "INNER JOIN RESERVATIONS RS ON RS.PROJECTION_ID_PROJ=P.ID_PROJ \n"+
                "INNER JOIN CITY C RS.CITY_ID_CITY=C.ID_CITY \n"+
                "WHERE M.MOVIE_NAME='"+MovieName+"'";
         Movie recieveMovie=new Movie();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlSearch)){
            ResultSet resultSet=preparedStatement.executeQuery();
            recieveMovie=getInfomationOfMovie(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recieveMovie;
    }
}
