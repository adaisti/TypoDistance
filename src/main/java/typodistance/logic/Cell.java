
package typodistance.logic;

/**
 *
 * Cells for the dynamic programming matrix in Ukkonen's cut-off algorithm.
 * 
 * @author Ada
 */
public class Cell {
    Cell prev;
    int dist;
    int i;
    int j;
    
    public Cell(int dist, Cell prev, int i, int j) {
        this.prev = prev;
        this.dist = dist;
        this.i = i;
        this.j = j;
    }
    
    
    
}
