/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author T430s
 */
public class Piece implements IPiece {
    
    public static final int TILESIZE = 32;
    private static Image image = new ImageIcon("pieces4.png").getImage();
    private static Map<Integer,Piece> pieces = new HashMap<>();
    private int index;
    private int x;
    private int y;

    private Piece(int idx) {
        this.index = idx;
    }

    public void draw(Graphics2D g, int x, int y) {
        this.x = x;
        this.y = y;
        g.drawImage(image, x, y, x + 1, y + 1, index * TILESIZE, 0, (index + 1) * TILESIZE, TILESIZE, null);
    }
    
    public static Piece getPiece(int idx)
    {
        if(!pieces.containsKey(idx))
        {
            pieces.put(idx, new Piece(idx));
        }
        return pieces.get(idx);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveTo(int xx, int yy) {
        x = xx;
        y = yy;
    }
    
    @Override
    public IPiece reverseDecoration() {
        return null;
    }
}
