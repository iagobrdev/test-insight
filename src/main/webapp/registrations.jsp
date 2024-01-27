<%-- 
    Document   : registrations
    Created on : 24/01/2024, 13:17:31
    Author     : Iago
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="web.model.HorariosTrabalhoList"%>
<%@page import="java.util.List"%>
<%@page import="web.model.MarcacoesFeitasList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Object msgm = request.getAttribute("msgmreg"); %>
<% List<HorariosTrabalhoList> regEntSai = (List<HorariosTrabalhoList>)request.getAttribute("regEntSai"); %>
<% Object msgmmarc = request.getAttribute("msgmmarc"); %>
<% List<MarcacoesFeitasList> marcList = (List<MarcacoesFeitasList>)request.getAttribute("marcacoes"); %>
<% SimpleDateFormat df = new SimpleDateFormat("HH:mm"); %> 

<div class="row border rounded" style="padding: 10px">
    <div class="col-md-6">
        <h5>Horários de Trabalho</h5>
        <form method="post" action="home">
            <div class="row">
                <div class="col-md-6" style="padding: 10px">
                    <label><b>Entrada</b></label>
                    <input type="time" class="form-control" value="" name="regEntrada" required />
                </div>
                <div class="col-md-6" style="padding: 10px">
                    <label><b>Saída</b></label>
                    <input type="time" class="form-control" value="" name="regSaida" required />
                </div>
                <div class="col-md-12" style="padding: 10px">
                    <button type="submit" class="btn btn-primary" value="Save">
                        Cadastrar
                    </button>
                </div>
                <% if(msgm != null) { %>
                    <div class="col-md-12 alert alert-danger" role="alert">
                        <%= request.getAttribute("msgmreg") %>
                    </div>
                <% } %>
            </div>
        </form>
    </div>
    <div class="col-md-6">
        <h5><center>Horários Cadastrados</center></h5>
        <table class="table table-striped">
            <thead>
              <tr>
                <th></th>
                <th scope="col">Entrada</th>
                <th scope="col">Saída</th>
                <th scope="col"></th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
                <% if(regEntSai != null) { int i = 0; for(HorariosTrabalhoList reg: regEntSai) { %>
                <form method="post" action="home">
                    <tr>
                        <td><input type="hidden" value="<%= (i) %>" name="removeID"/></td>
                        <td><%= df.format(reg.getRegEntrada()) %> hrs </td>
                        <td><%= df.format(reg.getRegSaida()) %> hrs </td>
                        <td>
                            <button type="button" class="btn btn-outline-primary btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal<%= (i) %>">
                                Editar
                            </button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-outline-danger btn-sm" onclick="return confirm('Deseja remover esse registro?');">
                                Remover
                            </button>
                        </td>
                    </tr>
                </form>
                <!-- Modal -->
                <div class="modal fade" id="exampleModal<%= (i) %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Editar Registro</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <form method="post" action="home">
                        <div class="modal-body">
                            <input type="hidden" value="<%= (i) %>" name="editID"/>
                            <div class="row">
                                <div class="col-md-6" style="padding: 10px">
                                    <label><b>Entrada</b></label>
                                    <input type="time" class="form-control" value="<%= df.format(reg.getRegEntrada()) %>" name="editEntrada" class="timepicker"/>
                                </div>
                                <div class="col-md-6" style="padding: 10px">
                                    <label><b>Saída</b></label>
                                    <input type="time" class="form-control" value="<%= df.format(reg.getRegSaida()) %>" name="editSaida" class="timepicker"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                          <input type="submit" class="btn btn-primary" name="UpdateRegistro" value="Atualizar">
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
                <% i++; } } %>
            </tbody>
        </table>
    </div>
</div>
<br/>
<div class="row border rounded" style="padding: 10px">
    <div class="col-md-6">
        <h5>Marcações Feitas</h5>
        <form method="post" action="home">
            <div class="row">
                <div class="col-md-6" style="padding: 10px">
                    <label><b>Entrada</b></label>
                    <input type="time" class="form-control" value="" name="marcEntrada" required />
                </div>
                <div class="col-md-6" style="padding: 10px">
                    <label><b>Saída</b></label>
                    <input type="time" class="form-control" value="" name="marcSaida" required />
                </div>
                <div class="col-md-12" style="padding: 10px">
                    <button type="submit" class="btn btn-primary" value="SaveMarc">
                        Cadastrar
                    </button>
                </div>
                <% if(msgmmarc != null) { %>
                    <div class="col-md-12 alert alert-danger" role="alert">
                        <%= request.getAttribute("msgmmarc") %>
                    </div>
                <% } %>
            </div>
        </form>
    </div>
    <div class="col-md-6">
        <h5><center>Marcações Cadastradas</center></h5>
        <table class="table table-striped">
            <thead>
              <tr>
                <th></th>
                <th scope="col">Entrada</th>
                <th scope="col">Saída</th>
                <th scope="col"></th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
                <% if(marcList != null) { int i = 0; for(MarcacoesFeitasList reg: marcList) { %>
                <form method="post" action="home">
                    <tr>
                        <td><input type="hidden" value="<%= (i) %>" name="marcRemoveID"/></td>
                        <td><%= df.format(reg.getRegEntrada()) %> hrs </td>
                        <td><%= df.format(reg.getRegSaida()) %> hrs </td>
                        <td>
                            <button type="button" class="btn btn-outline-primary btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModalEdit<%= (i) %>">
                                Editar
                            </button>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-outline-danger btn-sm" onclick="return confirm('Deseja remover esse registro?');">
                                Remover
                            </button>
                        </td>
                    </tr>
                </form>
                <!-- Modal -->
                <div class="modal fade" id="exampleModalEdit<%= (i) %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Editar Registro</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <form method="post" action="home">
                        <div class="modal-body">
                            <input type="hidden" value="<%= (i) %>" name="marcEditID"/>
                            <div class="row">
                                <div class="col-md-6" style="padding: 10px">
                                    <label><b>Entrada</b></label>
                                    <input type="time" class="form-control" value="<%= df.format(reg.getRegEntrada()) %>" name="marcEditEntrada" class="timepicker"/>
                                </div>
                                <div class="col-md-6" style="padding: 10px">
                                    <label><b>Saída</b></label>
                                    <input type="time" class="form-control" value="<%= df.format(reg.getRegSaida()) %>" name="marcEditSaida" class="timepicker"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                          <input type="submit" class="btn btn-primary" name="UpdateRegistro" value="Atualizar">
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
                <% i++; } } %>
            </tbody>
        </table>
    </div>
</div>
