/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.validations;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import web.services.HorariosTrabalhoService;
import web.services.MarcacoesFeitasService;

/**
 *
 * @author Iago
 */
public class HourValidations {
    
    public String validationHours(Timestamp entrada, Timestamp saida, Integer index) {
        
        String msgm = "sucess";
        
        try {
            HorariosTrabalhoService list = new HorariosTrabalhoService();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            //A data de ENTRADA e de SAÍDA não podem ser iguais...
            if(df.format(entrada).equals(df.format(saida))) {
                msgm = "Os horários não podem ser iguais!";
            }
            
            //se o status (msgm) não for alterado irá iniciar as outras validações, caso contrário já retorna o erro no browzer
            if(msgm.equals("sucess")) {
                
                //percorre a lista de horários já cadastrados para validar possíveis conflitos de horários
                for(int i = 0; i < list.getAll().size(); i++) {
                    
                    //se for passado um valor != null na variável index, significa que é pra ignorar a validação do registro i da lista pois se se trata da validação de um registro já inserido (EDIÇÃO DE HORARIO)
                    if(index == null || (index != null && index != i )) {
                        //converte os registros já armazenados em timestamp (data + hora) para realizar as comparações
                        Timestamp regEntrada = list.getAll().get(i).getRegEntrada();
                        Timestamp regSaida = list.getAll().get(i).getRegSaida();

                        //abaixo todas as possíveis validações para confirma se os horarios que estão sendo cadastrados não coincidem com os que já estão na List.
                        if(entrada.equals(list.getAll().get(i).getRegEntrada()) || entrada.equals(list.getAll().get(i).getRegSaida()) || saida.equals(list.getAll().get(i).getRegEntrada()) || saida.equals(list.getAll().get(i).getRegSaida())) {
                            msgm = "Os horários não podem coincidir!";
                            break;
                        }
                        else  if((entrada.after(regEntrada) && entrada.before(regSaida)) || (saida.after(regEntrada) && saida.before(regSaida))) {
                            msgm = "Os horários não podem coincidir!";
                            break;
                        }
                        else if(regEntrada.after(entrada) && regSaida.before(saida)) {
                            msgm = "Os horários não podem coincidir!";
                            break;
                        }
                    }
                }
            }
        } catch(Exception e) { e.printStackTrace();}
        
        return msgm;
    }
    
    public String validationMarkings(Timestamp entrada, Timestamp saida, Integer index) {
        
        String msgm = "sucess";
        
        try {
            MarcacoesFeitasService list = new MarcacoesFeitasService();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            //A data de ENTRADA e de SAÍDA não podem ser iguais...
            if(df.format(entrada).equals(df.format(saida))) {
                msgm = "Os horários não podem ser iguais!";
            }
            
            //se o status (msgm) não for alterado irá iniciar as outras validações, caso contrário já retorna o erro no browzer
            if(msgm.equals("sucess")) {
                
                //percorre a lista de horários já cadastrados para validar possíveis conflitos de horários
                for(int i = 0; i < list.getAll().size(); i++) {
                    
                    //se for passado um valor != null na variável index, significa que é pra ignorar a validação do registro i da lista pois se se trata da validação de um registro já inserido (EDIÇÃO DE HORARIO)
                    if(index == null || (index != null && index != i )) {
                        //converte os registros já armazenados em timestamp (data + hora) para realizar as comparações
                        Timestamp regEntrada = list.getAll().get(i).getRegEntrada();
                        Timestamp regSaida = list.getAll().get(i).getRegSaida();

                        //abaixo todas as possíveis validações para confirma se os horarios que estão sendo cadastrados não coincidem com os que já estão na List.
                        if(entrada.equals(list.getAll().get(i).getRegEntrada()) || entrada.equals(list.getAll().get(i).getRegSaida()) || saida.equals(list.getAll().get(i).getRegEntrada()) || saida.equals(list.getAll().get(i).getRegSaida())) {
                            msgm = "Os horários não podem coincidir!";
                            break;
                        }
                        else  if((entrada.after(regEntrada) && entrada.before(regSaida)) || (saida.after(regEntrada) && saida.before(regSaida))) {
                            msgm = "Os horários não podem coincidir!";
                            break;
                        }
                        else if(regEntrada.after(entrada) && regSaida.before(saida)) {
                            msgm = "Os horários não podem coincidir!";
                            break;
                        }
                    }
                }
            }
        } catch(Exception e) { e.printStackTrace();}
        
        return msgm;
    }
}
