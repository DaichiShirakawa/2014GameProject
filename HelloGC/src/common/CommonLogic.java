package common;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import texture.Texture;

public class CommonLogic {

	/**
	 * min から max までの間で乱数を生成して返す
	 */
	public static float random(float min, float max) {

		float dist = max - min;
		return (float) (Math.random() * dist + min);
	}

	/**
	 * Font生成
	 */
	public static Font createFont(String filepath) {
		try {
			InputStream is = new FileInputStream(filepath);
			Font font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(
					(float) FONT_HEIGHT);
			is.close();
			return font;
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * テクスチャつきポリゴンの描写
	 */
	public static void drawTexture(Texture texture, int width, int height) {
		texture.bind();

		// 四角のポリゴンとする
		glBegin(GL_QUADS);

		texture.point(texture.getWidth(), 0);
		glVertex3f(width / 2, height / 2, 0);
		texture.point(0, 0);
		glVertex3f(-width / 2, height / 2, 0);
		texture.point(0, texture.getHeight());
		glVertex3f(-width / 2, -height / 2, 0);
		texture.point(texture.getWidth(), texture.getHeight());
		glVertex3f(width / 2, -height / 2, 0);

		glEnd();
	}
}
