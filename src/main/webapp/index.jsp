<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>

</head>
<body>
<%
    String msg = "";
    if (request.getSession().getAttribute("message") != null) {
       msg= (String) request.getSession().getAttribute("message");
       request.getSession().removeAttribute("message");
    }
%>
<div style="background-color: bisque">
    <p><%=msg%></p>
    <b style="font-size: 50px">Welcome to Our site</b> <br>
    Please <a href="/login.jsp" style="font-size: 20px">Login</a> or <a href="/register.jsp" style="font-size: 20px">Register</a>
</div>

</body>
</html>