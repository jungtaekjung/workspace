package edu.kh.network.run;

import edu.kh.network.model.service.TCPClient;

public class TCPRun {

	public static void main(String[] args) {
		TCPClient tcp = new TCPClient();
		tcp.clientStart();
	}

}
