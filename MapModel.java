import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of Domestic Robot application */
public class MapModel extends GridWorldModel {

    
    // constants for the grid objects
    public static final int HEALTH = 16;

    // the grid size
    public static final int GSize = 7;

    boolean healthPicked   = false; // whether the health package is picked up

    Location lHealth  = new Location(GSize/2,GSize/2);


    Location lLeftUp = new Location(0,0); // left top corner 
    Location lRightUp = new Location(GSize-1,0); // right top corner
    Location lLeftDown = new Location(0,GSize-1); // left bottom corner 
    Location lRightDown = new Location(GSize-1,GSize-1); // right bottom corner 

   

    



   
    public MapModel() {
        // create a 7x7 grid with one mobile agent
        super(GSize, GSize, 1);

        // ag code 0 means the drone1
        setAgPos(0, 0, 0);

        // adding location to the health package
        add(HEALTH,lHealth);
                
        
    }


    boolean pickHealth(){
        if (!healthPicked){
            healthPicked = true;
            return true;
        }else{
            return false;
        }
    }

    

    boolean moveTowards(Location dest) {

        Location d1 = getAgPos(0);
        if (d1.x < dest.x)        d1.x++;
        else if (d1.x > dest.x)   d1.x--;
        if (d1.y < dest.y)        d1.y++;
        else if (d1.y > dest.y)   d1.y--;

        setAgPos(0, d1); // move the robot in the grid

        
        return true;
    }


   

    
}
