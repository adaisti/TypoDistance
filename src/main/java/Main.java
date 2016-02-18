
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
        
        // examples of edit and metrical and non-metrical typo distance
        
        System.out.println(ed.ed("vihreä", "virheä"));
        System.out.println(ed.typoDist("vihreä", "virheä"));
        System.out.println(ed.nonMetricTypoDist("vihreä", "virheä"));
        
        System.out.println("--");
        
        System.out.println(ed.ed("matto", "mato"));
        System.out.println(ed.typoDist("mato", "maito"));
        System.out.println(ed.nonMetricTypoDist("maito", "mato"));
        System.out.println(ed.nonMetricTypoDist("mato", "maito"));
        
        // examples of approximate String search with and without weigths
        
        String[] occs1 = ed.ukkonen("suto", "hauskan muotoinen auto on esimerkiksi kuplavolkkari", 3);
        String[] occs2 = ed.typoUkkonen("suto", "hauskan muotoinen auto on esimerkiksi kuplavolkkari", 3);
        String[] occs3 = ed.nonMetricTypoUkkonen("suto", "hauskan muotoinen auto on esimerkiksi kuplavolkkari", 3);
        
        System.out.println("--");
        
        for (int i = 0; i < occs1.length; i++) {
            System.out.println(occs1[i]);
        }
        
        System.out.println("--");
        
        for (int i = 0; i < occs2.length; i++) {
            System.out.println(occs2[i]);
        }
        
        System.out.println("--");
        
        for (int i = 0; i < occs3.length; i++) {
            System.out.println(occs3[i]);
        }
        
    }
    
    
}
