package com.example.USP.Servlets;

import com.example.USP.DAO.MovieDAO;
import com.example.USP.DAO.ProjectionDAO;
import com.example.USP.model.Movie;
import com.example.USP.model.Projection;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private int id_city;
    private int id_client;
    private String getMovieFromList;
    private String getCityFromList;
    private String getSearchDate;
    private String getSearchNameMovie;
    private List<Movie> movieList;
    private List<Movie> cityList;
    private List<Projection> projectionList;
    private List<Projection> SearchListProjection;
    private List<Projection> cinemaList;
    private Movie resultListOfName;
    private Movie resultList;
    private MovieDAO movieDAO;
    private ProjectionDAO projectionDAO;
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        id_client= (int) request.getSession().getAttribute("idClient");
        try{
            cityList= movieDAO.selectCityOfReservation();
            movieList=movieDAO.selectMoviesOfReservation();
            cinemaList=projectionDAO.getAllCinemas();// tova shte go izpolzvame kato potrebitelq cukne butona kina, da mi izvede kinata v syotvetnite gradove
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("cityList",cityList); // ще ги сетнем на два комбо-бокса където потребителя само ще си избира.
        request.setAttribute("MovieList",movieList);
        getMovieFromList=request.getParameter("city");//update 15.04
        getCityFromList=request.getParameter("movie");
        getSearchDate=request.getParameter("dateReservation");
        getSearchNameMovie=request.getParameter("nameMovie");

        try {
            resultList=movieDAO.searchMovie(getMovieFromList);// tuk izvejda samo informaciq za filma(aktiori,vremetraene i prochie)
            resultListOfName=movieDAO.searchMovieOfName(getSearchNameMovie);// тук също извежда информация за филма,но като се търси по име
            projectionList= projectionDAO.searchProjection(getCityFromList,getMovieFromList,getSearchDate); // tuk izvejda projekciite sprqmo filma
            SearchListProjection=projectionDAO.searchOfNameProjections(getSearchNameMovie);// tyk syshto shte izvejda projekciite sprqmo filma,no po tursene na ime
            id_city=movieDAO.selectIdFromMovie(getCityFromList);// tuk moje da ima error zashtoto ne znam v getCityFrom.. dali vzema id-to ot combo-boxa ili vzema string

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(request.getParameter("vhod")!=null){
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("LoginUser.jsp");
            requestDispatcher.forward(request,response);
        }
        else if(request.getParameter("search")!=null){
            // tuk trqbva da vidim kak ako potrebitelq izbere da vuvede ime na film i po nego da napravi zaqvka za infomaciqta za filma i projekciite,
            // da ne se prashtat danni ot combo-boxovete tui kato tam nqma da ima vuvedeno nishto
            request.getSession().setAttribute("searchMovieName",resultListOfName);// informaciq za filma
            request.getSession().setAttribute("getMovieName",resultList);// informaciq za filma
             request.getSession().setAttribute("ListOfProjections",projectionList);//projekcii
             request.getSession().setAttribute("SeachOfNameProjections",SearchListProjection);//projekcii
             request.getSession().setAttribute("ClientId",id_client);
             request.getSession().setAttribute("CityId",id_city);
            response.sendRedirect("ResultServlet");
        }
        else if(request.getParameter("kina")!=null){
            request.getSession().setAttribute("allCinemas",cinemaList);
            response.sendRedirect("KinoServlet");
        }
    }

   public void destroy() {
    }
}
