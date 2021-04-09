package com.example.USP.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("LoginServlet")
public class LoginServlet extends HttpServlet {
    private String log_Email;
    private String log_Pass;

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
        log_Email=req.getParameter("Log_Email"); // trqbva da se izpolzvat parametrite v kavichkite v name atributa na input-textovete
        log_Pass=req.getParameter("Log_Pass");
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
