<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="ISO-8859-1">
  <%@include file="../base.jsp" %>

  <script>
    $(document).ready(function () {
      getAllRecord();

      $('#saveExpense').show();
      $('#updateExpense').hide();
      $('#idfield').hide();

      $('#saveExpense').click(function () {
        $.ajax({
          type: "POST",
          url: "expenses/add",
          data: {
            expenseDate: $("#expenseDate").val(),
            description: $("#description").val(),
            amount: $("#amount").val()
          },
          success: function (result) {
            getAllRecord();
            $('#expenseForm')[0].reset()
          },
          error: function (err) {
            alert("error: " + err)
          }
        });
      });
    });

    function getAllRecord() {
      $.ajax({
        type: "GET",
        url: "expenses/all",
        success: function (response) {
          $('.tr').remove();
          for (let i = 0; i < response.length; i++) {
            $("#expenseTable")
                .append(
                    '<tr class="tr">' +
                    '<td>' + response[i].id + '</td>' +
                    '<td>' + response[i].expenseDate + '</td>' +
                    '<td>' + response[i].description + '</td>' +
                    '<td>' + response[i].amount + '</td>' +
                    '<td>' +
                    '<input type="button" class="btn btn-warning" ' +
                    'onclick="setExpenseInForm(' + response[i].id + ')" ' +
                    'value="Edit">' +
                    '</td> ' +
                    '<td>' +
                    '<input type="button" class="btn btn-danger" ' +
                    'onclick="deleteExpense(' + response[i].id + ');" ' +
                    'value="Delete">' +
                    '</td> ' +
                    '</tr>'
                );
          }
        },
        error: function (err) {
          alert("error: " + err)
        }
      });
    }

    function setExpenseInForm(id) {
      $.ajax({
        type: "GET",
        url: "expenses/" + id,
        success: function (response) {
          $("#id").val(response.id);
          $("#expenseDate").val(response.expenseDate);
          $("#description").val(response.description);
          $("#amount").val(response.amount);

          $('#saveExpense').hide();
          $('#updateExpense').show();
          $('#idfield').show();
        },
        error: function (err) {
          alert("error from getExpense: " + err)
        }
      });
    }

    function updateExpenseBtn() {
      $.ajax({
        type: "POST",
        url: "/expenses/update",
        data: {
          id: $("#id").val(),
          expenseDate: $("#expenseDate").val(),
          description: $("#description").val(),
          amount: $("#amount").val()
        },
        success: function (result) {
          getAllRecord();

          $('#saveExpense').show();
          $('#updateExpense').hide();
          $('#idfield').hide();
          $('#expenseForm')[0].reset();
        },
        error: function (err) {
          alert("error from updateExpenseBtn: " + err)
        }
      });
    }

    function deleteExpense(id) {
      $.ajax({
        type: "DELETE",
        url: "/expenses/delete/" + id,
        success: function (response) {
          getAllRecord();
        },
        error: function (err) {
          alert("error: " + err)
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
      <div class="col-6">
        <h3>Add New Expense</h3>
        <form id="expenseForm" name="expenseform">
          <div class="row" id="idfield">
            <div class="col">
              <div class="form-group">
                <label for="id">ID</label>
                <input type="text" readonly="readonly" class="form-control" id="id" name="id">
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="expenseDate">Date</label>
                <input type="date" class="form-control" id="expenseDate" name="expenseDate" placeholder="Enter Date">
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="description">Description</label>
                <input type="textaria" class="form-control" id="description" name="description"
                       placeholder="Enter Description">
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <div class="form-group">
                <label for="amount">Amount</label>
                <input type="text" class="form-control" id="amount" name="amount" placeholder="Enter Amount">
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col">
              <button type="button" id="saveExpense" class="btn btn-primary">Submit</button>
              <button type="button" id="updateExpense" onclick="updateExpenseBtn()" class="btn btn-primary">
                Update
              </button>
            </div>
          </div>
        </form>
      </div>

      <div class="col-6">
        <h3>Expense Record</h3>
        <br>
        <table class="table table-hover">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Date</th>
            <th scope="col">Description</th>
            <th scope="col">Amount</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
          </tr>
          </thead>
          <tbody id="expenseTable">
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>