import jason.environment.grid.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


/** class that implements the View of Domestic Robot application */
public class MapView extends GridWorldView {

    MapModel mmodel;

    public MapView(MapModel model) {
        super(model, "Autonomous Flight Arcade", 700);
        mmodel = model;
        defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
        setVisible(true);
        repaint();
    }

    /** draw application objects */
    @Override
    public void draw(Graphics g, int x, int y, int object) {
        Location lDrone1 = mmodel.getAgPos(0);
        // Location lDrone2 = mmodel.getAgPos(1);

        super.drawAgent(g, x, y, Color.lightGray, -1);

        switch (object) {
            case MapModel.HEALTH:
                if (lDrone1.equals(mmodel.lHealth)) {
                    super.drawAgent(g, x, y, Color.gray, -1);
                }
                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "Health Package");
                break;
        
        }
        repaint();

    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        Location lDrone1 = mmodel.getAgPos(0);
        // Location lDrone2 = mmodel.getAgPos(1);

       
        c = Color.yellow;
        super.drawAgent(g, x, y, c, -1);
        g.setColor(Color.black);
        super.drawString(g, x, y, defaultFont, "Drone 1");
        
    }
}
