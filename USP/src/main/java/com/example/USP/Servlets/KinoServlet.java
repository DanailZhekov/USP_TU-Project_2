package com.example.USP.Servlets;

import com.example.USP.DAO.ProjectionDAO;
import com.example.USP.model.Movie;
import com.example.USP.model.Projection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class KinoServlet extends HttpServlet {
    private List<Projection> cinemaList;
    private ProjectionDAO projectionDAO;


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
        // tuka shte pravim zaqvka, sled kato e cuknat butona "kina" v glavnata stranica, shte ni prepashta kum tozi servlet ili
        // kym suotvetnata jsp stranica koqto e za kina.
        // purvo shte izvedem vsichki kina v suotvetnite gradove
        // sled kato klienta izbere dadeno kino shte mu izvede informaciq za kinoto(obsht broi zali i syotvetno mesta)
        // i shte moje da vidi ot tuk projekciite v dadenoto kino.
        cinemaList=(List)req.getSession().getAttribute("allCinemas");
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("Kino.jsp");
        req.setAttribute("kino",cinemaList);
        requestDispatcher.forward(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
