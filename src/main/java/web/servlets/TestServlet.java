/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.model.HorariosTrabalhoList;
import web.model.MarcacoesFeitasList;
import web.services.HorariosTrabalhoService;
import web.services.MarcacoesFeitasService;
import web.validations.HourValidations;

/**
 *
 * @author Iago
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/home"})
public class TestServlet extends HttpServlet {

    HorariosTrabalhoService regEntSai = new HorariosTrabalhoService();
    MarcacoesFeitasService marcacoes = new MarcacoesFeitasService();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        //Método dispatcher irá passar os registros inseridos para o jsp.
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        request.setAttribute("page", "registrations");
        request.setAttribute("regEntSai", regEntSai.getAll());
        request.setAttribute("marcacoes", marcacoes.getAll());
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        //parâmetros capturados do jsp
        String regEntrada = request.getParameter("regEntrada");
        String regSaida = request.getParameter("regSaida");
        String editEntrada = request.getParameter("editEntrada");
        String editSaida = request.getParameter("editSaida");
        String editID = request.getParameter("editID");
        String removeID = request.getParameter("removeID");
        String marcEntrada = request.getParameter("marcEntrada");
        String marcSaida = request.getParameter("marcSaida");
        String marcEditEntrada = request.getParameter("marcEditEntrada");
        String marcEditSaida = request.getParameter("marcEditSaida");
        String marcEditID = request.getParameter("marcEditID");
        String marcRemoveID = request.getParameter("marcRemoveID");
        
        Date _date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dff = new SimpleDateFormat("HH:mm");
        
        //verifica a ação foi o CADASTRO de "Horários de Trabalho"
        if((regEntrada != null && regEntrada != "") && (regSaida != null && regSaida != "")) {
            
            //limite de 3 registros conforme solicitado
            if(regEntSai.getAll().size() < 3) {

                //converte os registros já armazenados em timestamp (data + hora) para realizar as comparações
                Timestamp _entrada = Timestamp.valueOf(df.format(new Date()) + " " + regEntrada + ":00");
                Timestamp _saida = Timestamp.valueOf(df.format(new Date()) + " " + regSaida+ ":00");
                
                //verifica se a hora de SAÍDA é menor que a de ENTRADA, se for, adiciona um dia a mais no horário de SAIDA (Ex.: 23:00 - 03:00) significa que entra as 23:00 hrs de um dia e sai as 03:00 hrs do outro dia.
                if(_saida.before(_entrada)) {
                    _date.setDate(_date.getDate() - 1);
                    _entrada = Timestamp.valueOf(df.format(_date) + " " + dff.format(_entrada) + ":00");
                }
                
                //chama a classe responsável pelas validações de horários e retorna uma mensagem do tipo String
                HourValidations val = new HourValidations();
                String msgm = val.validationHours(_entrada, _saida, null);
                
                //se o retorno for = sucess, as informações serão inseridas no List
                if(msgm.equals("sucess")) {
                    
                    //se a validação der ok, insere os novos horários na List
                    HorariosTrabalhoList regList = new HorariosTrabalhoList();
                    regList.setRegEntrada(_entrada);
                    regList.setRegSaida(_saida);
                    regList.setRegister(0);
                    regEntSai.Insert(regList);

                    response.sendRedirect("home");
                }
                //se o retorno for != sucess, exibira a mensagem de erro no browzer
                else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    request.setAttribute("msgmreg", msgm);
                    request.setAttribute("regEntSai", regEntSai.getAll());
                    request.setAttribute("marcacoes", marcacoes.getAll());
                    dispatcher.forward(request, response);
                }
            }
            //se cair nesse else, atualiza a página enviando uma msgm (Você não pode cadastrar mais que 3 registros!).
            else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                request.setAttribute("msgmreg", "Você não pode cadastrar mais que 3 registros!");
                request.setAttribute("regEntSai", regEntSai.getAll());
                request.setAttribute("marcacoes", marcacoes.getAll());
                dispatcher.forward(request, response);
            }
        }
        
        //verifica a ação foi o EDITAR "Horários de Trabalho"
        else if(editID != null && editID != "") {
            
            //converte os registros já armazenados em timestamp (data + hora) para realizar as comparações
            Timestamp _entrada = Timestamp.valueOf(df.format(new Date()) + " " + editEntrada + ":00");
            Timestamp _saida = Timestamp.valueOf(df.format(new Date()) + " " + editSaida+ ":00");
            
            //verifica se a hora de SAÍDA é menor que a de ENTRADA, se for, adiciona um dia a mais no horário de SAIDA (Ex.: 23:00 - 03:00) significa que entra as 23:00 hrs de um dia e sai as 03:00 hrs do outro dia.
            if(_saida.before(_entrada)) {
                _date.setDate(_date.getDate() - 1);
                _entrada = Timestamp.valueOf(df.format(_date) + " " + dff.format(_entrada) + ":00");
            }
            
            //chama a classe responsável pelas validações de horários e retorna uma mensagem do tipo String
            HourValidations val = new HourValidations();
            String msgm = val.validationHours(_entrada, _saida, Integer.parseInt(editID));
            
            //se o retorno for = sucess, as informações serão inseridas no List
            if(msgm.equals("sucess")) {
                
                //se a validação der ok, atualiza os dados do registro passando o index da List como referência.
                regEntSai.Update(Integer.parseInt(editID), _entrada, _saida);

                response.sendRedirect("home");
            }
            else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                request.setAttribute("msgmreg", msgm);
                request.setAttribute("regEntSai", regEntSai.getAll());
                request.setAttribute("marcacoes", marcacoes.getAll());
                dispatcher.forward(request, response);
            }
        }
        
        //verifica a ação foi o REMOVER "Horários de Trabalho"
        else if(removeID != null && removeID != "") {
            
            regEntSai.remove(Integer.parseInt(removeID));
            
            response.sendRedirect("home");
        }
        
        //verifica a ação foi o CADASTRO de "Marcações Feitas"
        else if((marcEntrada != null && marcEntrada != "") && (marcSaida != null && marcSaida != "")) {
            
            if(!marcEntrada.equals(marcSaida)) {
                
                //converte os registros já armazenados em timestamp (data + hora) para realizar as comparações
                Timestamp _entrada = Timestamp.valueOf(df.format(new Date()) + " " + marcEntrada + ":00");
                Timestamp _saida = Timestamp.valueOf(df.format(new Date()) + " " + marcSaida+ ":00");

                //verifica se a hora de SAÍDA é menor que a de ENTRADA, se for, adiciona um dia a mais no horário de SAIDA (Ex.: 23:00 - 03:00) significa que entra as 23:00 hrs de um dia e sai as 03:00 hrs do outro dia.
                if(_saida.before(_entrada)) {
                    _date.setDate(_date.getDate() - 1);
                    _entrada = Timestamp.valueOf(df.format(_date) + " " + dff.format(_entrada) + ":00");
                }

                //chama a classe responsável pelas validações de horários e retorna uma mensagem do tipo String
                HourValidations val = new HourValidations();
                String msgm = val.validationMarkings(_entrada, _saida, null);
                
                //se o retorno for = sucess, as informações serão inseridas no List
                if(msgm.equals("sucess")) {
                
                    MarcacoesFeitasList marcList = new MarcacoesFeitasList();
                    marcList.setRegEntrada(_entrada);
                    marcList.setRegSaida(_saida);
                    marcacoes.Insert(marcList);

                    response.sendRedirect("home");
                }
                else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    request.setAttribute("msgmmarc", msgm);
                    request.setAttribute("regEntSai", regEntSai.getAll());
                    request.setAttribute("marcacoes", marcacoes.getAll());
                    dispatcher.forward(request, response);
                }
            }
            else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                request.setAttribute("msgmmarc", "As horas não podem ser iguais!");
                request.setAttribute("regEntSai", regEntSai.getAll());
                request.setAttribute("marcacoes", marcacoes.getAll());
                dispatcher.forward(request, response);
            }
        }
        
        //verifica a ação foi o EDITAR "Marcações Feitas"
        else if(marcEditID != null && marcEditID != "") {
            
            if(!marcEditEntrada.equals(marcEditSaida)) {
            
                //converte os registros já armazenados em timestamp (data + hora) para realizar as comparações
                Timestamp _entrada = Timestamp.valueOf(df.format(new Date()) + " " + marcEditEntrada + ":00");
                Timestamp _saida = Timestamp.valueOf(df.format(new Date()) + " " + marcEditSaida+ ":00");

                //verifica se a hora de SAÍDA é menor que a de ENTRADA, se for, adiciona um dia a mais no horário de SAIDA (Ex.: 23:00 - 03:00) significa que entra as 23:00 hrs de um dia e sai as 03:00 hrs do outro dia.
                if(_saida.before(_entrada)) {
                    _date.setDate(_date.getDate() - 1);
                    _entrada = Timestamp.valueOf(df.format(_date) + " " + dff.format(_entrada) + ":00");
                }

                //chama a classe responsável pelas validações de horários e retorna uma mensagem do tipo String
                HourValidations val = new HourValidations();
                String msgm = val.validationMarkings(_entrada, _saida, Integer.parseInt(marcEditID));
                
                //se o retorno for = sucess, as informações serão inseridas no List
                if(msgm.equals("sucess")) {
                    //se a validação der ok, atualiza os dados do registro passando o index da List como referência.
                    marcacoes.Update(Integer.parseInt(marcEditID), _entrada, _saida);

                    response.sendRedirect("home");
                }
                else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    request.setAttribute("msgmmarc", msgm);
                    request.setAttribute("regEntSai", regEntSai.getAll());
                    request.setAttribute("marcacoes", marcacoes.getAll());
                    dispatcher.forward(request, response);
                }
            }
            else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                request.setAttribute("msgmmarc", "As horas não podem ser iguais!");
                request.setAttribute("regEntSai", regEntSai.getAll());
                request.setAttribute("marcacoes", marcacoes.getAll());
                dispatcher.forward(request, response);
            }
        }
        
        //verifica a ação foi o REMOVER "Marcações Feitas"
        else if(marcRemoveID != null && marcRemoveID != "") {
            
            marcacoes.remove(Integer.parseInt(marcRemoveID));
            
            response.sendRedirect("home");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
