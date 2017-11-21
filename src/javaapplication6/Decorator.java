/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.Graphics2D;

/**
 *
 * @author T430s
 */
public abstract class Decorator implements IPiece {

    protected IPiece piece;

    protected Decorator(IPiece p) {
        this.piece = p;
    }

    @Override
    public void draw(Graphics2D g, int x, int y) {
        this.piece.draw(g, x, y);
    }

    @Override
    public int getX() {
        return this.piece.getX();
    }

    @Override
    public int getY() {
        return this.piece.getY();
    }

    @Override
    public void moveTo(int xx, int yy) {
        this.piece.moveTo(xx, yy);
    }

    @Override
    public IPiece reverseDecoration() {
        return this.piece;
    }
}