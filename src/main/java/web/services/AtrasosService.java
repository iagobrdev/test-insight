/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.services;

import java.util.ArrayList;
import java.util.List;
import web.model.AtrasosList;

/**
 *
 * @author Iago
 */
public class AtrasosService {
    
    private static List<AtrasosList> list = new ArrayList<>();
    
    //função responsável por inserir os registros na lista
    public void Insert(AtrasosList insert) {
        
        list.add(insert);
    }
    
    //função responsável por retornar a lista
    public List<AtrasosList> getAll() {
        
        return list;
    }
    
    //função responsável por remover o registro
    public void removeAll() {
        
        list.clear();
    }
    
    public void removeIndex(int index) {
        list.remove(index);
    }
}
