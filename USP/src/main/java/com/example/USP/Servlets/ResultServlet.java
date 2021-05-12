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
import java.util.List;

public class ResultServlet extends HttpServlet {
    private String city;
    private int city_id;
    private int client_id;
    private int movie_id;
    private int project_id;
    private List<Projection> projectionList;
    private List<Projection> projectionListName;
    private ProjectionDAO projectionDAO;
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
        getSeachOfNameOfMovie=(Movie) req.getSession().getAttribute("searchMovieName");// informaciq za filma(kogato se vuvedete samo ime na film)
        getMovieInfo=(Movie)req.getSession().getAttribute("getMovieName");// informaciq za filma(kogato se izbirat danni ot combobox-vete)
        city=(String)req.getSession().getAttribute("City");
        if(getSeachOfNameOfMovie!=null){// tova se pravi po tusene na ime na film
            projectionList=(List) req.getSession().getAttribute("ListOfProjections");//projekcii(po vuvedeno ime na film)
            client_id=(int)req.getSession().getAttribute("ClientId");
            movie_id=projectionDAO.selectIdMovie(getSeachOfNameOfMovie.getName_movie());
        }
        else if(getMovieInfo!=null){// tova se pravi po vuvedenite danni v comboboxovete
            projectionListName=(List) req.getSession().getAttribute("SeachOfNameProjections");//projekcii(po danni ot combobox-vete)
            client_id=(int)req.getSession().getAttribute("ClientId");
            city_id=projectionDAO.selectIdMovie(city);
            movie_id=projectionDAO.selectIdMovie(getMovieInfo.getName_movie());
        }
        RequestDispatcher dispatcher1 = req.getRequestDispatcher("Result.jsp");
        if(getSeachOfNameOfMovie!=null){
            req.setAttribute("NameMovie",getSeachOfNameOfMovie.getName_movie());
            req.setAttribute("GenreMovie",getSeachOfNameOfMovie.getGenre_movie());
            req.setAttribute("ActorsMovie",getSeachOfNameOfMovie.getActors_movie());
            req.setAttribute("DirectorMovie",getSeachOfNameOfMovie.getDirector_movie());
            req.setAttribute("SummaryMovie",getSeachOfNameOfMovie.getSummary_movie());
            req.setAttribute("LenghtMovie",getSeachOfNameOfMovie.getLenght_movie());
            req.setAttribute("RatingMovie",getSeachOfNameOfMovie.getRating_movie());
            req.setAttribute("ListOfProjections",projectionList);
        }
        else if(getMovieInfo!=null){
            req.setAttribute("NameMovie",getMovieInfo.getName_movie());
            req.setAttribute("GenreMovie",getMovieInfo.getGenre_movie());
            req.setAttribute("ActorsMovie",getMovieInfo.getActors_movie());
            req.setAttribute("DirectorMovie",getMovieInfo.getDirector_movie());
            req.setAttribute("SummaryMovie",getMovieInfo.getSummary_movie());
            req.setAttribute("LenghtMovie",getMovieInfo.getLenght_movie());
            req.setAttribute("RatingMovie",getMovieInfo.getRating_movie());
            req.setAttribute("ListOfProjections",projectionListName);
        }
        if(req.getParameter("reserv")!=null){
            RequestDispatcher dispatcher=req.getRequestDispatcher("Reservation.jsp");
            dispatcher.forward(req,resp);
        }
       // project_id=projectionDAO.SelectIdFromProjection(data,time,movie_id);
        dispatcher1.forward(req,resp);
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
