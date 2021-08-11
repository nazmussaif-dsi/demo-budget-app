<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="ISO-8859-1">
  <%@include file="../base.jsp" %>
  <title>Expenses</title>

  <script>
    $(document).ready(function () {
      resetTable();
      resetForm();
    });

    function resetForm() {
      $('#saveExpense').show();
      $('#updateExpense').hide();
      $('#idField').hide();

      $('#expenseForm')[0].reset();
      $("#expenseDate").val(new Date().toISOString().split('T')[0]);

      $.ajax({
        type: "GET",
        url: "api/categories",
        success: function (response) {
          $("#category > option").remove();
          for (let i = 0; i < response.length; i++) {
            $("#category").append(new Option(response[i].name, response[i].id));
          }
        },
        error: function (err) {
          alert("error: " + err);
        }
      });
    }

    function resetTable() {
      $.ajax({
        type: "GET",
        url: "expenses/all",
        success: function (response) {
          $('.expenseTable_tr').remove();
          for (let i = 0; i < response.length; i++) {
            $("#expenseTable")
                .append(
                    '<tr class="expenseTable_tr">' +
                    '<td>' + response[i].id + '</td>' +
                    '<td>' + response[i].expenseDate + '</td>' +
                    '<td>' + response[i].description + '</td>' +
                    '<td>' + response[i].amount + '</td>' +
                    '<td>' + response[i].category.name + '</td>' +
                    '<td>' +
                    '<input type="button" class="btn btn-warning" ' +
                    'onclick="editExpenseBtn(' + response[i].id + ')" ' +
                    'value="Edit">' +
                    '</td> ' +
                    '<td>' +
                    '<input type="button" class="btn btn-danger" ' +
                    'onclick="deleteExpenseBtn(' + response[i].id + ');" ' +
                    'value="Delete">' +
                    '</td> ' +
                    '</tr>'
                );
          }
        },
        error: function (err) {
          alert("error from getAllRecordAndSetToTable: " + err);
        }
      });
    }

    function saveExpenseBtn() {
      $.ajax({
        type: "POST",
        url: "api/expenses/add",
        dataType : 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
          expenseDate: $("#expenseDate").val(),
          description: $("#description").val(),
          amount: $("#amount").val(),
          userId: 1,
          category: {
            id: $("#category").val(),
            name: $("#category option:selected").text()
          }
        }),
        success: function (result) {
          resetTable();
          resetForm();
        },
        error: function (err) {
          console.log(err);
          alert("error from saveExpenseBtn: " + err);
        }
      });
    }

    function editExpenseBtn(id) {
      $.ajax({
        type: "GET",
        url: "expenses/" + id,
        success: function (response) {
          $("#expenseId").val(response.id);
          $("#expenseDate").val(new Date(response.expenseDate).toISOString().split('T')[0]);
          $("#category").val(response.category.id);
          $("#description").val(response.description);
          $("#amount").val(response.amount);

          $('#saveExpense').hide();
          $('#updateExpense').show();
          $('#idField').show();
        },
        error: function (err) {
          alert("error from editExpenseBtn: " + err);
        }
      });
    }

    function updateExpenseBtn() {
      $.ajax({
        type: "POST",
        url: "api/expenses/update",
        dataType : 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
          id: $("#expenseId").val(),
          expenseDate: $("#expenseDate").val(),
          description: $("#description").val(),
          amount: $("#amount").val(),
          userId: 1,
          category: {
            id: $("#category").val(),
            name: $("#category option:selected").text()
          }
        }),
        success: function (result) {
          resetTable();
          resetForm();
        },
        error: function (err) {
          alert("error from updateExpenseBtn: " + err);
        }
      });
    }

    function deleteExpenseBtn(id) {
      $.ajax({
        type: "DELETE",
        url: "/expenses/delete/" + id,
        success: function (response) {
          resetTable();
        },
        error: function (err) {
          alert("error: " + err);
        }
      });
    }
  </script>
</head>

<body>
<jsp:include page="../navber.jsp"/>
<div class="container">
  <h1 class="text-center">Expense List</h1>
  <div class="container mt-3">
    <div class="row">
      <jsp:include page="expense_form.jsp"/>
      <jsp:include page="expense_list.jsp"/>
    </div>
  </div>
</div>
</body>
</html>