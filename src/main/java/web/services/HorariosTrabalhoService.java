/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import web.model.HorariosTrabalhoList;

/**
 *
 * @author Iago
 */
public class HorariosTrabalhoService {
    
    private static List<HorariosTrabalhoList> list = new ArrayList<>();
    
    //função responsável por inserir os registros na lista
    public void Insert(HorariosTrabalhoList insert) {
        list.add(insert);
    }
    
    //função responsável por retornar a lista
    public List<HorariosTrabalhoList> getAll() {
        
        //organiza a lista por horários de forma crescente...
        Collections.sort(list, new Comparator<HorariosTrabalhoList>() {
            public int compare(HorariosTrabalhoList o1, HorariosTrabalhoList o2) {
                return o1.getRegEntrada().compareTo(o2.getRegEntrada());
            }
        });
        
        return list;
    }
    
    //função responsável por remover o registro
    public void remove(int index) {
        
        list.remove(index);
    }
    
    //função responsável por atualizar as informações do registro.
    public void Update(int index, Timestamp entrada, Timestamp saida) {
        
        list.get(index).setRegEntrada(entrada);
        list.get(index).setRegSaida(saida);
    }
    
    public void setRegister(int index, int register) {
        
        list.get(index).setRegister(register);
    }
}
