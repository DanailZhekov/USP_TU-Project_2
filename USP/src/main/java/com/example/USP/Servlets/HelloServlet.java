package com.example.USP.Servlets;

import com.example.USP.DAO.MovieDAO;
import com.example.USP.model.Movie;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String getMovieFromList;
    private String getCityFromList;
    private String getSearchDate;
    private String getSearchNameMovie;
    private List<Movie> movieList;
    private List<Movie> cityList;
    private List<Movie> resultListOfName;
    private List<Movie> resultList;
    private MovieDAO movieDAO;
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        try{
            cityList= movieDAO.selectCityOfReservation();
            movieList=movieDAO.selectMoviesOfReservation();
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
            resultList=movieDAO.searchMovie(getCityFromList,getMovieFromList,getSearchDate);
            resultListOfName=movieDAO.searchMovieOfName(getSearchNameMovie);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   public void destroy() {
    }
}
