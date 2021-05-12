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
    private int id_movie;
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
        String name_client;
        name_client=(String) request.getSession().getAttribute("NameClient");
        request.setAttribute("Name",name_client);
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
        getMovieFromList=request.getParameter("movie");//vzemame film ot combobox
        getCityFromList=request.getParameter("city");// vzemame kino v grad ot combobox
        getSearchDate=request.getParameter("dateReservation");// vuvejdame data za projekciq
        getSearchNameMovie=request.getParameter("nameMovie");// tova e opciq dve za tursene po ime na film

        try {
            if(getMovieFromList!=null&&getCityFromList!=null&&getSearchDate!=null){// tova go pravim za da proverim purvo dali neshto e vuvejdal potrebitelq v trite combobox-a
                resultList=movieDAO.searchMovie(getMovieFromList);// tuk izvejda samo informaciq za filma(aktiori,vremetraene i prochie)
                projectionList= projectionDAO.searchProjection(getCityFromList,getMovieFromList,getSearchDate); // tuk izvejda projekciite sprqmo filma
            }
            else if(getSearchNameMovie!=null){
                resultListOfName=movieDAO.searchMovieOfName(getSearchNameMovie);// тук също извежда информация за филма,но като се търси по име
                SearchListProjection=projectionDAO.searchOfNameProjections(getSearchNameMovie);// tyk syshto shte izvejda projekciite sprqmo filma,no po tursene na ime
            }
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
            if(getMovieFromList!=null&&getCityFromList!=null&&getSearchDate!=null){
                request.getSession().setAttribute("getMovieName",resultList);// informaciq za filma
                request.getSession().setAttribute("ListOfProjections",projectionList);//projekcii
                request.getSession().setAttribute("City",getCityFromList);
                request.getSession().setAttribute("ClientId",id_client);
                response.sendRedirect("ResultServlet");
            }
            else if(getSearchNameMovie!=null){
                request.getSession().setAttribute("searchMovieName",resultListOfName);// informaciq za filma
                request.getSession().setAttribute("SeachOfNameProjections",SearchListProjection);//projekcii
                request.getSession().setAttribute("ClientId",id_client);
                response.sendRedirect("ResultServlet");
            }
        }
        else if(request.getParameter("kina")!=null){
            request.getSession().setAttribute("allCinemas",cinemaList);
            response.sendRedirect("KinoServlet");
        }
    }

   public void destroy() {
    }
}
