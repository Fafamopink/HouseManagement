package web;
import java.sql.Date;  

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HOUSEDAO; // 1st step import the DAO
import DAO.MeterReadingDAO;
import model.HOUSESDATA;// 2nd step to
import model.MeterReadModel;


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
    private MeterReadingDAO meterreadingdao;


    @Override
	public void init() {
        hOUSEDAO = new HOUSEDAO(); // so we can call the obj for HOUSEDAO
        meterreadingdao = new MeterReadingDAO();
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
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
                case "/delete":// this part is writen on the jsp"delete'
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                	updateHouse(request, response);
                    break;
				
				case "/ViewHouseReading": 
					ViewHouseReading(request, response);
					break;
				 
                case "/SelectAllRead":
                	listAllRead(request, response);
                    break;
                case "/deleteReading":// this part is written on the jsp"delete'
                	deleteReading(request, response);
                    break;
                    
                case "/NewMeterRead":// this part is written on the jsp"delete'
                	showADDFormMR(request, response);//showADDFormMR is method here in cotroller 
                    break;
                    
                    
                case "/insertMR":// this part is written on the jsp"delete'
                	insertMR(request, response);//showADDFormMR is method here in cotroller 
                    break;
                    
                    
                case "/editReading":// this part is written on the jsp"delete'
                	showEditFormMR(request, response);
                    break;

                case "/updateReading":// this part is written on the jsp"delete'
                	updateMR(request, response);
                    break;
                default:
                	listHOUSES(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
       // int housenum = Integer.parseInt(request.getParameter("housenum"));//get parameter means getting the data from the jsp ,
        String housename = request.getParameter("housename");//parameters will be pass to HOUSEDATA(classname notsure**) model
        HOUSESDATA newHouse = new HOUSESDATA(housename); //int  and string name will be pass here
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
	// code block to see MR of selected house already fix shouldnt be changed
	 private void ViewHouseReading(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException,
	  ServletException { 
      int id = Integer.parseInt(request.getParameter("housenum"));
	  List<MeterReadModel> listreading = meterreadingdao.ViewHouseReading(id);
	  request.setAttribute("listMeterReading",listreading);	  
	  RequestDispatcher dispatcher =request.getRequestDispatcher("ShowMeterRead.jsp");
	  dispatcher.forward(request, response); 
	  }
	 

    private void listAllRead(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < MeterReadModel > MRALLDATA = meterreadingdao.ViewAllHouseReading();
        request.setAttribute("listMeterReading", MRALLDATA);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowMeterRead.jsp");
        dispatcher.forward(request, response);
    }
    
    
    private void deleteReading(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("entryIDMR"));//means huse num odisplayed or already on the page
        meterreadingdao.deleteReading(id);
        response.sendRedirect("list");

    }    
    private void showADDFormMR(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
//code block to show housename
        List < HOUSESDATA > listofhousename = hOUSEDAO.selectAllHouses();
        request.setAttribute("listHOUSES1", listofhousename);
        RequestDispatcher dispatcher = request.getRequestDispatcher("MR-Form.jsp");
        dispatcher.forward(request, response);
        ////issue on this part
      
    }
    //showing jsp,is different to insering the data since inseting is an action
    private void insertMR(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int reading = Integer.parseInt(request.getParameter("reading"))     ;//parameters will be pass to HOUSEDATA(classname notsure**) model
        String date1 = request.getParameter("date1");
        String notes = request.getParameter("notes");
        int housenum  = Integer.parseInt(request.getParameter("housenum"));
        int entryIDMR = Integer.parseInt(request.getParameter("entryIDMR"));
        Date date=Date.valueOf(date1);        
        System.out.println("String converted to java.sql.Date :" + date);
        MeterReadModel newMR = new MeterReadModel(reading, date, notes, housenum, entryIDMR);
        meterreadingdao.insertMR(newMR);
        response.sendRedirect("list"); 
    }
    
    
    
    
    
    private void showEditFormMR(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException, ParseException {
        //parsing 
        int id = Integer.parseInt(request.getParameter("entryIDMR"));//housenum s the param also in the jsp
        MeterReadModel existingHouse = meterreadingdao.selectMR(id);
        request.setAttribute("listHOUSES", existingHouse);
        //displaying list of hosuename for each in jsp
        List < HOUSESDATA > listofhousename = hOUSEDAO.selectAllHouses(); 
        request.setAttribute("listHOUSES1", listofhousename);
        request.setAttribute("user", existingHouse);
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("MR-Form.jsp");
        dispatcher.forward(request, response);

    }
    
    
    private void updateMR(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ParseException {
    	  int reading = Integer.parseInt(request.getParameter("reading"))     ;//parameters will be pass to HOUSEDATA(classname notsure**) model
          String date1 = request.getParameter("date1");
          System.out.println(date1);
          String notes = request.getParameter("notes");
          int housenum  = Integer.parseInt(request.getParameter("housenum"));
          int entryIDMR = Integer.parseInt(request.getParameter("entryIDMR"));
          
          //last issue here,since i still cannt convert the date
          
        
          Date date=Date.valueOf(date1);
     

          System.out.println("String converted to java.sql.Date :" + date);
          MeterReadModel newMR = new MeterReadModel(reading, date, notes, housenum, entryIDMR);
           meterreadingdao.updateHouse(newMR);// meterreadingdao is object to call the class and updatehouse is a   method inside the class
           response.sendRedirect("list");
    	
    }

}