//20140201

package main;

import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import glogic.GLogic;

import java.io.IOException;
import java.text.DecimalFormat;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import texture.Texture;
import texture.TextureLoader;

public class HelloWorld implements Runnable {
	// FPS管理
	private static final int FPS = 60;
	private static final long PERIOD = (long) (1.0 / (long) FPS * 1000000000L);
	private static long MAX_STATS_INTERVAL = 1000000000L;
	private long calcInterval = 0L;
	private long prevCalcTime;
	private double actualFPS = 0.0;
	private DecimalFormat df = new DecimalFormat("0.0");
	private long beforeTime, afterTime, timeDiff, sleepTime;
	private long overSleepTime = 0L;

	// ゲーム管理
	private GLogic logic;

	public static void main(String[] args) {
		try {
			lwjglItialize();
			new HelloWorld().start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Display.destroy();
		}
	}

	public void start() {
		logic = new GLogic();
		run();
	}

	/**
	 * ウィンドウ生成、openGLの初期化
	 */
	private static void lwjglItialize() {
		// ウインドウを生成する
		try {
			Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
			Display.setTitle("hello world!");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		// OpenGL の初期設定
		// ポリゴンの片面（表 or 裏）表示を有効にする
		glEnable(GL_CULL_FACE);
		// ポリゴンの表示面を表（裏を表示しない）のみに設定する
		glCullFace(GL_BACK);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		// 視体積（描画する領域）を定義する
		glOrtho(0, WIDTH, 0, HEIGHT, 0, DEPTH);
		glMatrixMode(GL_MODELVIEW);
	}

	/**
	 * FPS計測(ゲーム処理前)
	 */
	private void fpsBefore() {
		beforeTime = System.nanoTime();
	}

	/**
	 * FPS計測(ゲーム処理後)
	 */
	private void fpsAfter() {
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
		} else {
			// 状態更新・レンダリングで時間を使い切ってしまい
			// 休止時間がとれない場合
			overSleepTime = 0L;
		}
	}

	/**
	 * FPS計算
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

	private Texture texture;

	public void run() {
		prevCalcTime = System.nanoTime();
		try {
			texture = new TextureLoader().loadTexture("media/image.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (!Display.isCloseRequested()) {
			fpsBefore();
			// オフスクリーンを初期化する
			glClear(GL_COLOR_BUFFER_BIT);
			// オフスクリーンに描画する
			upAndRender();
			// オフスクリーンをスクリーンに反映する
			Display.update();
			fpsAfter();
			calcFPS();
			Display.setTitle("FRAME:" + String.valueOf(frameCount) + "FPS:"
					+ df.format(actualFPS));
		}
	}

	private void upAndRender() {
        //  設定を初期化する
        glLoadIdentity();
		texture.bind();
		// 次に指定する４つの座標を、描く四角形の頂点として認識させる
		glBegin(GL_QUADS);

		// 色情報を設定する。アルファ値は 1f （非透過）。
		glColor4f(1f, 1f, 1f, 1f);

		glTexCoord2d(1f, 1f);
		glTexCoord2d(0f, 1f);
		glTexCoord2d(0f, 0f);
		glTexCoord2d(1f, 0f);
		
		glVertex3f(WIDTH - 50, HEIGHT - 50, 0); // 1 つめの座標を指定する
		glVertex3f(50, HEIGHT - 50, 0); // 2 つめの座標を指定する
		glVertex3f(50, 50, 0); // 3 つめの座標を指定する
		glVertex3f(WIDTH - 50, 50, 0); // 4 つめの座標を指定する

		glEnd();
	}
}
