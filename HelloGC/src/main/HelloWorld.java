//20140201

package main;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;

import glogic.GLogic;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class HelloWorld implements Runnable {

	// ゲーム管理
	private GLogic logic;

	// FPS管理
	private final static long PERIOD = (long) (1.0 / (long) FPS * 1000000000L);
	private final static long MAX_STATS_INTERVAL = 1000000000L;
	private long calcInterval_ = 0L;
	private long prevCalcTime_;
	private double actualFPS_ = 0.0;

	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File(WINDOWS_NATIVE_FOLDER_STRING).getAbsolutePath());
//		System.setProperty("org.lwjgl.librarypath", getClass().getResource(WINDOWS_NATIVE_FOLDER_STRING).getPath());
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
		/**
		 * 謎のエラー防止用
		 * ERROR: JDWP Unable to get JNI 1.2 environment, jvm->GetEnv() return
		 * code = -2
		 * JDWP exit error AGENT_ERROR_NO_JNI_ENV(183):
		 * [../../../src/share/back/util.c:838]
		 */
		 System.exit(0);
	}

	/**
	 * ロジック生成、ウィンドウ生成、openGLの初期化
	 */
	private void DisplayItialize() {

		// ウインドウを生成する
		try {
			Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
			Display.setTitle("hello world!");
			// // マルチサンプリングのバッファ数を 16 に指定して、ウインドウを生成する
			// Display.create(new PixelFormat(0, 8, 0, 16));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		// デフォルト背景色
		// glClearColor(1f, 1f, 1f, 1f);
		glClearColor(0, 0, 0, 1);

		// テクスチャーを有効に
		glEnable(GL_TEXTURE_2D);

		// アルファブレンドを有効に
		glEnable(GL_BLEND);

		// ポリゴンの片面のみを表示
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);

		// カメラ用の設定変更を宣言する
		glMatrixMode(GL_PROJECTION);
		// 設定を初期化する
		glLoadIdentity();
		// 視体積（描画する領域）を定義する
		glOrtho(0, WIDTH, 0, HEIGHT, -DEPTH / 2, DEPTH / 2);

		// 物体モデル用の設定変更を宣言する
		glMatrixMode(GL_MODELVIEW);

		// //深度有効?
		// glEnable(GL_DEPTH_TEST);
		//
		// if ((1 <= glGetInteger(GL_SAMPLE_BUFFERS)) && (2 <=
		// glGetInteger(GL_SAMPLES))) {
		// // マルチサンプリングを有効にする
		// glEnable(GL_MULTISAMPLE);
		// // Alpha to coverage を有効にする
		// glEnable(GL_SAMPLE_ALPHA_TO_COVERAGE);
		// } else {
		// // アルファテストを有効にする
		// glEnable(GL_ALPHA_TEST);
		// // アルファテストの条件を設定する
		// glAlphaFunc(GL_GREATER, 0.7f);
		// }

		logic = new GLogic();
	}

	private void terminate() {
		if (logic != null)
			logic.dispose();
	}

	public void run() {
		while (!Display.isCloseRequested()) {
			// ディスプレイがアクティブのときだけ処理を行う
			// if (Display.isActive()) {
			if (true) {
				// オフスクリーンを初期化する
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				// IO状態更新
				keyboard.update();
				// メイン処理/オフスクリーンに描画
				logic.update();
				logic.render();
				Display.setTitle("FRAME:" + String.valueOf(getFrameCount())
						+ "FPS:" + formatDouble0d0(actualFPS_));

				calcFPS();
			}
			// オフスクリーンをスクリーンに反映する
			Display.update();
			Display.sync(FPS);
		}
	}

	/**
	 * FPS計算
	 */
	private void calcFPS() {
		incFrameCount();
		calcInterval_ += PERIOD;

		// 1秒おきにFPSを再計算する
		if (calcInterval_ >= MAX_STATS_INTERVAL) {
			long timeNow = System.nanoTime();
			// 実際の経過時間を測定
			long realElapsedTime = timeNow - prevCalcTime_; // 単位: ns

			// FPSを計算
			// realElapsedTimeの単位はnsなのでsに変換する
			actualFPS_ = ((double) getFrameCount() / realElapsedTime) * 1000000000L;

			resetFrameCount();
			calcInterval_ = 0L;
			prevCalcTime_ = timeNow;
		}
	}
}
