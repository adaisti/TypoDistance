
package typodistance.logic;

/**
 *
 * This class has all kinds of methods for calculating the Levenhstein
 * edit distance and the weighted typo optimized distance.
 * 
 * @author Ada
 */
public class EditDistance {
    
    public EditDistance() {
    }
    
    /**
     * 
     * Levenhstein edit distance calculation. Returns a distance
     * 3 times bigger than the regular distance in order to be more easily
     * compared with typoDistance
     *
     * 
     * @param pattern
     * @param text
     * @return Levensthein edit distance * 3 between pattern and text
     */
    
    public int ed(String pattern, String text) {
         
        int n = pattern.length();
        int m = text.length();
        
        Cell[][] d = new Cell[n + 1][m + 1];
                
        for (int i = 0; i <= n; i++) {
            d[i][0] = new Cell(i, null, i, 0);
        }
        
        for (int i = 1; i <= m; i++) {
            d[0][i] = new Cell(i, null, 0, i);
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                
                char p = pattern.charAt(i - 1);
                char t = text.charAt(j - 1);
                
                d[i][j] = minimum(d, i, j, p, t);    

            }
        }
        
       
        return d[n][m].dist * 3;
    }
    
    /**
     * Weighted typo optimized edit distance
     * 
     * @param pattern
     * @param text
     * @return typo distance between pattern and text
     */
    
    public int typoDist(String pattern, String text) {
        
         
        int n = pattern.length();
        int m = text.length();
        
        Cell[][] d = new Cell[n + 1][m + 1];
                
        for (int i = 0; i <= n; i++) {
            d[i][0] = new Cell(i, null, i, 0);
        }
        
        for (int i = 1; i <= m; i++) {
            d[0][i] = new Cell(i, null, 0, i);
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                
                char p = pattern.charAt(i - 1);
                char t = text.charAt(j - 1);
                
                d[i][j] = typoMinimum(d, i, j, p, t, pattern, text);    

            }
        }
        
        
        return d[n][m].dist;
    }
    
    /**
     * 
     * Ukkonen's cut-off algorithm
     * 
     * @param pattern
     * @param text
     * @param k
     * @return String table of occurences in pattern with k mismatches
     */
    
    
    public String[] ukkonen(String pattern, String text, int k) {
        
        int m = pattern.length();
        int n = text.length();
        int[] occurences = new int[n + 1];
        int occurenceIndex = 0;
        
        int top = Math.min(k + 1, m);
        
        Cell[][] d = new Cell[m + 1][n + 1];
        
        for (int i = 0; i <= top; i++) {
            d[i][0] = new Cell(i, null, i, 0);
        }
        
        for (int i = 1; i <= n; i++) {
            d[0][i] = new Cell(0, null, 0, i);
        }
        
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= top; i++) {
                
                char p = pattern.charAt(i - 1);
                char t = text.charAt(j - 1);
                
                d[i][j] = minimum(d, i, j, p, t);
                
            }
            while (d[top][j].dist > k) {
                    top--;
                }
                
                if (top == m) {
                    occurences[occurenceIndex] = j;
                    occurenceIndex++;
                } else {
                    top++;
                    d[top][j] = new Cell(k + 1, d[top - 1][j], top, j);
                }
        }
        
        String results[] = new String[occurenceIndex];
        
        for (int i = 0; i < occurenceIndex; i++) {
            results[i] = alignment(occurences[i], d, text);
        }
        
        return results;
    }
    
    /**
     * 
     * Ukkonen's cut-off algorithm with weighted typo optimized edit distances
     * 
     * @param pattern
     * @param text
     * @param k
     * @return String table of occurences in pattern with k mismatches
     */
    
    public String[] typoUkkonen(String pattern, String text, int k) {
        
        int m = pattern.length();
        int n = text.length();
        int[] occurences = new int[n + 1];
        int occurenceIndex = 0;
        
        int top = Math.min(k + 1, m);
        
        Cell[][] d = new Cell[m + 1][n + 1];
        
        for (int i = 0; i <= top; i++) {
            d[i][0] = new Cell (i, null, i, 0);
        }
        
        for (int i = 1; i <= n; i++) {
            d[0][i] = new Cell (0, null, 0, i);
        }
        
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= top; i++) {
                
                char p = pattern.charAt(i - 1);
                char t = text.charAt(j - 1);
                
                d[i][j] = typoMinimum(d, i, j, p, t, pattern, text);
                
            }
            while (d[top][j].dist > k) {
                    top--;
                }
                
                if (top == m) {
                    occurences[occurenceIndex] = j;
                    occurenceIndex++;
                } else {
                    top++;
                    d[top][j] = new Cell(k + 1, d[top - 1][j], top, j);
                }
        }
        
        String results[] = new String[occurenceIndex];
        
        for (int i = 0; i < occurenceIndex; i++) {
            results[i] = alignment(occurences[i], d, text);
        }
        
        return results;
    }
    
    /**
     * Levenhstein edit distance between two characters.
     * @param a
     * @param b
     * @return distance between a and b.
     */
    
    public int levenhsteinDelta(char a, char b) {
        if (a == b) {
            return 0;
        }
        return 1;
    }
    
    /**
     * Weighted edit distance between two characters, based on a Finnish QWERTY keyboard.
     * @param a
     * @param b
     * @return distance between a and b.
     */
    
    public int typoDelta(char a, char b) {
        
        int neighbor = 1;
        int vertical = 2;
        
        if (a == b) {
            return 0;
        }
        
        switch (a) {
            case 'a':
                if (b == 's') {
                    return neighbor;
                }
                if (b == 'q' || b == 'w' || b == 'z' || b == '<') {
                    return vertical;
                }
                break;
            case 'b':
                if  (b == 'v' || b == 'n' ) {
                    return neighbor;
                }
                if (b == ' ' || b == 'h' || b == 'g') {
                    return vertical;
                }
                break;
            case 'c':
                if (b == 'x' || b == 'v' ) {
                    return neighbor;
                }
                if (b == 'd' || b == 'f' || b == ' ' ) {
                    return vertical;
                }
                break;
            case 'd':
                if (b == 's' || b == 'f') {
                    return neighbor;
                }
                if (b == 'e' || b == 'r' || b == 'c' | b == 'x') {
                    return vertical;
                }
                break;
            case 'e':
                if (b == 'w' || b == 'r') {
                    return neighbor;
                }
                if (b == 's' || b == 'd' || b == '3' || b == '4') {
                    return vertical;
                }
                break;
            case 'f':
                if (b == 'd' || b == 'g') {
                    return neighbor;
                }
                if (b == 'r' || b == 't' || b == 'c' || b == 'v') {
                    return vertical;
                }
                break;
            case 'g':
                if (b == 'h' || b == 'f') {
                    return neighbor;
                }
                if (b == 'y' || b == 't' || b == 'b' || b == 'v') {
                    return vertical;
                }
                break;
            case 'h':
                if (b == 'j' || b == 'g') {
                    return neighbor;
                }
                if (b == 'u' || b == 'y' || b == 'b' || b == 'n') {
                    return vertical;
                }
                break;
            case 'i':
                if (b == 'u' || b == 'o') {
                    return neighbor;
                }
                if (b == 'j' || b == 'k' || b == 'o' || b == '8' || b == '9') {
                    return vertical;
                }
                break;
            case 'j':
                if (b == 'h' || b == 'k') {
                    return neighbor;
                }
                if (b == 'n' || b == 'm' || b == 'u' || b == 'i') {
                    return vertical;
                }
                break;
            case 'k':
                if (b == 'j' || b == 'l') {
                    return neighbor;
                }
                if (b == 'i' || b == 'm' || b == 'o' || b == ',') {
                    return vertical;
                }
                break;
            case 'l':
                if (b == 'p' || b == 'o') {
                    return neighbor;
                }
                if (b == 'ö' || b == 'k' || b == 'p' || b == ',' || b == '.') {
                    return vertical;
                }
                break;
            case 'm':
                if (b == 'n' || b == ',') {
                    return neighbor;
                }
                if (b == 'j' || b == 'k' || b == ' ') {
                    return vertical;
                }
                break;
            case 'n':
                if (b == 'b' || b == 'm') {
                    return neighbor;
                }
                if (b == 'j' || b == ' ' || b == 'h') {
                    return vertical;
                }
                break;
            case 'o':
                if (b == 'i' || b == 'p') {
                    return neighbor;
                }
                if (b == 'l' || b == 'k' || b == 'p' || b == '0' || b == '9') {
                    return vertical;
                }
                break;
            case 'p':
                if (b == 'o' || b == 'å') {
                    return neighbor;
                }
                if (b == 'l' || b == 'ö'|| b == '0' || b == '+') {
                    return vertical;
                }
                break;
            case 'q':
                if (b == 'w') {
                    return neighbor;
                }
                if (b == 'a' || b == '1' || b == '2') {
                    return vertical;
                }
                break;
            case 'r':
                if (b == 'e' || b == 't') {
                    return neighbor;
                }
                if (b == 'd' || b == 'f' || b == '4' || b == '5') {
                    return vertical;
                }
                break;
            case 's':
                if (b == 'a' || b == 'd') {
                    return neighbor;
                }
                if (b == 'w' || b == 'e' || b == 'z' || b == 'x') {
                    return vertical;
                }
                break;
            case 't':
                if (b == 'r' || b == 'y') {
                    return neighbor;
                }
                if (b == '5' || b == '6' || b == 'f' || b == 'g') {
                    return vertical;
                }
                break;
            case 'u':
                if (b == 'i' || b == 'y') {
                    return neighbor;
                }
                if (b == '7' || b == '8' || b == 'h' || b == 'j') {
                    return vertical;
                }
                break;
            case 'v':
                if (b == 'c' || b == 'b') {
                    return neighbor;
                }
                if (b == 'f' || b == 'g' || b == ' ') {
                    return vertical;
                }
                break;
            case 'x':
                if (b == 'z' || b == 'c') {
                    return neighbor;
                }
                if (b == 's' || b == 'd') {
                    return vertical;
                }
                break;
            case 'y':
                if (b == 't' || b == 'u') {
                    return neighbor;
                }
                if (b == 'g' || b == 'h' || b == '6' || b == '7') {
                    return vertical;
                }
                break;
            case 'z':
                if (b == '<' || b == 'x') {
                    return neighbor;
                }
                if (b == 's' || b == 'd') {
                    return vertical;
                }
                break;
            case 'å':
                if (b == 'p') {
                    return neighbor;
                }
                if (b == 'ö' || b == 'ä') {
                    return vertical;
                }
                break;
            case 'ä':
                if (b == 'ö') {
                    return neighbor;
                }
                if (b == 'å' || b == '-') {
                    return vertical;
                }
                break;
            case 'ö':
                if (b == 'l' || b == 'ä') {
                    return neighbor;
                }
                if (b == 'p' || b == 'å' || b == '.' || b == '-') {
                    return vertical;
                }
                break;
                    
        }
        
        
        return 3;
    }
    
    /**
     * 
     * Calculates the minimal weighted typo distance for the cells in the dynamic programming matrix.
     * 
     * @param d
     * @param i
     * @param j
     * @param p
     * @param t
     * @param pattern
     * @param text
     * @return Cell with the minimal typo distance
     */
    
    public Cell typoMinimum(Cell[][] d, int i, int j, char p, char t, String pattern, String text) {
                
        int diag = d[i - 1][j - 1].dist + typoDelta(p, t);
        int down = d[i][j - 1].dist + 1;    //common typo: one letter missing
        int right = d[i - 1][j].dist + involuntaryDoubleLetter(i, j, text, pattern); 
        
        
        if (i >= 2 && j >= 2) {
            
            //Damerau distance
            
            if (pattern.charAt(i - 1) == t && text.charAt(j - 1) == p) {
                int dam = d[i - 2][j - 2].dist + 1;
                if (diag >= dam && down >= dam && right >= dam) {
                    return new Cell(dam, d[i - 2][j - 2], i, j);
                } 
            }
        }
        
        if (diag <= down) {
            if (diag <= right) {
                return new Cell(diag, d[i - 1][j - 1], i, j);
            }   
        }
        
        if (down < right) {
            return new Cell(down, d[i][j - 1], i, j);
        }
        return new Cell(right, d[i - 1][j], i, j);
    }
    
    /**
     * 
     * Calculates the minimal distance for the cells in the dynamic programming matrix.
     * 
     * @param d
     * @param i
     * @param j
     * @param p
     * @param t
     * @return Cell with the minimal distance
     */

    public Cell minimum(Cell[][] d, int i, int j, char p, char t) {
        
        int diag = d[i - 1][j - 1].dist + levenhsteinDelta(p, t);
        int down = d[i][j - 1].dist + 1;
        int right = d[i - 1][j].dist + 1;
        
        if (diag <= down) {
            if (diag <= right) {
                return new Cell(diag, d[i - 1][j - 1], i, j);
            }   
        }
        
        if (down < right) {
            return new Cell(down, d[i][j - 1], i, j);
        }
        return new Cell(right, d[i - 1][j], i, j);
    }
    
    /**
     * Searches for the occurence (with k mismatches) of the pattern 
     * in the text by finding the path
     * of minima in the dynamic programmin matrix.
     * 
     * @param index
     * @param d
     * @param text
     * @return occurence in text
     */
    
    public String alignment(int index, Cell[][] d, String text) {
        int last = index;
        
        Cell prev = d[d.length - 1][index];
        
        if (prev == null) {
            return text;
        }
        
        while (prev.j != 0 && prev.i != 0) {
            if (prev.prev == null) {
                break;
            }
            prev = prev.prev;
            
        }
        
        int first = prev.j;
        
        return text.substring(first, last);
        
    }
    
    /**
     * Weights a double letter with a lower weight than a non-double letter
     * 
     * @param i
     * @param j
     * @param text
     * @param pattern
     * @return 1 if double letter, else 3
     */
    
    
    public int involuntaryDoubleLetter(int i, int j, String text, String pattern) {

        if (i < 1 || j < 1) {
            return 3;
        }
        
        if(i == pattern.length()) {
            return 3;
        }
        if (pattern.charAt(i) == pattern.charAt(i - 1) && pattern.charAt(i - 1) == text.charAt(j - 1)) {
            return 1;
        }
        return 3;
    }
    
    
}
