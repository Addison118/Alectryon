package org.alectryon.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Handler extends Thread {

	private String port;
	private InetSocketAddress address;
	private InetSocketAddress remoteAddress;
	private InetAddress clientIp;
	private int clientPort;
	private String ip;
	private String remoteServerAddress;
	private String remotePort;
	boolean isRunning;

	public Handler(String port, String ip, String remotePort, String remoteServerAddress) {
		this.port = port;
		this.ip = ip;
		this.remotePort = remotePort;
		this.remoteServerAddress = remoteServerAddress;
	}
	
	public void Run() throws IOException {
		isRunning = true;
		this.handleNetwork();
	}
	
	public void handleNetwork() throws IOException {
		this.address = new InetSocketAddress(this.ip, Integer.parseInt(this.port));
		this.remoteAddress = new InetSocketAddress(this.remoteServerAddress, Integer.parseInt(this.port));
		DatagramSocket s = new DatagramSocket(this.address);
		DatagramSocket sock = new DatagramSocket();
		DatagramSocket clientSock = new DatagramSocket();
		byte[] recvBuf = new byte[1024 * 1024];
		sock.connect(this.remoteAddress);
		DatagramPacket dp = new DatagramPacket(recvBuf, recvBuf.length);
        DatagramPacket remotePacket = new DatagramPacket(recvBuf, recvBuf.length);
        DatagramPacket remoteRecieve = new DatagramPacket(recvBuf, recvBuf.length);
		while(isRunning) {
        s.receive(dp);
        this.clientIp = dp.getAddress();
        this.clientPort = dp.getPort();
        System.out.println(dp.getAddress());
		System.out.println("Connected to: " + sock.getRemoteSocketAddress());
		byte[] data = dp.getData();
		System.out.println("PID: " + data[0]);
		switch(data[0]) {
		case Protocol.UNCONNECTED_PING_OPEN_CONNECTIONS:
			this.handlePacket(sock, dp);
		}
		s.receive(remotePacket);
		}
		s.close();
	}
	
	public void handlePacket(DatagramSocket sock, DatagramPacket packet) throws IOException {
		packet.setAddress(InetAddress.getByName(this.remoteServerAddress));
		packet.setPort(Integer.parseInt(this.remotePort));
		sock.send(packet);
	}
}
