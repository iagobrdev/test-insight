<%-- 
    Document   : relatorios
    Created on : 24/01/2024, 13:08:07
    Author     : Iago
--%>

<%@page import="web.services.AtrasosService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="web.services.HorasExtrasService"%>
<%@page import="web.validations.ReportsCount"%>
<%@page import="web.model.HorariosTrabalhoList"%>
<%@page import="web.model.MarcacoesFeitasList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% SimpleDateFormat df = new SimpleDateFormat("HH:mm"); %>
    
<%
    List<HorariosTrabalhoList> horarios = (List<HorariosTrabalhoList>) request.getAttribute("regEntSai");
    List<MarcacoesFeitasList> marcacoes = (List<MarcacoesFeitasList>) request.getAttribute("marcacoes");
%>

<div class="row border rounded" style="padding: 10px">
    <div class="col-md-12">
        <h5><center>Marcações (Atrasos)</center></h5>
        <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">Marcações</th>
                <th scope="col">Horário (Atraso)</th>
                <th scope="col">Total</th>
              </tr>
            </thead>
            <tbody>
            <% if(horarios == null || (horarios != null && horarios.size() == 0)) { %>
            <tr>
                <td colspan="5"><center>Não foram encontrados os registros de Horários de Trabalho...</center></td>
            </tr>
            <% } else if(marcacoes == null || (marcacoes != null && marcacoes.size() == 0)) { %>
            <tr>
                <td colspan="5"><center>Não foram encontrados os registros de Marcações Feitas...</center></td>
            </tr>
            <% } else { 
                    
                ReportsCount reports = new ReportsCount();
                    
                //chama a função responsável por calcular os Atrasos
                reports.Atraso();

                //chama a List que irá retornar os Atrasos
                AtrasosService hours = new AtrasosService();

                for(int i = 0; i < hours.getAll().size(); i++) {
                    
            %>
            <% if(!reports.returnHoursAndMinutes(hours.getAll().get(i).getDuration()).equals("00:00")) { %>
            <tr>
                <td><%= df.format(hours.getAll().get(i).getMarcEntrada()) + " - " + df.format(hours.getAll().get(i).getMarcSaida()) %> </td>
                <td><%= df.format(hours.getAll().get(i).getHoraEntrada()) + " - " + df.format(hours.getAll().get(i).getHoraSaida()) %> </td>
                <td><%= reports.returnHoursAndMinutes(hours.getAll().get(i).getDuration()) %> hrs </td>
            </tr>

            <% } } } %>
            </tbody>
        </table>
    </div>
    <div class="col-md-12">
         <br/>
        <h5><center>Marcações (Horas Extras)</center></h5>
        <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">Marcações</th>
                <th scope="col">Horário (Hora Extra)</th>
                <th scope="col">Total</th>
              </tr>
            </thead>
            <tbody>
            <% if(horarios == null || (horarios != null && horarios.size() == 0)) { %>
            <tr>
                <td colspan="5"><center>Não foram encontrados os registros de Horários de Trabalho...</center></td>
            </tr>
            <% } else if(marcacoes == null || (marcacoes != null && marcacoes.size() == 0)) { %>
            <tr>
                <td colspan="5"><center>Não foram encontrados os registros de Marcações Feitas...</center></td>
            </tr>
            <% } else { 
                    
                ReportsCount reports = new ReportsCount();
                    
                //chama a função responsável por calcular as Horas Extras
                reports.HoraExtra();

                //chama a List que irá retornar as Horas Extras
                HorasExtrasService hours = new HorasExtrasService();

                for(int i = 0; i < hours.getAll().size(); i++) {
                    
            %>
            <% if(!reports.returnHoursAndMinutes(hours.getAll().get(i).getDuration()).equals("00:00")) { %>
            <tr>
                <td><%= df.format(hours.getAll().get(i).getMarcEntrada()) + " - " + df.format(hours.getAll().get(i).getMarcSaida()) %> </td>
                <td><%= df.format(hours.getAll().get(i).getHoraEntrada()) + " - " + df.format(hours.getAll().get(i).getHoraSaida()) %> </td>
                <td><%= reports.returnHoursAndMinutes(hours.getAll().get(i).getDuration()) %> hrs </td>
            </tr>

            <% } } } %>
            </tbody>
        </table>
    </div>
</div>