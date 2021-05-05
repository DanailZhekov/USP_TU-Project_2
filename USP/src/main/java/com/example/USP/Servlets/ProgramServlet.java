package com.example.USP.Servlets;

import com.example.USP.model.Movie;
import com.example.USP.model.Projection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProgramServlet extends HttpServlet {
    private List<Projection> projectionList;
    private Movie getSeachOfName;
    private Movie getMovie;

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
        getSeachOfName=(Movie) req.getSession().getAttribute("searchMovieName");
        getMovie=(Movie) req.getSession().getAttribute("resultList");
        projectionList=(List) req.getSession().getAttribute("ListOfProjections");
        RequestDispatcher dispatcher1 = req.getRequestDispatcher("Result.jsp");
        dispatcher1.forward(req,resp);
    }
    @Override
    public void destroy() {
        super.destroy();
    }

}
