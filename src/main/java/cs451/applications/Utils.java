package cs451.applications;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.Date;

public class Utils {

    public static String addMessageHeader(String messageHeader, String message){
        return messageHeader + "/" + message;
    }

    public static String createMessageHeader(String processId, String UUID){
        return processId + "#" + UUID;
    }

    public static String createUUID(String sequenceId, String nonce) {
        return sequenceId + "@" + nonce;
    }

    public static String getMessageHeader(String rawMessage){
        String[] arr = rawMessage.split("/", 2);
        return arr[0];
    }

    public static String getProcessId(String messageHeader){
        String[] arr = messageHeader.split("#", 2);
        return arr[0];
    }

    public static String getUUID(String messageHeader){
        String[] arr = messageHeader.split("#", 2);
        return arr[1];
    }

    public static String getSequenceId(String UUID){
        String[] arr = UUID.split("@", 2);
        return arr[0];
    }

    public static String getNonce(String UUID){
        String[] arr = UUID.split("@", 3);
        return arr[1];
    }

    public static String getMessage(String rawMessage){
        String[] arr = rawMessage.split("/", 2);
        return arr[1];
    }

    /** code based on https://www.baeldung.com/udp-in-java **/
    public static void sendUdpMessage(String message, InetAddress destIp, int destPort) throws IOException {

        byte [] buf = message.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, destIp, destPort);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);

        socket.close();
    }
}
