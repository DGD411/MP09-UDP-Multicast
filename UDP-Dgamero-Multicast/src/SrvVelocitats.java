

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.util.Random;

public class SrvVelocitats {
	private MulticastSocket socket;
	private InetAddress multicastIP;
	private int port;
	private boolean continueRunning = true;
	private Random random = new Random();
	private String[] paraules = {"hola", "adeu", "gat", "gos", "cotxe"};

	public SrvVelocitats(int portValue, String strIp) throws IOException {
		socket = new MulticastSocket(portValue);
		multicastIP = InetAddress.getByName(strIp);
		port = portValue;
	}

	public void runServer() throws IOException {
		while (continueRunning) {
			String paraula = paraules[random.nextInt(paraules.length)];
			byte[] sendingData = paraula.getBytes();
			DatagramPacket packet = new DatagramPacket(sendingData, sendingData.length, multicastIP, port);
			socket.send(packet);

			try {
				Thread.sleep(2000); // Envía una palabra cada 2 segundos
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		socket.close();
	}

	public static void main(String[] args) throws IOException {
		SrvVelocitats srvVelocitats = new SrvVelocitats(5557, "224.0.11.111");
		srvVelocitats.runServer();
		System.out.println("Parat!");
	}
}
