import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class MainFrame extends JComponent implements MouseListener,
		MouseMotionListener {
	private int size = 60;
	private int x, y;
	int array[][] = new int[10][10];

	MainFrame() {
		y=550;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		boolean black = true;
		for (int b = 0; b < 10; b++)
			for (int a = 0; a < 11; a++) {
				if (black) {
					black = false;
					g2.setColor(Color.GRAY);
				} else {
					black = true;
					g2.setColor(Color.BLACK);
				}
				g2.fill(new Rectangle2D.Double(a * size, b * size, size, size));
			}
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Double(x, y, size - 15, size - 15,
				size - 15, size - 15));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int tX = (e.getX() / size) * size;
		int tY = (e.getY() / size) * size;
		if(tY>y)return;
		if ((tX / size) % 2 == 0 && (tY / size) % 2 == 0) {
			return;
		}
		if ((tX / size) % 2 != 0 && (tY / size) % 2 != 0) {
			return;
		}
		x = tX + 7;
		y = tY + 7;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				JFrame frame = new JFrame("Checkers");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(605, 650);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				JComponent c = new MainFrame();
				JMenuBar m = new JMenuBar();
				JMenu menu = new JMenu("A Menu");
				m.add(menu);
				JMenuItem menuItem = new JMenuItem("A text-only menu item",
						KeyEvent.VK_T);
				menu.add(menuItem);
				frame.setJMenuBar(m);
				frame.add(c);
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		int tX = (e.getX() / size) * size;
//		int tY = (e.getY() / size) * size;
//		if ((tX / size) % 2 == 0 && (tY / size) % 2 == 0) {
//			return;
//		}
//		if ((tX / size) % 2 != 0 && (tY / size) % 2 != 0) {
//			return;
//		}
//		x = tX + 7;
//		y = tY + 7;
//		repaint();

	}
}
