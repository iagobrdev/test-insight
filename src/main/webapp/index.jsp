<%-- 
    Document   : newjsp
    Created on : 23/01/2024, 18:46:08
    Author     : Iago
--%>

<%@page import="web.model.MarcacoesFeitasList"%>
<%@page import="java.util.List"%>
<%@page import="web.model.HorariosTrabalhoList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Insight Test - Iago Ribeiro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(function () {
            setTimeout(function () {
                if ($(".alert").is(":visible")){
                     //you may add animate.css class for fancy fadeout
                    $(".alert").fadeOut("fast");
                }
            }, 3000)
        });
    </script>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="home">Cadastros</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="reports">Relatórios</a>
              </li>
            </ul>
            <form class="d-flex">
              <a href="https://github.com/iagobrdev/test-insight" class="btn btn-primary" target="_blank" >GIT</a>
            </form>
          </div>
        </div>
    </nav>
    <div class="container">
        <br/>
        <% if(request.getAttribute("page") == null) { %>
            <jsp:include page="registrations.jsp" />
        <% } else { %>
            <% if(request.getAttribute("page") == "registrations") { %>
                <jsp:include page="registrations.jsp" />
            <% } else if(request.getAttribute("page") == "reports") { %>
                <jsp:include page="reports.jsp" />
            <% } %>
        <% } %>
    </div>
  </body>
</html>
