package cliente;

import javax.swing.SwingUtilities;
import gui.ClienteView;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ClienteView().setVisible(true));
	}
}