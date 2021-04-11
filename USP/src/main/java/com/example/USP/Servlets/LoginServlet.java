package com.example.USP.Servlets;

import com.example.USP.DAO.ClientDAO;

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
    private String Client_name;
    private ClientDAO clientDAO;

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
        Client_name=clientDAO.selectNameOfLog(log_Email,log_Pass);
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
