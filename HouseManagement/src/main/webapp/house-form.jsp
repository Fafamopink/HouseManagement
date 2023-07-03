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
                        <a href="https://www.javaguides.net" class="navbar-brand"> House Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
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
                                    Edit User
                                </c:if>
                                <c:if test="${user == null}">
                                    Add New User
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${user != null}">
                        <form action="update" method="post" >
                        <label>House ID</label> 
                        <input type="text" name="housenum" value="<c:out value='${user.housenum}' />" />
	                    <label>House Name</label> <input type="text"  value="<c:out value='${user.housename}' />" class="form-control" name="housename" required="required">
                        <button type="submit"class="btn btn-success" >Save</button>
                        </form>                      
                        </c:if>
                        
                        
                        
                        <c:if test="${user == null}">
                        <form  action="insert" method="post" >
                        <label>House ID</label> <input type="text"  name="housenum"   class="form-control"  required="required">
                        <label>House Name</label> <input type="text" name="housename" class="form-control"  required="required">
                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                                               
                        </c:if>
                        

                    </div>
                </div>
            </div>
        </body>

        </html>