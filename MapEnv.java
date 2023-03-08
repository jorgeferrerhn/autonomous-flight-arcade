import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.Location;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class MapEnv extends Environment {

    // common literals

    // Moving drone 1
	public static final Literal lu = Literal.parseLiteral("at(drone1,leftup)"); 
	public static final Literal ld = Literal.parseLiteral("at(drone1,leftdown)"); 
	public static final Literal ru = Literal.parseLiteral("at(drone1,rightup)"); 
	public static final Literal rd = Literal.parseLiteral("at(drone1,rightdown)"); 

	public static final Literal ph = Literal.parseLiteral("pick(drone1,health)"); 
	public static final Literal nh = Literal.parseLiteral("needs_health(drone1)"); 


    public class PortListener extends Thread{
	
        @Override
        public void run(){
            try {
                DatagramSocket mySocket = new DatagramSocket(11000);
                byte[] buffer = new byte[1024];
                while ( true ) {
                    DatagramPacket peticion = new DatagramPacket(buffer,buffer.length);
                    mySocket.receive(peticion);
                    System.out.println("IP :"+peticion.getAddress());
                    System.out.println("Port :"+peticion.getPort());
                    
                    // message cast to string
                    System.out.println("Mensaje :"+new String(peticion.getData(),0,peticion.getLength()));
    
                    
                }
                
            } catch(SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        
        }
    }
   
	
    static Logger logger = Logger.getLogger(MapEnv.class.getName());

    MapModel model; // the model of the grid

    @Override
    public void init(String[] args) {
		
		// Initiate socket 
		
		PortListener listener = new PortListener();
		listener.start();

		
		// Initiate model 
        model = new MapModel();

        if (args.length == 1 && args[0].equals("gui")) {
            MapView view  = new MapView(model);
            model.setView(view);
        }

        updatePercepts();

        // after updating percepts, we check them
        System.out.print(consultPercepts("drone1"));
    }

    /** creates the agents percepts based on the MapModel */
    void updatePercepts() {
        // clear the percepts of the agents
        clearPercepts("drone1");

        Location lDrone1 = model.getAgPos(0);
		
        // initially, the drone will move in circles clockwise

        // add agent location to its percepts
        System.out.println("Current position "+lDrone1);

        if (lDrone1.equals(model.lLeftUp)) {
            addPercept("drone1", lu);
        }

        if (lDrone1.equals(model.lRightUp)) {
            addPercept("drone1", ru);
        }

        if (lDrone1.equals(model.lRightDown)) {
            addPercept("drone1", rd);
        }

        if (lDrone1.equals(model.lLeftDown)) {
            addPercept("drone1", ld);
        }

        // Finally, the drone1 is going to get the health package
        addPercept("drone1",nh);

        
        

        
    }


    @Override
    public boolean executeAction(String ag, Structure action) {
        System.out.println("["+ag+"] doing: "+action);
        boolean result = false;
        Location dest = null;

        // Spaghetti code 


        if (action.getFunctor().equals("move_towards")) {
            System.out.println("is going to move towards");

            String l = action.getTerm(0).toString();
            
           
            if (l.equals("rightup")) { // top right corner --> bottom right corner
                dest = model.lRightDown;
            } else if (l.equals("rightdown")) { // bottom right corner --> bottom left corner
                dest = model.lLeftDown;
            } else if (l.equals("leftdown")) { // bottom left corner --> top left corner
                dest = model.lLeftUp;
            } else if (l.equals("leftUp")) { // top left corner --> top right corner
                dest = model.lRightUp;
            } else if (l.equals("health")) { // go to pick up the health package
                dest = model.lHealth;
            }

            try {
                result = model.moveTowards(dest);
                System.out.print("Finally: "+result);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            logger.info("Failed to execute action "+action);
        }
          
        

        if (result) {

            updatePercepts();
            System.out.print(ag);
            try {
                Thread.sleep(100);
            } catch (Exception e) {}
        }
        return result;
    }
}
