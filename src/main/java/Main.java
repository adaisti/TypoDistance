
import typodistance.logic.EditDistance;


/**
 *
 * Main class
 * 
 * @author Ada
 */
public class Main {
    
    public static void main(String[] args) {
        
        EditDistance ed = new EditDistance();
        
        // examples of edit and typo distance
        
        System.out.println(ed.ed("vihreä", "virheä"));
        System.out.println(ed.typoDist("vihreä", "virheä"));
        
        // examples of approximate String search with and without weigths
        
        String[] occs1 = ed.ukkonen("suto", "hauskan muotoinen auto on esimerkiksi kuplavolkkari", 3);
        String[] occs2 = ed.typoUkkonen("suto", "hauskan muotoinen auto on esimerkiksi kuplavolkkari", 3);
        
        System.out.println("--");
        
        for (int i = 0; i < occs1.length; i++) {
            System.out.println(occs1[i]);
        }
        
        System.out.println("--");
        
        for (int i = 0; i < occs2.length; i++) {
            System.out.println(occs2[i]);
        }
        
    }
    
    
}
