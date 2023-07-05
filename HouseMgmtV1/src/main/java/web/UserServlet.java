package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HOUSEDAO; // 1st step import the DAO
import model.HOUSESDATA;// 2nd step to 


/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * 
 */

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1 ;
    private HOUSEDAO hOUSEDAO;

    public void init() {
        hOUSEDAO = new HOUSEDAO(); // so we can call the obj for HOUSEDAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertHouse(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                	updateHouse(request, response);
                    break;
                default:
                	listHOUSES(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listHOUSES(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < HOUSESDATA > listHOUSES1 = hOUSEDAO.selectAllHouses();
        request.setAttribute("listHOUSES", listHOUSES1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("House-List.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("house-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("housenum"));
        HOUSESDATA existingHouse = hOUSEDAO.selectHouse(id);  //means we will get the data from the model
        RequestDispatcher dispatcher = request.getRequestDispatcher("house-form.jsp");
        request.setAttribute("user", existingHouse);
        dispatcher.forward(request, response);

    }

    private void insertHouse(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int housenum = Integer.parseInt(request.getParameter("housenum"));//get parameter means getting the data from the jsp ,
        String housename = request.getParameter("housename");//parameters will be pass to HOUSEDATA(classname notsure**) model
        HOUSESDATA newHouse = new HOUSESDATA(housenum, housename); //int  and string name will be pass here
        hOUSEDAO.insertHouse(newHouse);
        response.sendRedirect("list");
    }

    private void updateHouse(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int housenum = Integer.parseInt(request.getParameter("housenum")); //get parameter means getting the data from the jsp ,double checked this is sure
        String housename = request.getParameter("housename");//get the data on html page
       
        HOUSESDATA book = new HOUSESDATA(housenum, housename);//put the data on bok obj
        hOUSEDAO.updateHouse(book);//
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("housenum"));//means huse num odisplayed or already on the page
        hOUSEDAO.deleteHouse(id);
        response.sendRedirect("list");

    }
}