// Beliefs
drone1(lu) //drone1 is at position (x1,y1)
// drone2(x2,y2) //drone2 is at position (x2,y2)

/*
last_order_id(1). // initial belief
// plan to achieve the the goal "order" from agent Ag
+!order(Product,Qtd)[source(Ag)] : true
	<- ?last_order_id(N);
	OrderId = N + 1;
	-+last_order_id(OrderId);
	deliver(Product,Qtd);
	.send(Ag, tell, delivered(Product,Qtd,OrderId)).
	
*/
