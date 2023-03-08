import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDP_server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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

