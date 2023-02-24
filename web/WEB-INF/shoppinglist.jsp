<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Shopping List page</title>
  </head>
  <body>
    <h1>Shopping List</h1>
    <p>Hello, ${username} <a href="?action=logout">Logout</a></p>
    <form action="?action=add" method="POST">
      <h2>List</h2>
      <label>Add item:</label>
      <input name="item" />
      <button type="submit">Add</button>
    </form>
    <form action="?action=delete" method="POST">
      <ul>
        <c:forEach var="item" items="${items}" varStatus="loop">
          <li>
            <input type="radio" name="item" value="${loop.index}" id="${item}-${loop.index}" />
            <label for="${item}-${loop.index}">${item}</label>
          </li>
        </c:forEach>
      </ul>
      <button type="submit">Delete</button>
    </form>
  </body>
</html>
