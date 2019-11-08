package Client;

public class Main {

	public static void main(String[] args) {
		ClientThread clientThread = new ClientThread();
		ClientFrame clientFrame = new ClientFrame();
		clientThread.setClientFrame(clientFrame);
		clientFrame.setTitle(clientFrame.getTitle() + ": " + clientThread.getUserName());
		clientThread.start();
		clientFrame.setVisible(true);
	}

}
