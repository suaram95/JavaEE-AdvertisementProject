
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%
    String msg = "";
    if (request.getSession().getAttribute("message") != null) {
        msg= (String) request.getSession().getAttribute("message");
        request.getSession().removeAttribute("message");
    }
%>
<div style="border: 1px black solid; width: 40%">
    <p style="color: red"><%=msg%></p>
    <form action="/register" method="post">
        Name: <input type="text" name="name"> <br>
        Surname: <input type="text" name="surname"> <br>
        Phone Number: <input type="text" name="phoneNumber"> <br>
        Username: <input type="text" name="username"> <br>
        Password: <input type="text" name="password"> <br>
        <input type="submit" value="Register"><br>
        <input type="reset" value="Reset"><br>
    </form>
</div>

</body>
</html>
