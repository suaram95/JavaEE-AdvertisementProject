
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<%
    String loginErrorMessage="";
    if (request.getSession().getAttribute("loginErrorMessage")!=null){
        loginErrorMessage= (String) request.getSession().getAttribute("loginErrorMessage");
        session.removeAttribute("loginErrorMessage");
    }
%>
<input type="radio" checked id="toggle--login" name="toggle" class="ghost" />
<input type="radio" id="toggle--signup" name="toggle" class="ghost" />

<img class="logo framed" src="https://www.tumblr.com/images/logo/logo_large.png?v=7ea0eb57dd627a95f82be5bde0c43d59" alt="Tumblr logo" />

<form class="form form--login framed" action="/login" method="post">
    <p style="color: red"><%=loginErrorMessage%></p>
    <input type="text" placeholder="Username" name="username" class="input input--top" />
    <input type="password" placeholder="Password" name="password" class="input" />
    <input type="submit" value="Log in" class="input input--submit" />
</form>

<div class="fullscreen-bg"></div>

</body>
</html>
