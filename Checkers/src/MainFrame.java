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
	private boolean picked = false;
	private int x, y;
	private int px, py;
	Figure map[][] = new Figure[8][8];

	MainFrame() {
		for (int b = 0; b < 8; b += 2) {
			map[b + 1][0] = new Figure("black");
			map[b][1] = new Figure("black");
			map[b + 1][2] = new Figure("black");
		}
		for (int b = 7; b > 0; b -= 2) {
			map[b - 1][5] = new Figure("white");
			map[b][6] = new Figure("white");
			map[b - 1][7] = new Figure("white");
		}
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	class Figure {
		private String color;

		Figure(String color) {
			this.color = color;
		}

		String getColor() {
			return color;
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		boolean black = true;
		for (int b = 0; b < 8; b++)
			for (int a = 0; a < 9; a++) {
				if (black) {
					black = false;
					g2.setColor(Color.GRAY);
				} else {
					black = true;
					g2.setColor(Color.BLACK);
				}
				g2.fill(new Rectangle2D.Double(a * size, b * size, size, size));
			}
		for (int a = 0; a < map.length; a++)
			for (int b = 0; b < map[0].length; b++) {
				if (map[a][b] != null) {
					if (map[a][b].getColor().equals("black"))
						g2.setColor(Color.LIGHT_GRAY);
					else
						g2.setColor(Color.WHITE);
					g2.fill(new RoundRectangle2D.Double(a * 60 + 7, b * 60 + 7,
							size - 15, size - 15, size - 15, size - 15));
				}
			}
		if (picked) {
			g2.setColor(new Color(96, 96, 224));
			g2.fill(new RoundRectangle2D.Double(px * size + 7, py * size + 7,
					size - 15, size - 15, size - 15, size - 15));
		}
	}

	int distance() {
		double a = Math.pow((px - x), 2);
		double b = Math.pow((py - y), 2);
		if (a > b)
			return (int) Math.sqrt(a);
		else
			return (int) Math.sqrt(b);
	}

	boolean onDiagonal() {
		// duct tape
		int distance = distance();
		if (px + distance == x && py + distance == y)
			return true;
		if (px - distance == x && py - distance == y)
			return true;
		if (px + distance == x && py - distance == y)
			return true;
		if (px - distance == x && py + distance == y)
			return true;
		return false;
	}

	boolean isAdversary() {
//		int distance=distance();
//		if(px-distance)
		return false;
	}

	public void mouseClicked(MouseEvent e) {
		int tX = e.getX() / size;
		int tY = e.getY() / size;
		if (picked) {
			// if (distance() > 1)
			// return;
			if (!onDiagonal())
				return;
			if (map[tX][tY] == map[x][y]) {
				picked = false;
				repaint();
				return;
			}
			if (map[px][py] != null)
				return;
			map[px][py] = map[x][y];
			map[x][y] = null;
			picked = false;
			repaint();
			return;
		}
		if (map[tX][tY] != null) {
			System.out.println(tX + " " + tY);
			x = tX;
			y = tY;
			picked = true;
		}
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
				frame.setSize(485, 530);
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
		if (!picked)
			return;
		int tX = e.getX() / size;
		int tY = e.getY() / size;
		px = tX;
		py = tY;
		if (!onDiagonal())
			return;
		repaint();
	}
}
