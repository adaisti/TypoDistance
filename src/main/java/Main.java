
import typodistance.logic.EditDistance;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ada
 */
public class Main {
    
    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        ed.ukkonen("atruct", "datastructure", 1);
        
    }
    
    
}
