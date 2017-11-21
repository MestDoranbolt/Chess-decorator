/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author T430s
 */
class TransformDec extends Decorator {

    private AffineTransform transform;

    public TransformDec(IPiece p, AffineTransform a) {
        super(p);
        this.transform = a;
    }
    
    @Override
    public void draw(Graphics2D g, int x, int y) {
        AffineTransform oldTransform = g.getTransform();
        g.transform(transform);
        this.piece.draw(g, x, y);
        g.setTransform(oldTransform);
    }
}
