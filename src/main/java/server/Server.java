package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Spark.port(getSparkAssignedPort());
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

	static int getSparkAssignedPort() {
//		ProcessBuilder processBuilder = new ProcessBuilder();
//		if (processBuilder.environment().get("9003") != null) {
//			return Integer.parseInt(processBuilder.environment().get("9003"));
//		}
		return 9003; //return default port if saprk-port isn't set (i.e. on localhost)
	}

}