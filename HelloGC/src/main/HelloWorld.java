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
 * ここからはじまる
 *
 * @author tokiha_s
 */
public final class HelloWorld implements Runnable {
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
        // System.setProperty("org.lwjgl.librarypath",
        // getClass().getResource(WINDOWS_NATIVE_FOLDER_STRING).getPath());
        HelloWorld loop = new HelloWorld();
        try {
            loop.displayItialize();
            loop.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loop.terminate();
            Display.destroy();
        }
        /**
         * 謎のエラー防止用 ERROR: JDWP Unable to get JNI 1.2 environment, jvm->GetEnv()
         * return code = -2 JDWP exit error AGENT_ERROR_NO_JNI_ENV(183):
         * [../../../src/share/back/util.c:838]
         */
        System.exit(0);
    }

    /**
     * 隠蔽コンストラクタ
     */
    private HelloWorld() {
        //
    }

    /**
     * ロジック生成、ウィンドウ生成、openGLの初期化
     */
    private void displayItialize() {

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
        //                glClearColor(1f, 1f, 1f, 1f);
        //                glClearColor(0, 0, 0, 1);

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
