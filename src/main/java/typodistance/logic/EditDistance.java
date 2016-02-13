/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typodistance.logic;

/**
 *
 * @author Ada
 */
public class EditDistance {

    
    
    public EditDistance() {
    }
    
    
    
//    fori←0  to m do di0←i(
//           (2)forj←1  to n do d0j←j
//            (3)forj←1  to n do
//            (4)fori←1  to m do
//            (5)dij←min{di−1,j−1+δ(A[i],B[j]),di−1,j+ 1,di,j−1+ 1}
//    (6)returndmn
    
    public int ed(String pattern, String text) {
         
        int n = pattern.length();
        int m = text.length();
        
        int[][] d = new int[n + 1][m + 1];
        
        for (int i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        
        for (int i = 1; i <= m; i++) {
            d[0][i] = i;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                
                char p = pattern.charAt(i - 1);
                char t = text.charAt(j - 1);
                
                d[i][j] = minimum(d, i, j, p, t);
                        
                        
                
            }
        }
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.print(d[i][j]);
            }
            System.out.println("");
        }
        
        return d[n][m];
    }
    
    
    public int[] ukkonen(String pattern, String text, int k) {
        
        
        int m = pattern.length();
        int n = text.length();
        int[] occurences = new int[100000];
        int occurenceIndex = 0;

        int top = Math.min(k + 1, m);
        
        int[][] d = new int[m + 1][n + 1];
        
        for (int i = 0; i <= top; i++) {
            d[i][0] = i;
        }
        
        for (int i = 1; i <= n; i++) {
            d[0][i] = 0;
        }
        
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= top; i++) {
                
                char p = pattern.charAt(i - 1);
                char t = text.charAt(j - 1);
                
//                d[i][j] = minimum(d, i, j, p, t);
                d[i][j] = typoMinimum(d, i, j, p, t, pattern, text);
                
            }
            while (d[top][j] > k) {
                    top--;
                }
                
                if (top == m) {
                    occurences[occurenceIndex] = j;
                    occurenceIndex++;
                } else {
                    top++;
                    d[top][j] = k + 1;
                }
        }
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(d[i][j]);
            }
            System.out.println("");
        }
        
        for (int i = 0; i < occurenceIndex; i++) {
            System.out.print(occurences[i] + " ");
        }
        
        System.out.println(occurence(d, occurences, pattern));
        
        return occurences;
    }
    
    public int levenhsteinDelta(char a, char b) {
        if (a == b) {
            return 0;
        }
        return 1;
    }
    
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
    
    public int typoMinimum(int[][] d, int i, int j, char p, char t, String pattern, String text) {
        
        // i: index in pattern, j: index in text
        
        int diag = d[i - 1][j - 1] + levenhsteinDelta(p, t);
        int right = d[i][j - 1] + 1;    //common typo: one letter missing
        int down = d[i - 1][j] + involuntaryDoubleLetter(i, j, text, pattern); 
        
        if (diag <= down) {
            if (diag <= right) {
                return diag;
            }   
        }
        
        if (down < right) {
            return down;
        }
        return right;
    }

    public int minimum(int[][] d, int i, int j, char p, char t) {
        
        int diag = d[i - 1][j - 1] + levenhsteinDelta(p, t);
        int right = d[i][j - 1] + 1;
        int down = d[i - 1][j] + 1;
        
        if (diag <= down) {
            if (diag <= right) {
                return diag;
            }   
        }
        
        if (down < right) {
            return down;
        }
        return right;
    }
    
    
    public String[] occurence(int[][] d, int[] occs, String pattern) {
                //i: index in pattern
        String[] occurences = new String[occs.length];

        for (int occ = 0; occ < occs.length; occ++) {
            
            int occurenceLength = 0;
            int occurenceIndex = occs[occ];
            int i = occs[occ];
            int j = d.length;
            
            while (i > 0 && j > 0) {
                if (d[i - 1][j] < d[i - 1][j - 1] && d[i - 1][j] < d[i][j - 1]) {
                    j--;
                } else {
                    occurenceLength++;
                    if (d[i - 1][j] < d[i - 1][j - 1]) {
                        i--;
                    } else {
                        j--;
                        i--;
                    }
                }
            }
            occurences[occ] = pattern.substring(occurenceIndex - occurenceLength, occurenceIndex + 1);
            
        }
        
        return occurences;
    }

    private int involuntaryDoubleLetter(int i, int j, String text, String pattern) {
        if (text.charAt(j) == text.charAt(j - 1) && pattern.charAt(i - 1) == text.charAt(j - 1)) {
            return 1;
        }
        return 3;
    }
    
    
}
