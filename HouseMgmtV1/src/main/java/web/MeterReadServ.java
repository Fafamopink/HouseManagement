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

import DAO.MeterReadingDAO; // 1st step import the DAO
import model.MeterReadModel;// 2nd step to import the model,because the servlet part is the connectiong between XXx


/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * 
 */

@WebServlet("/")
public class MeterReadServ extends HttpServlet {
    private static final long serialVersionUID = 1 ;
    private MeterReadingDAO meterreadingdao;

    public void init() {
    	meterreadingdao = new MeterReadingDAO(); // so we can call the obj for HOUSEDAO
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
			/*
			 * case "/new": showNewForm(request, response); break; case "/insert":
			 * insertHouse(request, response); break; case "/delete": deleteUser(request,
			 * response); break; case "/edit": showEditForm(request, response); break; case
			 */
            case "/SelectMeterRead":
                	listMeterReading(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMeterReading(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<MeterReadModel> listreading = meterreadingdao.ViewAllHouseReading();
        request.setAttribute("listHOUSES", listreading);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowMeterRead.jsp");
        dispatcher.forward(request, response);
    }

   
}