package com.modefin.monitorservice.Utils;

import java.net.InetSocketAddress;
import java.net.Socket;

public class PortUtil {
	// ✅ Check if a port is open
	public static boolean isAppRunning(String host, int port) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(host, port), 5000); // 5s timeout
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
