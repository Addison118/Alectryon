package org.alectryon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import org.alectryon.net.Handler;
import org.alectryon.net.Protocol;
import org.alectryon.Versions;
import org.alectryon.utils.ConsoleColorTranslation;

public class Alectryon {
	
	public Logger logger = Logger.getLogger("Alectryon");
	
	private final ConsoleColorTranslation color = new ConsoleColorTranslation();
	
	File config = new File("server.properties");
	
	private boolean isOn = false;
	
	SocketAddress remoteAddr;
	
	private Alectryon server;
	
	private boolean debugging = false;
	
	private PrintStream fileStream;
	private PrintStream orgStream;
	public long serverID;
	
	public String ip;
	public String port;
	private String motd;
	
	public DatagramSocket sock;
	public InetAddress clientIp;
	public int clientPort;

	private String debug;

	private String remoteServerIp;

	private String remoteServerPort;
	
	public static void main(String[] args) throws IOException {
		new Alectryon().start(args);
	}

	public void start(String[] args) throws IOException {
		logger.info(ConsoleColorTranslation.ANSI_YELLOW + "Loading properties..." + ConsoleColorTranslation.ANSI_RESET);
		
		Properties prop = new Properties();
		OutputStream output = null;

			output = new FileOutputStream("server.properties");

			prop.setProperty("proxy-ip", "0.0.0.0");
			prop.setProperty("proxy-port", "19132");
			prop.setProperty("remote-server-ip", "sg1.lbsg.net");
			prop.setProperty("remote-server-port", "19132");
			prop.setProperty("motd", "A MC:PE Proxy");
			prop.setProperty("debug", "True");
			prop.store(output, null);
			
			FileInputStream input = new FileInputStream("server.properties");
			prop.load(input);
			
			this.ip = prop.getProperty("proxy-ip");
			this.port = prop.getProperty("proxy-port");
			this.remoteServerIp = prop.getProperty("remote-server-ip");
			this.remoteServerPort = prop.getProperty("remote-server-port");
			this.motd = prop.getProperty("motd");
			this.debug = prop.getProperty("debug");
			
		logger.info("Starting MC:PE Proxy version " + ConsoleColorTranslation.ANSI_BLUE + Versions.MCPE_VERSION + ConsoleColorTranslation.ANSI_RESET);
		logger.info("This server is running Alectryon version " + ConsoleColorTranslation.ANSI_CYAN + Versions.PROXY_VERSION + ConsoleColorTranslation.ANSI_RESET + " " + Versions.CODENAME);
		logger.info("Alectryon is licensed under the MIT license");
		
		this.isOn = true;
		
		this.startNetworking();
		
		logger.info("Done...");
	}		
	
	public void shutdown() throws InterruptedException {
		logger.info("Shutting down all threads");
		Thread.sleep(5000);
		this.isOn = false;
		System.exit(0);
	}
	
	public void startNetworking() {
		logger.info(ConsoleColorTranslation.ANSI_GREEN + "Starting packet handler...");
		new Handler(this.ip, this.port, this.remoteServerPort, this.remoteServerIp).start();
	}
	
	public void sendClient(DatagramPacket packet, DatagramSocket cliSock, DatagramSocket socket) throws IOException {
		socket.receive(packet);
		packet.setAddress(this.clientIp);
		packet.setPort(this.clientPort);
		cliSock.send(packet);
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public String getMotd() {
		return this.motd;
	}
	
	public String getPort() {
		return this.port;
	}
	
	public String getDebugging() {
		return this.debug;
	}
}
