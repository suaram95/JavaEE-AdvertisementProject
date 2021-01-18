<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page import="util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
<%
    List<User> userList = (List<User>) request.getAttribute("users");
    List<Item> itemList = (List<Item>) request.getAttribute("items");
    User currentUser = (User) request.getSession().getAttribute("currentUser");
%>

<b style="color: orange">Welcome <%=currentUser.getName()%> &nbsp; <%=currentUser.getName()%></b> <br>
<a href="/logout">Logout</a>
<br><br>
<div style="border: 1px black solid; width: 50%">
    <b style="color: forestgreen">Users:</b> <br>
    <table>
        <tr>
            <td style="color: royalblue">ID</td>
            <td style="color: royalblue">Name</td>
            <td style="color: royalblue">Surname</td>
            <td style="color: royalblue">PhoneNumber</td>
            <td style="color: royalblue">Username</td>
            <td style="color: royalblue">UserType</td>
            <td style="color: royalblue">Action</td>
        </tr>
        <% for (User user : userList) {%>
        <tr>
            <td><%=user.getId()%>
            </td>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getSurname()%>
            </td>
            <td><%=user.getPhoneNumber()%>
            </td>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getUserType()%>
            </td>
            <td><a href="/deleteUser?id=<%=user.getId()%>">Delete</a></td>
        </tr>
        <% }%>
        <tr></tr>
    </table>
</div>
<br><br>
<div style="border: 1px solid black; width: 70%">
    <b style="color: red">Items:</b> <br>
    <table>
        <tr>
            <td style="color: royalblue">ID</td>
            <td style="color: royalblue">Title</td>
            <td style="color: royalblue">Description</td>
            <td style="color: royalblue">Price</td>
            <td style="color: royalblue">Category</td>
            <td style="color: royalblue">Created Date</td>
            <td style="color: royalblue">User</td>
            <td style="color: royalblue">Action</td>
        </tr>
        <% for (Item item : itemList) {%>
            <tr>
                <td><%=item.getId()%></td>
                <td><%=item.getTitle()%></td>
                <td><%=item.getDescription()%></td>
                <td><%=item.getPrice()%></td>
                <td><%=item.getCategory()%></td>
                <td><%=DateUtil.getStringFromDate(item.getCreatedDate())%></td>
                <td><%=item.getUser().getUsername()%></td>
                <td><button><a href="/deleteItem?id=<%=item.getId()%>">Delete</a></button></td>
            </tr>
        <%}%>
    </table>
</div>

</body>
</html>
