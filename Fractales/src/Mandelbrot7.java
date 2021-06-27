/******************************************************************************
 *  Compilation:  javac Mandelbrot.java
 *  Execution:    java Mandelbrot xc yc size
 *  Dependencies: StdDraw.java
 *
 *  Plots the size-by-size region of the Mandelbrot set, centered on (xc, yc)
 *
 *  % java Mandelbrot -0.5 0 2
 *
 ******************************************************************************/
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
 
public class Mandelbrot7 extends JFrame {
 
    private final int MAX_ITER = 570;
    private final double ZOOM = 200;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;
 
    public Mandelbrot7() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = (x - 400) / ZOOM;
                cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                
                //(a + bi) ^ 7
                
                //Z = Z*Z*Z*Z*Z*Z*Z + C 
                
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx * zx * zx * zx * zx * zx - 21.0 * zx * zx * zx * zx * zx * zy * zy + 35.0 * zx * zx * zx * zy * zy * zy * zy - 7.0 * zx * zy * zy * zy * zy * zy * zy + cX;
                    zy = 7.0 * zx * zx * zx * zx * zx * zx * zy - 35.0 * zx * zx * zx * zx * zy * zy * zy + 21.0 * zx * zx * zy * zy * zy * zy * zy - zy * zy * zy * zy * zy * zy * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 20));
            }
        }
    }
 
    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
 
    // Main
    // public static void main(String[] args) {
    //     new Mandelbrot7().setVisible(true);
    //}
}