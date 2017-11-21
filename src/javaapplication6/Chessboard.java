package javaapplication6;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Chessboard extends JPanel {

    public static final int ZEROX = 23;
    static final int ZEROY = 7;

    private HashMap<Point, IPiece> board = new HashMap();
    
    private Image image;
    private IPiece dragged = null;
    private Point mouse = null;

    Chessboard() {
        AffineTransform drawTransform = new AffineTransform();
        drawTransform.translate(ZEROX, ZEROY);
        drawTransform.scale(Piece.TILESIZE, Piece.TILESIZE);
        board.put(new Point(0, 2), new TransformDec(Piece.getPiece(11), drawTransform));
        board.put(new Point(0, 6), new TransformDec(Piece.getPiece(0), drawTransform));
        board.put(new Point(1, 4), new TransformDec(Piece.getPiece(6), drawTransform));
        board.put(new Point(1, 5), new TransformDec(Piece.getPiece(5), drawTransform));
        board.put(new Point(3, 7), new TransformDec(Piece.getPiece(1), drawTransform));
        board.put(new Point(4, 3), new TransformDec(Piece.getPiece(6), drawTransform));
        board.put(new Point(4, 4), new TransformDec(Piece.getPiece(7), drawTransform));
        board.put(new Point(5, 4), new TransformDec(Piece.getPiece(6), drawTransform));
        board.put(new Point(5, 6), new TransformDec(Piece.getPiece(0), drawTransform));
        board.put(new Point(6, 5), new TransformDec(Piece.getPiece(0), drawTransform));
        board.put(new Point(7, 4), new TransformDec(Piece.getPiece(0), drawTransform));
        image = new ImageIcon("board3.png").getImage();
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        AffineTransform dragTransform = new AffineTransform();
              
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                //dragged = take((ev.getX() - ZEROX) / Piece.TILESIZE, (ev.getY() - ZEROY) / Piece.TILESIZE);
                dragged = new TransformDec(take((ev.getX() - ZEROX) / Piece.TILESIZE, (ev.getY() - ZEROY) / Piece.TILESIZE), dragTransform);
                mouse = ev.getPoint();
            }

            public void mouseReleased(MouseEvent ev) {
                drop(dragged.reverseDecoration(), (ev.getX() - ZEROX) / Piece.TILESIZE, (ev.getY() - ZEROY) / Piece.TILESIZE);
                dragged = null;
                undo.setEnabled(true);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent ev) {
                dragTransform.setToTranslation(ev.getX() - mouse.getX(), ev.getY() - mouse.getY());
                repaint();
            }
        });
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
        for (Map.Entry<Point, IPiece> e : board.entrySet()) {
            Point pt = e.getKey();
            IPiece pc = e.getValue();
            pc.draw((Graphics2D) g, pt.x, pt.y);
        }
        if (mouse != null && dragged != null) {
            dragged.draw((Graphics2D) g, dragged.getX(), dragged.getY());
        }
    }


    public void drop(IPiece p, int x, int y) {
        repaint();
        p.moveTo(x, y);
        board.put(new Point(x, y), p); // jeśli na tych współrzędnych
        //jest już jakaś figura, znika ona z planszy
        //(HashMap nie dopuszcza powtórzeń)
    }

    public IPiece take(int x, int y) {
        repaint();
        return board.remove(new Point(x, y));
    }

    
    class UndoButton implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            System.out.println("UNDO");
            redo.setEnabled(true);
        }
    }

    class RedoButton implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            System.out.println("REDO");
        }
    }

    private JButton undo, redo;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Chessboard board = new Chessboard();

        JToolBar bar = new JToolBar();
        board.undo = new JButton(new ImageIcon("undo.png"));
        board.redo = new JButton(new ImageIcon("redo.png"));
        board.undo.addActionListener(board.new UndoButton());
        board.redo.addActionListener(board.new RedoButton());
        board.undo.setEnabled(false);
        board.redo.setEnabled(false);
        bar.add(board.undo);
        bar.add(board.redo);

        frame.add(bar, BorderLayout.PAGE_START);
        frame.add(board);

        frame.pack();
        frame.setVisible(true);
    }
}
