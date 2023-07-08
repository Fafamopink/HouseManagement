<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>House Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://github.com/rjmdedil/HouseManagement" class="navbar-brand"> House Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">ALL HOUSES</a></li>
                        <li><a href="<%=request.getContextPath()%>/SelectAllRead" class="nav-link">ALL READING</a></li>
                    </ul>

                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">

                        <caption>
                            <h2>
                                <c:if test="${user != null}">
                                    Edit Meter Reading
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New Meter Reading
                                </c:if>
                            </h2>
                        </caption>
                        <c:if test="${user != null}">	
                        <form action="updateReading" method="post" >
                        <label>Reading</label> <input type="text" name="reading" value="<c:out value='${user.reading}' />" class="form-control"  required="required">
						<label>Date</label> <input type="date" name="date1" value="<c:out value='${user.date1}' />" class="form-control"  required="required">
						<label>Notes</label> <input type="text" name="notes" value="<c:out value='${user.notes}' />" class="form-control"  required="required">
						<label>House Numasd</label>					
						<select name="housenum" class="form-control">
						<c:forEach var="mr" items="${listHOUSES1}">
						<option value="${mr.housenum}">${mr.housename}</option>    							
						</c:forEach>
						</select>
						
                        <label>House IDMR</label> <input type="text" name="entryIDMR" value="<c:out value='${user.entryIDMR}' />" class="form-control"  required="required">
                        <button type="submit"class="btn btn-success" >Save</button>
                        </form>                      
                        </c:if>
                        
                        
                        
                        <c:if test="${user == null}">
                        <form  action="insertMR" method="post" >
                        <label>Reading</label> <input type="text" name="reading" class="form-control"  required="required">
						<label>Date</label> <input type="date" name="date1" class="form-control"  required="required">
						<label>Notes</label> <input type="text" name="notes" class="form-control"  required="required">
						<label>House Numasd</label>
						<select name="housenum" class="form-control">
						<c:forEach var="mr" items="${listHOUSES1}">
						<option value="${mr.housenum}">${mr.housename}</option>    		
						</c:forEach>
						</select>
                        <label>House IDMR</label> <input type="text" name="entryIDMR" class="form-control"  required="required">
                        <button type="submit" class="btn btn-success">Save</button>
                        </form>                                          
                        </c:if>
                        

                    </div>
                </div>
            </div>
        </body>

        </html>