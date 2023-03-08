/* Initial beliefs and rules */

needs_health(dron1). // initial belief



/* Plans */

// If the drone needs health, it can go to pick it up
+!needs_health(drone1)
   : true
   <- !at(drone1,health);
      pick(drone1,health).

+!at(drone1,P) : at(drone1,P) <- true.
+!at(drone1,P) : not at(drone1,P)
  <- move_towards(P);
     !at(drone1,P).
	 

	 
	
+?time(T) : true
  <-  time.check(T).
