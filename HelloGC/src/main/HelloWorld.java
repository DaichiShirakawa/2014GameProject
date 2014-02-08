//20140201

package main;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import glogic.GLogic;

import java.text.DecimalFormat;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class HelloWorld implements Runnable {

	// ゲーム管理
	private GLogic logic;

	// FPS管理
	private static final long PERIOD = (long) (1.0 / (long) FPS * 1000000000L);
	private static long MAX_STATS_INTERVAL = 1000000000L;
	private long calcInterval = 0L;
	private long prevCalcTime;
	private double actualFPS = 0.0;
	private DecimalFormat df = new DecimalFormat("0.0");

	public static void main(String[] args) {
		HelloWorld loop = new HelloWorld();
		try {
			loop.DisplayItialize();
			loop.run();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			loop.terminate();
			Display.destroy();
		}
	}

	/**
	 * ロジック生成、ウィンドウ生成、openGLの初期化
	 */
	private void DisplayItialize() {

		// ウインドウを生成する
		try {
			Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
			Display.setTitle("hello world!");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		// OpenGL の初期設定効化する

		// でふぉると色
		// glClearColor(1f, 1f, 1f, 1f);
		glClearColor(0, 0, 0, 1);

		// テクスチャーを有効に
		glEnable(GL_TEXTURE_2D);

		// アルファブレンドを有効に
		glEnable(GL_BLEND);

		// ポリゴンの片面のみを表示
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		// 視体積（描画する領域）を定義する
		glOrtho(0, WIDTH, 0, HEIGHT, -DEPTH / 2, DEPTH / 2);
		glMatrixMode(GL_MODELVIEW);

		logic = new GLogic();
	}

	private void terminate() {
		if (logic != null)
			logic.terminate();
	}

	public void run() {
		while (!Display.isCloseRequested()) {
			// オフスクリーンを初期化する
			glClear(GL_COLOR_BUFFER_BIT);
			// メイン処理/オフスクリーンに描画
			logic.gameUpdate();
			logic.gameRender();
			// オフスクリーンをスクリーンに反映する
			Display.update();

			calcFPS();
			Display.setTitle("FRAME:" + String.valueOf(getFrameCount())
					+ "FPS:" + df.format(actualFPS));
			Display.sync(FPS);
		}
	}

	/**
	 * FPS計算
	 */
	private void calcFPS() {
		incFrameCount();
		calcInterval += PERIOD;

		// 1秒おきにFPSを再計算する
		if (calcInterval >= MAX_STATS_INTERVAL) {
			long timeNow = System.nanoTime();
			// 実際の経過時間を測定
			long realElapsedTime = timeNow - prevCalcTime; // 単位: ns

			// FPSを計算
			// realElapsedTimeの単位はnsなのでsに変換する
			actualFPS = ((double) getFrameCount() / realElapsedTime) * 1000000000L;

			resetFrameCount();
			calcInterval = 0L;
			prevCalcTime = timeNow;
		}
	}
}
