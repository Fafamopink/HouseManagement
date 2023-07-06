<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://github.com/rjmdedil/HouseManagement.git" class="navbar-brand"> HOUSE
     Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/SelectAllRead" class="nav-link">METER READ LIST</a></li>
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">HOUSE LIST</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Readings</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Meter Reading</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>READING</th>
                                <th>DATE</th>
								<th>NOTES</th>
								<th>HOUSE NUMBER</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="mrdata" items="${listMeterReading}">
                             <!--//items is the arraylist on the servelet  -->
                                <tr>
                                    <td>
                                        <c:out value="${mrdata.reading}" />
                                        <!-- housenum here is the housenum on the model  -->
                                    </td>
                                    <td>
                                        <c:out value="${mrdata.date}" />
                                  <!-- housename here is the housename on the model  -->
                                        
                                    </td>
                                                                        <td>
                                        <c:out value="${mrdata.notes}" />
                                  <!-- housename here is the housename on the model  -->
                                        
                                    </td>
                                                                        <td>
                                        <c:out value="${mrdata.housenum}" />
                                  <!-- housename here is the housename on the model  -->
                                        
                                    </td>


                                    <td><a href="edit?housenum=<c:out value='${mrdata.housenum}' />">Select</a><a href="edit?housenum=<c:out value='${mrdata.housenum}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?housenum=<c:out value='${mrdata.housenum}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>