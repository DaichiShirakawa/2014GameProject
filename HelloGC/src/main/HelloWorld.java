package main;
import java.awt.Container;

import javax.swing.JFrame;

public class HelloWorld extends JFrame {
	private static final long serialVersionUID = 1L;

	public HelloWorld() {
		setTitle("たいとaー");
		そしてこっちは違うブランチ

		// メインパネルを作成してフレームに追加
		MainPanel panel = new MainPanel();
		Container contentPane = getContentPane();
		contentPane.add(panel);
		setResizable(false);
		setIgnoreRepaint(true);
		
		// パネルサイズに合わせてフレームサイズを自動設定
		pack();
	}

	public static void main(String[] args) {
		HelloWorld frame = new HelloWorld();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
