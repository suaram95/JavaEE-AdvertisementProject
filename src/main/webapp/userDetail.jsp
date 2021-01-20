<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");
    String userEditMsg="";
    if (request.getSession().getAttribute("userEditMsg")!=null){
        userEditMsg= (String) request.getSession().getAttribute("userEditMsg");
        request.getSession().removeAttribute("userEditMsg");
    }
%>
<a href="/userHome">Back</a><br>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Phone Number</td>
        <td>Username</td>
        <td>Password</td>

    </tr>
    <tr>
        <td><%=currentUser.getId()%>
        </td>
        <td><%=currentUser.getName()%>
        </td>
        <td><%=currentUser.getSurname()%>
        </td>
        <td><%=currentUser.getPhoneNumber()%>
        </td>
        <td><%=currentUser.getUsername()%>
        </td>
        <td><%=currentUser.getPassword()%>
        </td>
    </tr>
</table>
<br><br>
<%if (currentUser.getPictureUrl()!=null){%>
<img src="/image?path=<%=currentUser.getPictureUrl()%>" width="100" alt="">
<%}%> <br> <br>
<div style="border: 1px black solid; width: 30%">
    <p style="color: red"><%=userEditMsg%></p>
    <b>Edit Data:</b><br><br>
    <form action="/editUserData" method="post" enctype="multipart/form-data">
        Name: <input type="text" name="name" value="<%=currentUser.getName()%>"><br>
        Surname: <input type="text" name="surname" value="<%=currentUser.getSurname()%>"><br>
        PhoneNumber: <input type="text" name="phoneNumber" value="<%=currentUser.getPhoneNumber()%>"><br>
        Username: <input type="text" name="username" value="<%=currentUser.getUsername()%>"><br>
        Password: <input type="text" name="password" value="<%=currentUser.getPassword()%>"><br>
        Profile Picture: <input type="file" name="image"><br>
        <input type="submit" value="Edit">
    </form>

</div>
</body>
</html>
