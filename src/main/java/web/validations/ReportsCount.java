/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.validations;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
                        setHoraExtra(_marcEntrada, marcSaida, marcEntrada, horaEntrada);
                        marcEntrada = horaSaida;
                    }
                    //se a marcação desaída for antes do horário de saída
                    else if(marcSaida.before(horaSaida)) {
                        setHoraExtra(_marcEntrada, marcSaida, marcEntrada, marcSaida);
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
                        setHoraExtra(_marcEntrada, marcSaida, marcEntrada, marcSaida);
                    }
                }
            }
        }
    }
    
    public void Atraso() {

        HorariosTrabalhoService list = new HorariosTrabalhoService();
        MarcacoesFeitasService marc = new MarcacoesFeitasService();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        atrasos.removeAll();
        
        for(int i = 0; i < list.getAll().size(); i++) {
            list.setRegister(i, 0);
        }
        
        //vai percorrer os registros de marcações
        for(int i = 0; i < marc.getAll().size(); i++) {

            //essa variável sofrerá alterações, para cada validação de horários, ela receberá o valor do horário de saída anterior
            Timestamp marcEntrada = marc.getAll().get(i).getRegEntrada();
            Timestamp marcSaida = marc.getAll().get(i).getRegSaida();

            //percorre todos os horários de trabalho cadastrados
            for(int x = 0; x < list.getAll().size(); x++) {
                
                Timestamp horaEntrada = list.getAll().get(x).getRegEntrada();
                Timestamp horaSaida = list.getAll().get(x).getRegSaida();

                //verifica se a marcação está entre o horário de entrada ou saída
                if((marcEntrada.after(horaEntrada) && marcEntrada.before(horaSaida)) || (marcSaida.after(horaEntrada) && marcSaida.before(horaSaida))) {
                    
                    //informa que o horário em questão foi trabalhado...
                    list.setRegister(x, 1);
                    
                    //caso a marcação seja maior que o horário de entrada (atraso)
                    if(marcEntrada.after(horaEntrada)) {
                        
                        //se a marcação de saída for antes do horário de saída, ira setar dois atrasos (na entrada e na saída)
                        if(marcSaida.before(horaSaida)) {
                            setAtraso(marcEntrada, marcSaida, horaEntrada, horaSaida, horaEntrada, marcEntrada);
                            setAtraso(marcEntrada, marcSaida, horaEntrada, horaSaida, marcSaida, horaSaida);
                        }
                        //se a marcação de saída não for antes do horario de saida, irá setar somente o atraso na entrada 
                        else {
                            setAtraso(marcEntrada, marcSaida, horaEntrada, horaSaida, horaEntrada, marcEntrada);                        
                        }
                    }
                    //caso a marcação seja dentro do horário de entrada...
                    else {
                        //se a marcação de saída for antes do horário de saída, irá setar a saída antecipada
                        if(marcSaida.before(horaSaida)) {
                            setAtraso(marcEntrada, marcSaida, horaEntrada, horaSaida, marcSaida, horaSaida);
                        }
                        //caso não houve a saída antecipada, irá informar que foi trabalhado o período completo
                        else {
                            setAtraso(marcEntrada, marcSaida, horaEntrada, horaSaida, horaEntrada, horaSaida);
                        }
                    }
                }
                else {
                    
                    //caso as marcações não estiverem entre os horários, irá verifica se o período entre as marcações e o horário coincide
                    if((marcEntrada.before(horaEntrada) && marcSaida.after(horaSaida)) || 
                       (marcEntrada.before(horaEntrada) && df.format(marcSaida).equals(df.format(horaSaida))) || 
                       (df.format(marcEntrada).equals(df.format(horaEntrada)) && marcSaida.after(horaSaida)) || 
                       (df.format(marcEntrada).equals(df.format(horaEntrada)) && df.format(marcSaida).equals(df.format(horaSaida)))) {
                        
                            list.setRegister(x, 1);
                    }
                }
            }
        }
        
        //todo horário com o register == 0, significa que não foi trabalho, então irá setar na tabela de atrasos o período inteiro.
        for(int i = 0; i < list.getAll().size(); i++) {
            
            if(list.getAll().get(i).getRegister() == 0) {
                setAtraso(list.getAll().get(i).getRegEntrada(), list.getAll().get(i).getRegSaida(), list.getAll().get(i).getRegEntrada(), list.getAll().get(i).getRegSaida(), list.getAll().get(i).getRegEntrada(), list.getAll().get(i).getRegSaida());
            }
        }
        
        //chama função que irá compor as marcações que estão dentro do mesmo horário...
        validateDelays();
    }
    
    private static void validateDelays() {
        
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        ReportsCount clas = new ReportsCount();
        
        for(int i = 0; i < atrasos.getAll().size(); i++) {
            
            //se a diferença em horas for = 00:00 não fará nada...
            if(!clas.returnHoursAndMinutes(atrasos.getAll().get(i).getDuration()).equals("00:00")) {
            
                //verifica se existe mais registros na list
                if(i + 1 < atrasos.getAll().size()) {
                    
                    Timestamp a = atrasos.getAll().get(i).getA();
                    Timestamp proxB = atrasos.getAll().get(i + 1).getB();
                    Timestamp horaSaida = atrasos.getAll().get(i).getHoraSaida();
                    
                    //compara se a marcação de entrada do próximo registro é maior que a marcação de saída atual e se a marcação do proximo registro é antes do horário de saída
                    if(proxB.after(a) && proxB.before(horaSaida)) {

                        Duration duration = Duration.between(atrasos.getAll().get(i).getMarcSaida().toLocalDateTime(), atrasos.getAll().get(i + 1).getMarcEntrada().toLocalDateTime());

                        atrasos.update(i, atrasos.getAll().get(i + 1).getB(), duration);

                        //se depois da atualização acima, o próximo registro B for igual ao registro B atual, ira setar diferença = 00:00 (não será exibido no browzer)
                        if(df.format(atrasos.getAll().get(i + 1).getB()).equals(df.format(atrasos.getAll().get(i).getB()))) {
                            
                            duration = Duration.between(atrasos.getAll().get(i).getB().toLocalDateTime(), atrasos.getAll().get(i).getB().toLocalDateTime());
                            atrasos.update(i + 1, atrasos.getAll().get(i).getB(), duration);
                        }
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
    
    private static void setAtraso(Timestamp marcEntrada, Timestamp marcSaida, Timestamp horaEntrada, Timestamp horaSaida, Timestamp a, Timestamp b) {
        
        Duration duration = Duration.between(a.toLocalDateTime(), b.toLocalDateTime());
                
        AtrasosList set = new AtrasosList();
        set.setHoraEntrada(horaEntrada);
        set.setHoraSaida(horaSaida);
        set.setMarcEntrada(marcEntrada);
        set.setMarcSaida(marcSaida);
        set.setA(a);
        set.setB(b);
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
