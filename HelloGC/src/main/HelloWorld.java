//20140201

package main;

import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class HelloWorld {

	public static void main(String[] args) {
		new HelloWorld().start();
	}

	public void start() {
		try {
			// ウインドウを生成する
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("hello world!");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			return;
		}
		try {
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

			while (!Display.isCloseRequested()) {
				// オフスクリーンを初期化する
				glClear(GL_COLOR_BUFFER_BIT);

				// オフスクリーンに描画する
				render();

				// オフスクリーンをスクリーンに反映する
				Display.update();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Display.destroy();
		}
	}

	private void render() {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
