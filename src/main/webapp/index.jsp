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
<p><%=msg%></p>
Welcome to Our site<br>
Please <a href="/login.jsp">Login</a> or <a href="/register.jsp">Register</a>
</body>
</html>