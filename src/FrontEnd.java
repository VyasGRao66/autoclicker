import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.*;
import java.lang.System;
import java.awt.AWTException;
import java.awt.Robot;
import java.lang.InterruptedException;

public class FrontEnd extends JFrame {

	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontEnd frame = new FrontEnd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontEnd() throws AWTException, InterruptedException {

		Robot bot = new Robot();
		setTitle("Auto CLicker");
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 345);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);

		JLabel Title = new JLabel("Auto Clicker");
		Title.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(207, 11, 140, 29);
		panel.add(Title);

		JLabel TimeLbl = new JLabel("Time in Minutes");
		TimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		TimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TimeLbl.setBounds(54, 58, 146, 38);
		panel.add(TimeLbl);

		JLabel ClicksPerSeclbl = new JLabel("Click/Sec");
		ClicksPerSeclbl.setHorizontalAlignment(SwingConstants.CENTER);
		ClicksPerSeclbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ClicksPerSeclbl.setBounds(379, 58, 86, 38);
		panel.add(ClicksPerSeclbl);

		JLabel Buttonlbl = new JLabel("Button");
		Buttonlbl.setHorizontalAlignment(SwingConstants.CENTER);
		Buttonlbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Buttonlbl.setBounds(207, 75, 124, 38);
		panel.add(Buttonlbl);

		String[] st = { "Right Click", "Middle Click", "Left Click" };

		JList list = new JList(st);
		list.setFont(new Font("Tahoma", Font.PLAIN, 12));
		list.setBounds(207, 120, 86, 59);
		panel.add(list);
		list.getSelectedValue();

		JSpinner time = new JSpinner();
		time.setFont(new Font("Tahoma", Font.PLAIN, 25));
		time.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		time.setBounds(54, 103, 118, 49);
		panel.add(time);

		JSpinner cps = new JSpinner();
		cps.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cps.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		cps.setBounds(379, 106, 118, 49);
		panel.add(cps);

		JButton btnNewButton = new JButton("GO");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(296, 120, 51, 59);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				long minutes = 60000 * (int) time.getValue(); // Timer
				long ctmwm = System.currentTimeMillis() + minutes; // Current Time Milliseconds with Minutes or how long
				long ctm = System.currentTimeMillis(); // Current Time Milliseconds
				long delay = 1000 / (int) cps.getValue(); // Time delay
				String str = new String();
				str = (String) list.getSelectedValue();
				if (str.equals("Right Click")) {
					while (ctm < ctmwm) {
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						int click = InputEvent.BUTTON1_DOWN_MASK;
						bot.mousePress(click);
						bot.mouseRelease(click);
						ctm = System.currentTimeMillis();
					}
				} else if (str.equals("Middle Click")) {
					while (ctm < ctmwm) {
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

						int click = InputEvent.BUTTON2_DOWN_MASK;
						bot.mousePress(click);
						bot.mouseRelease(click);
						ctm = System.currentTimeMillis();

					}
				} else if (str.equals("Left Click")) {

					while (ctm < ctmwm) {
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

						int click = InputEvent.BUTTON3_DOWN_MASK;
						bot.mousePress(click);
						bot.mouseRelease(click);
						ctm = System.currentTimeMillis();

					}

				}
			}
		});

	}
}
