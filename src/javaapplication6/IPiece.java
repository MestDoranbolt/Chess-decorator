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
public interface IPiece {

    void draw(Graphics2D g);

    int getX();

    int getY();

    void moveTo(int x, int y);

    IPiece reverseDecoration();
}
