package com.arialclient.event.implement;

import com.arialclient.event.Event;

public class KeyPressEvent extends Event{
	private final int key;
	
	public KeyPressEvent(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
}
