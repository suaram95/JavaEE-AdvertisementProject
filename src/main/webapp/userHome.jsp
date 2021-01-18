<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page import="model.Category" %>
<%@ page import="util.DateUtil" %>
<%@ page import="model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<body>
<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");

    List<User> userList = (List<User>) request.getAttribute("users");
    List<Item> itemList = (List<Item>) request.getAttribute("items");

    List<Item> currentUserItemsList = (List<Item>) request.getAttribute("currentUserItems");


    String itemDetailMsg = "";
    if (request.getSession().getAttribute("itemDetailMsg") != null) {
        itemDetailMsg = (String) request.getSession().getAttribute("itemDetailMsg");
        request.getSession().removeAttribute("itemDetailMsg");
    }
%>
<div style="border: 1px black solid; width: 50%">
    <b>Welcome <%=currentUser.getName()%>  <%=currentUser.getSurname()%></b><br>
    <a href="/userDetails">My Details</a> <br>
    <a href="/logout">Logout</a>
</div>
<br> <br>
<div style="border: 1px black solid; width: 50%">
    <b style="color: blueviolet">Add Item</b><br>
    <p style="color: red"><%=itemDetailMsg%>
    </p>
    <form action="/addItem" method="post">
        <b>Title:</b> <input type="text" name="title"><br>
        <b>Description:</b> <input type="text" name="description"><br>
        <b>Price:</b> <input type="number" name="price"> <br>
        <b>Category:</b> <select name="category">
        <option value="CAR"><%=Category.CAR%>
        </option>
        <option value="HOUSES"><%=Category.HOUSES%>
        </option>
        <option value="OTHER"><%=Category.OTHER%>
        </option>
    </select><br> <br>
        <input type="submit" value="ADD"> &nbsp;
        <input type="reset" value="Reset">
    </form>
</div>
<br><br>
<div style="border: 1px black solid; width: 50%">
    <b style="color: forestgreen">My Items</b><br>
    <table border="1">
        <tr>
            <td style="color: royalblue">ID</td>
            <td style="color: royalblue">Title</td>
            <td style="color: royalblue">Description</td>
            <td style="color: royalblue">Price</td>
            <td style="color: royalblue">Category</td>
            <td style="color: royalblue">CreatedDate</td>
            <td style="color: royalblue">Action</td>
        </tr>
        <% for (Item item : currentUserItemsList) {%>
        <tr>
            <td><%=item.getId()%>
            </td>
            <td><%=item.getTitle()%>
            </td>
            <td><%=item.getDescription()%>
            </td>
            <td><%=item.getPrice()%>
            </td>
            <td><%=item.getCategory()%>
            </td>
            <td><%=DateUtil.getStringFromDate(item.getCreatedDate())%>
            </td>
            <td><a href="/deleteItem?id=<%=item.getId()%>">Delete</a></td>
        </tr>
        <%}%>

    </table>
</div>
<br><br>
<div style="border: 1px black solid; width: 50%">
    <b style="color: forestgreen">All Items</b><br>
    <table border="1">
        <tr>
            <td style="color: royalblue">ID</td>
            <td style="color: royalblue">Title</td>
            <td style="color: royalblue">Description</td>
            <td style="color: royalblue">Price</td>
            <td style="color: royalblue">Category</td>
            <td style="color: royalblue">CreatedDate</td>
            <td style="color: royalblue">User</td>
        </tr>
        <% for (Item item : itemList) {%>
        <tr>
            <td><%=item.getId()%>
            </td>
            <td><%=item.getTitle()%>
            </td>
            <td><%=item.getDescription()%>
            </td>
            <td><%=item.getPrice()%>
            </td>
            <td><%=item.getCategory()%>
            </td>
            <td><%=DateUtil.getStringFromDate(item.getCreatedDate())%>
            </td>
            <td><%=item.getUser().getName()%>  <%=item.getUser().getSurname()%>
            </td>
        </tr>
        <%}%>
    </table>
</div>
<br><br>
<div style="border: 1px black solid; width: 50%">
    <b style="color: forestgreen">Users</b><br>
    <table border="1">
        <tr>
            <td style="color: royalblue">Name</td>
            <td style="color: royalblue">Surname</td>
            <td style="color: royalblue">PhoneNumber</td>
            <td style="color: royalblue">Username</td>
        </tr>
        <% for (User user : userList) {
        if (user.getUserType()== UserType.USER){%>
        <tr>
            <td><%=user.getName()%></td>
            <td><%=user.getSurname()%></td>
            <td><%=user.getPhoneNumber()%></td>
            <td><%=user.getUsername()%></td>
        </tr>
        <%}}%>
    </table>
</div>
</body>
</html>
