/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.model;

import java.sql.Timestamp;
import java.time.Duration;

/**
 *
 * @author Iago
 */
public class AtrasosList {
    
    private Timestamp marcEntrada;
    private Timestamp marcSaida;
    private Timestamp horaEntrada;
    private Timestamp horaSaida;
    private Timestamp a;
    private Timestamp b;
    private Duration duration;

    public Timestamp getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Timestamp horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Timestamp getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Timestamp horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Timestamp getMarcEntrada() {
        return marcEntrada;
    }

    public void setMarcEntrada(Timestamp marcEntrada) {
        this.marcEntrada = marcEntrada;
    }

    public Timestamp getMarcSaida() {
        return marcSaida;
    }

    public void setMarcSaida(Timestamp marcSaida) {
        this.marcSaida = marcSaida;
    }

    public Timestamp getA() {
        return a;
    }

    public void setA(Timestamp a) {
        this.a = a;
    }

    public Timestamp getB() {
        return b;
    }

    public void setB(Timestamp b) {
        this.b = b;
    }
    
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
