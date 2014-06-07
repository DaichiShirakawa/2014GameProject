package texture.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import texture.Texture;
import texture.TextureLoader;

/**
 * 受け取った文字列をテクスチャ化して返す
 * 
 * @author shirakawa
 * 
 */
public class TextTextureMaker {
	private TextTextureMaker() {
		// 隠蔽
	}

	public static Texture createText(String str) {
		return createText(str, Color.white);
	}

	public static Texture createText(String str, Color color) {
		return create(str, color, FontDef.DEFAULT);
	}

	public static Texture createText(String str, FontDef fontDef) {
		return create(str, Color.white, fontDef);
	}

	public static Texture create(String str, Color color, FontDef fontDef) {
		Font font = FontCollector.getFont(fontDef);
		String[] lines = str.split("\n");
		float width = 0;
		for (String line : lines) {
			// 縦横比が半角は2:1、全角は1:1の前提
			width = Math.max((fontDef.size / 2) * getByteLength(line), width);
		}
		float lineHeight = fontDef.size;
		return createText(lines, color, font, width, lineHeight);
	}

	private static float getByteLength(String str) {
		try {
			return str.getBytes("Shift-JIS").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return -1;
	}

	private static Texture createText(String[] lines, Color color, Font font,
			float width, float lineHeight) {
		BufferedImage image = null;
		Graphics2D g = null;
		try {
			image = TextureLoader.createImageData((int) width, (int) lineHeight
					* lines.length);

			g = image.createGraphics();
			g.setFont(font);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(color);

			int y = -4;
			for (String line : lines) {
				y += lineHeight;
				float x = width - ((lineHeight / 2) * getByteLength(line));
				x /= 2;
				g.drawString(line, x, y);
			}

			return TextureLoader.loadTexture(image);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (g != null) {
				g.dispose();
			}
			if (image != null) {
				image.flush();
			}
		}
		return null;
	}

	@Deprecated
	public static Texture createTextTexture(String str, int width, int height,
			Color color) {
		BufferedImage image = null;
		Graphics2D g = null;
		Font font = FontCollector.getFont(FontDef.MSGOTHIC_32);

		try {
			image = TextureLoader.createImageData(width, height);

			// 透明色で塗りつぶし、BufferedImage を初期化する
			g = image.createGraphics();
			g.setColor(new Color(0f, 0f, 0f, 0f));
			g.fillRect(0, 0, width, height);

			// 外部フォントを使う準備をする
			g.setFont(font);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(color);

			g.drawString(str, 0, height - 3);

			return TextureLoader.loadTexture(image);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (g != null) {
				g.dispose();
			}
			if (image != null) {
				image.flush();
			}
		}
		return null;
	}
}
