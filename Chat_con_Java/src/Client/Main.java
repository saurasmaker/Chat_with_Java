package Client;

public class Main {

	public static void main(String[] args) {
		ClientThread clientThread = new ClientThread();
		ClientFrame clientFrame = new ClientFrame();
		clientThread.setClientFrame(clientFrame);
		clientThread.start();
		clientFrame.setVisible(true);
	}

}
