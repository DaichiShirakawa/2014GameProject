package main;

import static main.Commons.*;
import glogic.GLogic;
import io.Keyboard;
import io.Mouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable {
	// パネル
	private static final long serialVersionUID = 2L;
	private static final int PANEL_WIDTH = 640;
	private static final int PANEL_HEIGHT = 480;

	// IO
	private Mouse mouse;
	private Keyboard keyboard;

	// バッファリング
	private Graphics dbg;
	private Image dbImage = null;

	// ゲームループ
	private Thread gameLoop;
	private volatile boolean running = false;

	// FPS管理
	private static final int FPS = 60;
	private static final long PERIOD = (long) (1.0 / FPS * 1000000000);
	private static long MAX_STATS_INTERVAL = 1000000000L;
	private long calcInterval = 0L;
	private long prevCalcTime;
	private double actualFPS = 0.0;
	private DecimalFormat df = new DecimalFormat("0.0");

	// ゲーム管理
	private GLogic logic;

	public MainPanel() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		// IO
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		setFocusable(true);
		keyboard = new Keyboard();
		addKeyListener(keyboard);

		Commons.initializeCommons(this, mouse, keyboard);
		logic = new GLogic();

		gameLoop = new Thread(this);
		gameLoop.start();
	}

	public void run() {
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		int noDelays = 0;

		beforeTime = System.nanoTime();
		prevCalcTime = beforeTime;

		running = true;
		while (running) {
			logic.gameUpdate();
			bufferRefresh();
			paintScreen();

			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (PERIOD - timeDiff) - overSleepTime;

			if (sleepTime > 0) {
				try {
					// nano->mili secs
					Thread.sleep(sleepTime / 1000000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
				if (overSleepTime < 0)
					noDelays = 0;
			} else {
				// 状態更新・レンダリングで時間を使い切ってしまい
				// 休止時間がとれない場合
				overSleepTime = 0L;
				// 休止なしが16回以上続いたら
				if (++noDelays >= 16) {
					Thread.yield(); // 他のスレッドを強制実行
					noDelays = 0;
				}
			}
			beforeTime = System.nanoTime();
			calcFPS();
		}
	}

	private void bufferRefresh() {
		// 初回の呼び出し時にダブルバッファリング用オブジェクトを作成
		if (dbImage == null) {
			// バッファイメージ
			dbImage = createImage(PANEL_WIDTH, PANEL_HEIGHT);
			if (dbImage == null) {
				return;
			} else {
				// バッファイメージの描画オブジェクト
				dbg = dbImage.getGraphics();
			}
		}

		// バッファをクリアする
		dbg.setColor(Color.WHITE);
		dbg.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		logic.gameRender(dbg);
		dbg.drawString(df.format(actualFPS) + " FPS", 10, 20);
	}

	private void paintScreen() {
		try {
			Graphics g = getGraphics(); // グラフィックオブジェクトを取得
			if ((g != null) && (dbImage != null)) {
				g.drawImage(dbImage, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0, 0,
						Commons.WIDTH, Commons.HEIGHT, null);
			}

			Toolkit.getDefaultToolkit().sync();
			if (g != null) {
				g.dispose(); // グラフィックオブジェクトを破棄
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * FPSの計算
	 */
	private void calcFPS() {
		frameCount++;
		calcInterval += PERIOD;

		// 1秒おきにFPSを再計算する
		if (calcInterval >= MAX_STATS_INTERVAL) {
			long timeNow = System.nanoTime();
			// 実際の経過時間を測定
			long realElapsedTime = timeNow - prevCalcTime; // 単位: ns

			// FPSを計算
			// realElapsedTimeの単位はnsなのでsに変換する
			actualFPS = ((double) frameCount / realElapsedTime) * 1000000000L;

			frameCount = 0L;
			calcInterval = 0L;
			prevCalcTime = timeNow;
		}
	}

	/*
	 * フォントの生成
	 */
	@SuppressWarnings("unused")
	private Font createMyFont(String filename, float size) {
		Font font = null;
		InputStream is = null;

		try {
			is = getClass().getResourceAsStream(filename);
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		return font.deriveFont(size);
	}

}
