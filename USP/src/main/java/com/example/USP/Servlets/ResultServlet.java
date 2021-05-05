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

public class ResultServlet extends HttpServlet {
    private int city_id;
    private int client_id;
    private List<Projection> projectionList;
    private List<Projection> projectionListName;
    private Movie getSeachOfNameOfMovie;
    private Movie getMovieInfo;

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
        getSeachOfNameOfMovie=(Movie) req.getSession().getAttribute("searchMovieName");// informaciq za filma
        getMovieInfo=(Movie)req.getSession().getAttribute("getMovieName");// informaciq za filma
        projectionList=(List) req.getSession().getAttribute("ListOfProjections");//projekcii
        projectionListName=(List) req.getSession().getAttribute("SeachOfNameProjections");//projekcii
        city_id=(int)req.getSession().getAttribute("CityId");
        client_id=(int)req.getSession().getAttribute("ClientId");
        RequestDispatcher dispatcher1 = req.getRequestDispatcher("Result.jsp");
        req.setAttribute("NameMovie",getMovieInfo.getName_movie());
        req.setAttribute("GenreMovie",getMovieInfo.getGenre_movie());
        req.setAttribute("ActorsMovie",getMovieInfo.getActors_movie());
        req.setAttribute("DirectorMovie",getMovieInfo.getDirector_movie());
        req.setAttribute("SummaryMovie",getMovieInfo.getSummary_movie());
        req.setAttribute("LenghtMovie",getMovieInfo.getLenght_movie());
        req.setAttribute("RatingMovie",getMovieInfo.getRating_movie());
        req.setAttribute("ListOfProjections",projectionList);
        dispatcher1.forward(req,resp);
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
