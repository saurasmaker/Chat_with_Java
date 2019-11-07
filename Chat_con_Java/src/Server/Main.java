package Server;

public class Main {

	public static void main(String[] args) {
		MainThread mainThread = new MainThread();
		ServerFrame serverFrame = new ServerFrame();
		mainThread.setServerFrame(serverFrame);
		mainThread.start();
		serverFrame.setVisible(true);
	}

}
