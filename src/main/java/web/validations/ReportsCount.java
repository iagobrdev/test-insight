/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.validations;

import java.sql.Timestamp;
import java.time.Duration;
import web.model.AtrasosList;
import web.model.HorasExtrasList;
import web.services.AtrasosService;
import web.services.HorariosTrabalhoService;
import web.services.HorasExtrasService;
import web.services.MarcacoesFeitasService;

/**
 *
 * @author Iago
 */
public class ReportsCount {
    
    private static HorasExtrasService horasextras = new HorasExtrasService();
    private static AtrasosService atrasos = new AtrasosService();
    
    public void HoraExtra() {

        HorariosTrabalhoService list = new HorariosTrabalhoService();
        MarcacoesFeitasService marc = new MarcacoesFeitasService();

        horasextras.removeAll();
        
        //vai percorrer os registros de marcações
        for(int i = 0; i < marc.getAll().size(); i++) {

            //essa variável sofrerá alterações, para cada validação de horários, ela receberá o valor do horário de saída anterior
            Timestamp marcEntrada = marc.getAll().get(i).getRegEntrada();
            
            Timestamp _marcEntrada = marcEntrada;
            Timestamp marcSaida = marc.getAll().get(i).getRegSaida();

            Timestamp horaEntrada = null;
            Timestamp horaSaida = null;
            
            //percorre todos os horários de trabalho cadastrados
            for(int x = 0; x < list.getAll().size(); x++) {
                
                horaEntrada = list.getAll().get(x).getRegEntrada();
                horaSaida = list.getAll().get(x).getRegSaida();

                //se a marcação de entrada for antes do horário
                if(marcEntrada.before(horaEntrada)) {
                    
                    //se a marcação de entrada for depois da marcação de saida, encerra o loop por já fez as validações
                    if(marcEntrada.after(marcSaida)) {
                        break;
                    }
                    
                    //se a marcação de saída for depois do horário de entrada
                    if(marcSaida.after(horaEntrada)) {
                        setHoraExtra(_marcEntrada, marcSaida,  marcEntrada, horaEntrada);
                        marcEntrada = horaSaida;
                    }
                    //se a marcação desaída for antes do horário de saída
                    else if(marcSaida.before(horaSaida)) {
                        setHoraExtra(_marcEntrada, marcSaida,  marcEntrada, marcSaida);
                        marcEntrada = horaSaida;
                    }
                }
                //se a marcação for depois do primeiro horário
                else {
                    
                    //se a marcação de entrada não for depois do horário de saída
                    if(!marcEntrada.after(horaSaida)) {
                        marcEntrada = horaSaida;
                    }
                    
                    //se a marcação de entrada for depois da marcação de saida, encerra o loop por já fez as validações
                    if(marcEntrada.after(marcSaida)) {
                        break;
                    }
                }
                
                //essa função irá validar se a comparação está no último registro das horas...
                if(x == list.getAll().size() - 1) {
                    
                    //se a marcação de saída for depois da marcação de entrada...
                    if(marcSaida.after(marcEntrada)) {
                        setHoraExtra(_marcEntrada, marcSaida,  marcEntrada, marcSaida);
                    }
                }
            }
        }
    }
    
    public void Atraso() {

        HorariosTrabalhoService list = new HorariosTrabalhoService();
        MarcacoesFeitasService marc = new MarcacoesFeitasService();
        atrasos.removeAll();
        
        //vai percorrer os registros de marcações
        for(int i = 0; i < marc.getAll().size(); i++) {

            //essa variável sofrerá alterações, para cada validação de horários, ela receberá o valor do horário de saída anterior
            Timestamp marcEntrada = marc.getAll().get(i).getRegEntrada();
            
            Timestamp _marcEntrada = marcEntrada;
            Timestamp marcSaida = marc.getAll().get(i).getRegSaida();

            Timestamp horaEntrada = null;
            Timestamp horaSaida = null;
            
            //percorre todos os horários de trabalho cadastrados
            for(int x = 0; x < list.getAll().size(); x++) {
                
                horaEntrada = list.getAll().get(x).getRegEntrada();
                horaSaida = list.getAll().get(x).getRegSaida();

                //se a marcação de entrada for depois do horário (chegou atraso no início)
                if(marcEntrada.after(horaEntrada)) {
                    
                    //se a marcação de entrada for antes da hora de saída
                    if(!marcEntrada.after(horaSaida)) {
                        setAtraso(_marcEntrada, marcSaida, horaEntrada, marcEntrada);
                    }
                    
                    //se a marcação de saída for antes do horário de saída
                    if(marcSaida.before(horaSaida)) {
                        setAtraso(_marcEntrada, marcSaida,  marcSaida, horaSaida);
                    }
                }
                //se a marcação for antes do primeiro horário (chegou na hora certa)
                else {
                    
                    //se a marcação de saida for antes da hora de saída e a marcação de saída for depois do horário de entrada
                    if(marcSaida.before(horaSaida) && marcSaida.after(horaEntrada)) {
                        setAtraso(_marcEntrada, marcSaida,  marcSaida, horaSaida);
                    }
                }
            }
        }
    }
    
    private static void setHoraExtra(Timestamp marcEntrada, Timestamp marcSaida, Timestamp a, Timestamp b) {
        
        Duration duration = Duration.between(a.toLocalDateTime(), b.toLocalDateTime());
                
        HorasExtrasList set = new HorasExtrasList();
        set.setHoraEntrada(a);
        set.setHoraSaida(b);
        set.setMarcEntrada(marcEntrada);
        set.setMarcSaida(marcSaida);
        set.setDuration(duration);
        horasextras.Insert(set);
    }
    
    private static void setAtraso(Timestamp marcEntrada, Timestamp marcSaida, Timestamp a, Timestamp b) {
        
        Duration duration = Duration.between(a.toLocalDateTime(), b.toLocalDateTime());
                
        AtrasosList set = new AtrasosList();
        set.setHoraEntrada(a);
        set.setHoraSaida(b);
        set.setMarcEntrada(marcEntrada);
        set.setMarcSaida(marcSaida);
        set.setDuration(duration);
        atrasos.Insert(set);
    }
    
   public String returnHoursAndMinutes(Duration duration) {
       
        long minutos = duration.toMinutes() % 60;
        long horas = (duration.toMinutes() - minutos) / 60;

        if(horas < 0) {
            horas = horas * (-1);
        }

        if(minutos < 0) {
            minutos = minutos * (-1);
        }

        return String.format("%02d", horas)+":"+String.format("%02d", minutos);
   }
}
