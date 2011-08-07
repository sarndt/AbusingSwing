package net.abusingjava.swing.demo;

import javax.swing.*;

public class Main {

	public static void main(String... $args) {
		JFrame $frame = new JFrame();
		JPanel $panel = new JPanel();
		$frame.setContentPane($panel);
		$panel.add(new JLabel("AbusingSwing from AbusingJava.net (1.0-SNAPSHOT)"));
		$frame.setSize(500, 150);
		$frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		$frame.setVisible(true);
	}


}
