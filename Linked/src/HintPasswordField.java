import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPasswordField;

public class HintPasswordField extends JPasswordField implements FocusListener {

	private static final long serialVersionUID = 1L;
	private final String hint;
	private boolean showingHint;

	HintPasswordField(final String hint) {
		
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		this.setEchoChar((char) 0); 
		super.addFocusListener(this);
	}

	public void focusGained(FocusEvent e) {
		
		if (this.getPassword().length == 0) {
			super.setText("");
			showingHint = false;
			this.setEchoChar('•');
		}
	}

	public void focusLost(FocusEvent e) {
		
		if (this.getPassword().length == 0) {
			super.setText(hint);
			showingHint = true;
			this.setEchoChar((char) 0);
		}
	}

	public char[] getPassword() {
		return showingHint ? new char[0] : super.getPassword();
	}
}