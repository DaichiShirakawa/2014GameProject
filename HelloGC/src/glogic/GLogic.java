package glogic;

import static main.Commons.*;
import gobject.Ball;
import gobject.BallLauncher;
import gobject.Haikei;
import gobject.Rainy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class GLogic {
	// ゲーム用変数
	private Haikei haikei;
	private ArrayList<Ball> ball;
	private BallLauncher launcher;
	private Rainy rainy;

	public GLogic() {
		// 背景
		haikei = new Haikei(WIDTH, HEIGHT, "../mediaResources/background.png");
		// ボールを格納する配列を作成
		ball = new ArrayList<>();
		rainy = new Rainy();
	}

	public void gameUpdate() {
		int size = 20;
		Point point;
		if ((point = mouse.getPressPoint()) != null) {
			launcher = new BallLauncher(point);
		} else if (launcher != null
				&& (point = mouse.getReleasePoint()) != null) {
			 ball.add(launcher.launch(point, size));
			launcher = null;
		} else if (launcher != null) {
			launcher.update(mouse.getCursor());
		}

		// 各ボールを速度分だけ移動させる
		for (int i = 0; i < ball.size(); i++)
			ball.get(i).move();
		
		rainy.update();
	}

	public void gameRender(Graphics g) {
		// 背景
		haikei.draw(g);
		if(launcher != null) {
			launcher.draw(g);
		}
		// 各ボールを描画
		for (int i = 0; i < ball.size(); i++) {
			ball.get(i).draw(g);
		}
		
		rainy.draw(g);
		
		g.setColor(Color.blue);
		g.drawString(ball.size() + " balls", 10, 30);
	}

}
