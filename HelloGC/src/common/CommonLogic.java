package common;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
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

	/**
	 * floatフォーマット用
	 */
	public static String formatDouble0d0(double val) {
		return FORMAT_FOR_FLOAT_0d0.format(val);
	}

	/**
	 * Color→LWJGL互換用
	 */
	public static void setGlColor4f(Color color, float alpha) {
		glColor4f(color.getRed() / 255f, color.getGreen() / 255f,
				color.getBlue() / 255f, alpha);
	}

	/**
	 * きれいなコスモスいろをつくる
	 */
	public static Color generateColorCosmos() {
		float rand = random(0f, 1.4f);
		float r = 1f;
		float g = (1f < rand) ? rand - 1f : 0f;
		float b = (rand <= 1f) ? rand : 0f;
		rand = random(0.3f, 1f);
		r += ((1f - r) * rand);
		g += ((1f - g) * rand);
		b += ((1f - b) * rand);
		// ランダムで、色を黒に近づける
		rand = random(0.95f, 1f);
		r *= rand;
		g *= rand;
		b *= rand;
		return new Color(r, g, b);
	}
}
