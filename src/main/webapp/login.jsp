
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%
    String loginErrorMessage="";
    if (request.getSession().getAttribute("loginErrorMessage")!=null){
        loginErrorMessage= (String) request.getSession().getAttribute("loginErrorMessage");
        session.removeAttribute("loginErrorMessage");
    }
%>
<div style="border: 1px black solid; width: 40%">
    <p style="color: red"><%=loginErrorMessage%></p>
    <form action="/login" method="post">
        UserName: <input type="text" name="username"> <br>
        Password: <input type="password" name="password"> <br>
        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>
