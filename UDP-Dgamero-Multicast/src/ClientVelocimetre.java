

import java.io.IOException;
import java.net.*;

public class ClientVelocimetre {
    private MulticastSocket socket;
    private InetAddress multicastIP;
    private int port;
    private NetworkInterface netIf;
    private InetSocketAddress group;

    public ClientVelocimetre(int portValue, String strIp) throws IOException {
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        socket = new MulticastSocket(port);
        netIf = socket.getNetworkInterface();
        group = new InetSocketAddress(strIp, portValue);
    }

    public void runClient() throws IOException {
        DatagramPacket packet;
        byte[] receivedData = new byte[1024];

        socket.joinGroup(group, netIf);
        System.out.printf("Connectat a %s:%d%n", group.getAddress(), group.getPort());

        while (true) {
            packet = new DatagramPacket(receivedData, receivedData.length);
            socket.receive(packet);
            String receivedMessage = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Paraules rebudes: " + receivedMessage);
        }
    }

    public static void main(String[] args) throws IOException {
        ClientVelocimetre clientVelocimetre = new ClientVelocimetre(5557, "224.0.11.111");
        clientVelocimetre.runClient();
        System.out.println("Parat!");
    }
}
