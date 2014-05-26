package main;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import glogic.GLogic;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * 転職活動作品用
 *
 * @author tokiha_s
 * @since 2014/05/26
 */
public final class Main implements Runnable {
    // ゲーム管理
    private GLogic logic;

    // FPS管理
    private static final long PERIOD = (long) (1.0 / (long) FPS * 1_000_000_000L);
    private static final long MAX_STATS_INTERVAL = 1_000_000_000L;
    private long calcInterval = 0L;
    private long prevCalcTime_ns;
    private double actualFPS = 0.0;

    public static void main(final String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File(
                WINDOWS_NATIVE_FOLDER_STRING).getAbsolutePath());
        Main gameLoop = new Main();
        try {
            gameLoop.displayItialize();
            gameLoop.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            gameLoop.terminate();
            Display.destroy();
        }
        System.exit(0);
    }

    /**
     * 隠蔽コンストラクタ
     */
    private Main() {
        //
    }

    /**
     * ロジック生成、ウィンドウ生成、openGLの初期化
     */
    private void displayItialize() {
        createDisplay();
        initializeGLSettings();

        logic = new GLogic();
    }

	private void initializeGLSettings() {
		glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, 0, HEIGHT, -DEPTH / 2, DEPTH / 2);
        glMatrixMode(GL_MODELVIEW);
	}

	private void createDisplay() {
		try {
            Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
            Display.setTitle(PRODUCT_TITLE);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
	}

    private void terminate() {
        if (logic != null) {
            logic.dispose();
        }
    }

    public void run() {
        while (!Display.isCloseRequested()) {
            // ディスプレイがアクティブのときだけ処理を行う
            // if (Display.isActive()) {
            if (true) {
                // オフスクリーンを初期化する
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                // IO状態更新
                KEYBOARD.update();
                // メイン処理/オフスクリーンに描画
                logic.update();
                logic.render();
                Display.setTitle("FRAME:" + String.valueOf(getFrameCount())
                        + "FPS:" + formatDouble0d0(actualFPS));

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
        calcInterval += PERIOD;

        // 1秒おきにFPSを再計算する
        if (calcInterval >= MAX_STATS_INTERVAL) {
            long now_ns = System.nanoTime();
            long realElapsedTime_ns = now_ns - prevCalcTime_ns;

            // FPSを計算
            actualFPS = ((double) getFrameCount() / realElapsedTime_ns) * 1_000_000_000L;

            resetFrameCount();
            calcInterval = 0L;
            prevCalcTime_ns = now_ns;
        }
    }
}
