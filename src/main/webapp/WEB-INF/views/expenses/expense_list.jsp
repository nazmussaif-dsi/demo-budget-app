<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="../base.jsp"%>
  <title>Expense List</title>
</head>

<body>
<div class="container">
  <h1 class="text-center">Expense List</h1>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Date</th>
      <th scope="col">Description</th>
      <th scope="col">Amount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${expenses}" var="expenses">
      <tr>
        <th scope="row">${expenses.id}</th>
        <td>${expenses.expenseDate}</td>
        <td>${expenses.description}</td>
        <td>${expenses.amount}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
