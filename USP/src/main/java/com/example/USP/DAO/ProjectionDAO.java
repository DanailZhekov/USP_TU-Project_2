package com.example.USP.DAO;

import com.example.USP.model.Projection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectionDAO {
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

    public List<Projection> getProjetions(ResultSet rs) throws SQLException{
        List<Projection> projectionList=new ArrayList<>();
        while (rs.next()){
            Projection projection=new Projection();
            projection.setNameMovie(rs.getString("MOVIE_NAME"));
            projection.setProjection_date(rs.getDate("date"));
            projection.setProjection_time(rs.getTimestamp("TIME"));
            projection.setRating_movie(rs.getString("RATING"));
            projectionList.add(projection);
        }
        return projectionList;
    }
    public List<Projection> searchProjection(String kino,String MovieName,String DateProjection){
        String sqlSearch="SELECT M.MOVIE_NAME,P.date,P.TIME,R.RATING \n"+
                "FROM MOVIE M \n" +
                "INNER JOIN RATING R ON M.RATINGS_ID_RATING=R.ID_RATIONG \n "+
                "INNER JOIN PROJECTION P ON P.MOVIE_ID_MOVIE=M.ID_MOVIE \n"+
                "WHERE C.NAME_CITY='"+kino+"'AND M.MOVIE_NAME='"+MovieName+"'AND P.date='"+DateProjection+"'; \n";
        List<Projection> listSearchMovie=new ArrayList<>();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlSearch)){
            ResultSet resultSet=preparedStatement.executeQuery();
            listSearchMovie=getProjetions(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listSearchMovie;
    }
    public  void ProjectionReservation(int br_seats,int project_id,int client_id,int city_id){
        String reservation=
                "BEGIN \n"+
                        "INSERT RESERVATIONS(ID_RESERVATION,SEATS,PROJECTION_ID_PROJ,CLIENT_ID_CLIENT,CITY_ID_CITY) \n" +
                        "values(SEQUENCE_RESERVATION.nextval,'"+br_seats+"','"+project_id+"','"+client_id+"','"+city_id+"') \n" +
                        "END; \n";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate(reservation);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
