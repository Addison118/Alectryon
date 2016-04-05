package org.alectryon.net.info;

	/**
	 * RakNet Network Channel (orderChannel)
	 *
	 * @author jython234
	 */
	public enum Channel {
	    CHANNEL_NONE(0),
	    CHANNEL_PRIORITY(1),
	    CHANNEL_WORLD_CHUNKS(2),
	    CHANNEL_MOVEMENT(3),
	    CHANNEL_BLOCKS(4),
	    CHANNEL_WORLD_EVENTS(5),
	    CHANNEL_ENTITY_SPAWNING(6),
	    CHANNEL_TEXT(7),
	    CHANNEL_END(31);

	    private byte channel;

	    private Channel(byte channel) {
	        this.channel = channel;
	    }

	    private Channel(int channel) {
	        this.channel = (byte) channel;
	    }

	    public byte getAsByte() {
	        return channel;
	    }
	}
