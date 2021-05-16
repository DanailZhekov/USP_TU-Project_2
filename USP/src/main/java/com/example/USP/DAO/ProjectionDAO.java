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
    public List<Projection> getCinemas(ResultSet rs) throws SQLException {
        List<Projection> cinemasLiST=new ArrayList<>();
        while (rs.next()) {
            Projection projection=new Projection();
            projection.setCity_projetion(rs.getString("NAME_CITY"));
            cinemasLiST.add(projection);
        }
        return cinemasLiST;
    }
    public int getIdMovie(ResultSet rs) throws SQLException{
        int id_movie=0;
        while(rs.next()){
            id_movie=rs.getInt("ID_MOVIE");
        }
        return id_movie;
    }
    public int getIdProjection(ResultSet rs) throws SQLException{
        int id_proj=0;
        while(rs.next()){
            id_proj=rs.getInt("ID_PROJ");
        }
        return id_proj;
    }
    public int selectIdMovie(String name_movie){
        String sqlMovie="SELECT M.ID_MOVIE \n"+
                "FROM MOVIE M \n"+
                "WHERE M.MOVIE_NAME='"+name_movie+"' \n";
        int id_movie = 0;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlMovie)){
            ResultSet resultSet=preparedStatement.executeQuery();
            id_movie=getIdMovie(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id_movie;
    }
    public List<Projection> getProjetions(ResultSet rs) throws SQLException{
        List<Projection> projectionList=new ArrayList<>();
        while (rs.next()){
            Projection projection=new Projection();
            projection.setProjection_date(rs.getDate("date"));
            projection.setCity_projetion(rs.getString("NAME_CITY"));
            projection.setProjection_time(rs.getTimestamp("TIME"));
            projectionList.add(projection);
        }
        return projectionList;
    }
    public List<Projection> searchProjection(String kino,String MovieName,String DateProjection){
        String sqlSearch="SELECT P.date,C.NAME_CITY,P.TIME \n"+
                "FROM PROJECTION P \n" +
                "INNER JOIN RESERVATION R ON R.PROJECTION_ID_PROJ=P.ID_PROJ\n"+
                "INNER JOIN MOVIE M ON P.MOVIE_ID_MOVIE=M.ID_MOVIE \n"+
                "INNER JOIN CITY C ON R.CITY_ID_CITY=C.ID_CITY \n"+
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
    public List<Projection> searchOfNameProjections(String nameMovie){
        String sqlSearch="SELECT P.date,C.NAME_CITY,P.TIME \n"+
                "FROM PROJECTION P \n" +
                "INNER JOIN RESERVATION R ON R.PROJECTION_ID_PROJ=P.ID_PROJ\n"+
                "INNER JOIN MOVIE M ON P.MOVIE_ID_MOVIE=M.ID_MOVIE \n"+
                "INNER JOIN CITY C ON R.CITY_ID_CITY=C.ID_CITY \n"+
                "WHERE M.MOVIE_NAME='"+nameMovie+"'; \n";
        List<Projection> Searchlist=new ArrayList<>();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlSearch)){
            ResultSet resultSet=preparedStatement.executeQuery();
            Searchlist=getProjetions(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Searchlist;
    }
    public List<Projection> getAllCinemas() throws SQLException {
        String sql = "SELECT * FROM CITY";
        List<Projection> listCinemas = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            listCinemas = getCinemas(resultSet);
        }
        return listCinemas;
    }
    public int SelectIdFromProjection(Date date,Date time,int MovieId){
        String sqlSearch="SELECT P.ID_PROJ \n"+
                "FROM PROJECTION P \n"+
                "INNER JOIN MOVIE M ON P.MOVIE_ID_MOVIE=M.ID_MOVIE \n"+
                "WHERE P.date='"+date+"' AND P.TIME='"+time+"' AND M.ID_MOVIE='"+MovieId+"';";
        int idProject = 0;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlSearch)){
            ResultSet resultSet=preparedStatement.executeQuery();
            idProject=getIdProjection(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idProject;
    }
    public  void insertReservation(int br_seats,int project_id,int client_id,int city_id){
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
