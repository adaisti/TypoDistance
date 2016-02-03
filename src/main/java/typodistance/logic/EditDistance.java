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

    
    // ei hyvä, mutta en jaksa ajatella
    private int occurenceLength;
    
    public EditDistance() {
        this.occurenceLength = 0;
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
        
        this.occurenceLength = pattern.length();
        
        int m = pattern.length();
        int n = text.length();
        int[] vastaukset = new int[100000];
        int vastausIndeksi = 0;

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
                
                d[i][j] = minimum(d, i, j, p, t);
                
                
            }
            while (d[top][j] > k) {
                    top--;
                }
                
                if (top == m) {
                    vastaukset[vastausIndeksi] = j;
                    vastausIndeksi++;
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
        
        for (int i = 0; i < vastausIndeksi; i++) {
            System.out.print(vastaukset[i] + " ");
        }
        System.out.println(this.occurenceLength);
        return vastaukset;
    }
    
    public int levenhsteinDelta(char a, char b) {
        if (a == b) {
            return 0;
        }
        return 1;
    }

    private int minimum(int[][] d, int i, int j, char p, char t) {
        
        int diag = d[i - 1][j - 1] + levenhsteinDelta(p, t);
        int right = d[i][j - 1] + 1;
        int down = d[i - 1][j] + 1;
        
        if (diag <= down) {
            if (diag <= right) {
                return diag;
            }   
        }
        
        if (down < right) {
            this.occurenceLength--;
            return down;
        }
        this.occurenceLength++;
        return right;
        
//        Math.min(Math.min(d[i - 1][j - 1] + 
//                        levenhsteinDelta(p, t), d[i - 1][j] + 1), 
//                        d[i][j -1] + 1);
    }
    
    
}
