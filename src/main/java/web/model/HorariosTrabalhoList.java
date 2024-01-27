/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.model;

import java.sql.Timestamp;

/**
 *
 * @author Iago
 */
public class HorariosTrabalhoList {
    
    private Timestamp regEntrada;
    private Timestamp regSaida;

    public Timestamp getRegEntrada() {
        return regEntrada;
    }

    public void setRegEntrada(Timestamp entrada) {
        this.regEntrada = entrada;
    }

    public Timestamp getRegSaida() {
        return regSaida;
    }

    public void setRegSaida(Timestamp regSaida) {
        this.regSaida = regSaida;
    }
    
    public int compareTo(HorariosTrabalhoList o) {
      return getRegEntrada().compareTo(o.getRegEntrada());
    }
}
