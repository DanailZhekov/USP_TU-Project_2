package com.example.USP.Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private String name_Client;
    private String email_Client;
    private String phone_Client;
    private int age_Client;
    private String password_Client;

    @Override
    public void init() throws ServletException {
        super.init();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        name_Client=req.getParameter("Client_name"); // trqbva da se izpolzvat parametrite v kavichkite v name atributa na input-textovete
        email_Client=req.getParameter("Client_email");
        phone_Client=req.getParameter("Client_phone");
        age_Client= Integer.parseInt(req.getParameter("Client_age"));
        password_Client=req.getParameter("Client_password");
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
