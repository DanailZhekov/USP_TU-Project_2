package com.example.USP.DAO;

import com.example.USP.model.Client;

import java.sql.*;
import java.util.List;

public class ClientDAO {
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

    private String getNameOfClient(ResultSet rs) throws SQLException {
        String clientName = null;
        while (rs.next()) {
            clientName = rs.getString("NAME");
        }
        return clientName;
    }
    public String selectNameOfLog(String email_client, String password_client) {
        String query_Client = "SELECT C.NAME \n"+
                "FROM CLIENT C \n" +
                "WHERE C.EMAIL='"+email_client+"' AND C.PASSWORD='"+password_client+"'";
        String name_client = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query_Client)) {
            ResultSet resultSet=preparedStatement.executeQuery();
            name_client=getNameOfClient(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return name_client;
    }
    public void RegistrationOfClient(String name_client,String email_client,String phone_client,int age_client,String pass_client){
        String insert_client="BEGIN \n"+
                "INSERT INTO CLIENT(ID_CLIENT,NAME,EMAIL,PHONE,AGE,PASSWORD) \n" +
                  "values(SEQUENCE_CLIENT.nextval,'"+name_client+"','"+email_client+"','"+phone_client+"','"+age_client+"','"+pass_client+"') \n" +
                "END \n";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate(insert_client);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
