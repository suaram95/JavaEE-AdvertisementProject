<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%
    String msg = "";
    if (request.getSession().getAttribute("message") != null) {
        msg = (String) request.getSession().getAttribute("message");
        request.getSession().removeAttribute("message");
    }
%>

<input type="radio" checked id="toggle--login" name="toggle" class="ghost"/>
<input type="radio" id="toggle--signup" name="toggle" class="ghost"/>

<img class="logo framed" src="https://www.tumblr.com/images/logo/logo_large.png?v=7ea0eb57dd627a95f82be5bde0c43d59"
     alt="Tumblr logo"/>
<a href="/login.jsp" style="font-size: 70px">Login</a>
<form class="form form--login framed" action="/register" method="post" enctype="multipart/form-data">
    <p style="color: red"><%=msg%>
        <input type="text" placeholder="Name" name="name" class="input input--top"/>
        <input type="text" placeholder="Surname" name="surname" class="input"/>
        <input type="text" placeholder="Phone Number" name="phoneNumber" class="input"/>
        <input type="text" placeholder="Username" name="username" class="input"/>
        <input type="password" placeholder="Password" name="password" class="input"/>
        <input type="file" name="image" class="input"/>
        <input type="submit" value="Register" class="input input--submit"/>
</form>

<div class="fullscreen-bg"></div>

</body>
</html>
