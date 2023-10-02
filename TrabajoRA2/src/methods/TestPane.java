package methods;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TestPane extends JPanel {

    public TestPane() {
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        int size = Math.min(getWidth(), getHeight()) / 2;
        
        RoundRectangle2D border = new RoundRectangle2D.Double(0, 0, 210, 200, 20, 20);
        ImageUtilities.applyQualityRenderingHints(g2d);
        g2d.drawImage(ImageUtilities.applyShadow(border, 2, getBackground(), Color.BLACK, 0.25f), 5, 5, this);
        g2d.setColor(Color.DARK_GRAY);
        g2d.translate(5, 5);
        g2d.draw(border);
        g2d.dispose();
    }

}