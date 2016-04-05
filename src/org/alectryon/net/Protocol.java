package org.alectryon.net;

public class Protocol {

	public static final long serverID = 925686942;
	
	public static final byte UNCONNECTED_PING_OPEN_CONNECTIONS = (byte) 0x01;
	public static final byte BATCH_PACKET = (byte) 0x92;
	public static final byte QUERY_PACKET = (byte) 0x02;
	public static final byte LOGIN_PACKET = (byte) 0x82;
	public static final byte LOGIN_STATUS_PACKET = (byte) 0x83;
	public static final byte READY_PACKET = (byte) 0x84;
	public static final byte MESSAGE_PACKET = (byte) 0x85;
	public static final byte SET_TIME_PACKET = (byte) 0x86;
	public static final byte START_GAME_PACKET = (byte) 0x87;
	public static final byte ADD_MOB_PACKET = (byte) 0x88;
	public static final byte ADD_PLAYER_PACKET = (byte) 0x89;
	public static final byte REMOVE_PLAYER_PACKET = (byte) 0x8a;
	public static final byte MOVE_PLAYER_PACKET = (byte) 0x94;
	public static final byte UPDATE_BLOCK_PACKET = (byte) 0x97;
	public static final byte PLACE_BLOCK_PACKET = (byte) 0x95;
	public static final byte REMOVE_BLOCK_PACKET = (byte) 0x96;
}
