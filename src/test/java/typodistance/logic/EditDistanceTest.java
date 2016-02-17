/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typodistance.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ada
 */
public class EditDistanceTest {
    
    EditDistance ed;
    Cell[][] d;
    
    public EditDistanceTest() {
        
        ed = new EditDistance();
        d = new Cell[5][5];
    }
    
    @Before
    public void setUp() {
        
        
        for (int i = 0; i < 4; i++) {
            d[i][0] = new Cell(0, null, i, 0);
        }
        
        for (int i = 0; i < 4; i++) {
            d[0][i] = new Cell(0, null, 0, i);
        }
        
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                d[i][j] = new Cell(0, d[i - 1][j - 1], i, j);
            }
        }
        
    }

    @Test
    public void testEd() {
        assertEquals(18, ed.ed("ballad", "handball"));
    }

    @Test
    public void testEdtAS() {
        assertEquals(3, ed.ed("ballad", "ballsd"));
    }
    
    @Test
    public void testTypoDist() {
        assertEquals(9, ed.typoDist("ballad", "handball"));
    }
    
    @Test
    public void testTypoDistAS() {
        assertEquals(1, ed.typoDist("ballad", "ballsd"));
    }

    @Test
    public void testUkkonen() {
        assertEquals("auto", ed.ukkonen("suto", "hauskan muotoinen auto on esimerkiksi kuplavolkkari", 1)[0]);
    }

    @Test
    public void testTypoUkkonen() {
        assertEquals("auto", ed.ukkonen("suto", "hauskan muotoinen auto on esimerkiksi kuplavolkkari", 1)[0]);
    }

    @Test
    public void testLevenhsteinDelta() {
        assertEquals(1, ed.levenhsteinDelta('a', 'b'));
    }

    @Test
    public void testTypoDelta() {
        assertEquals(3, ed.typoDelta('a', 'b'));
    }

    @Test
    public void testTypoMinimum() {
        assertEquals(0, ed.typoMinimum(d, 3, 3, 'a', 'a', "aaaa", "abba").dist);
    }

    @Test
    public void testMinimum() {
        assertEquals(0, ed.minimum(d, 3, 3, 'a', 'a').dist);
    }

    @Test
    public void testAlignment() {
        assertEquals("sato", ed.alignment(3, d, "sato"));
    }

    @Test
    public void testInvoluntaryDoubleLetterIsDouble() {
        assertEquals(1, ed.involuntaryDoubleLetter(3, 3, "muki", "mukki"));
    }
    
    @Test
    public void testInvoluntaryDoubleLetterIsNotDouble() {
        assertEquals(3, ed.involuntaryDoubleLetter(2, 2, "muki", "muni"));
    }
}
