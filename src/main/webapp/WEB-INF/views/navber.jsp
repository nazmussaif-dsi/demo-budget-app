<nav class="navbar navbar-expand-sm navbar-light bg-light sticky-top" id="mainNav">
  <div class="container">
    <a class="navbar-brand" href="#">Budget App</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
            aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse align-items-center" id="navbarResponsive">
      <ul class="navbar-nav ml-auto align-items-center">
        <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.roles[0].name == 'ADMIN'}">
          <li class="nav-item">
            <a class="nav-link" href="/users">User List</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/categories">Categories</a>
          </li>
        </c:if>
        <li class="nav-item">
          <a class="nav-link" href="/expenses">Expenses</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/logout">${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.name} (Log Out)</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
