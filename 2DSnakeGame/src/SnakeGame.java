import javax.swing.JFrame;

/**
 * @author ChangJie Yang
 *
 */

public class SnakeGame {

	private JFrame jframe;
	
	public void init(){
		
		jframe = new JFrame("Snake Game");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(806,629);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.add(new SnakePanel());
		jframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		SnakeGame snakegame = new SnakeGame();
		snakegame.init();
	}

}
