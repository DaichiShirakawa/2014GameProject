package texture;

import static main.Commons.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TextTexture {
	private static Font font = null;
	private static final String FONT_FILEPATH = RESOURCE_FOLDER_STRING
			+ "Ricty.ttf";
	private static final int TRUE_TYPE_FONT_HEIGHT = 10;

	/**
	 * 外部フォントで表示したい文字列を書いたテクスチャーを生成する
	 */
	public Texture createTextTexture(String str) {

		BufferedImage image = null;
		Graphics2D g = null;
		try {
			if(font == null) createFont();
			image = new TextureLoader().createImageData(WIDTH, HEIGHT);

			// 透明色で塗りつぶし、BufferedImage を初期化する
			g = image.createGraphics();
			g.setColor(new Color(0f, 0f, 0f, 0f));
			g.fillRect(0, 0, WIDTH, HEIGHT);

			// 外部フォントを使う準備をする
			g.setFont(font);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(new Color(1f, 1f, 1f, 1f));

			// 表示する文字列の位置を計算する
			String message = str;
			int count = 0;
			int y = 0;
			while (0 < message.length()) {
				boolean isDrawed = false;

				if (message.length() <= count) {
					isDrawed = true;
				} else {
					// メッセージウインドウの右端まで文字を書き込んだら、改行する
					int width = g.getFontMetrics().stringWidth(
							message.substring(0, count + 1));

					isDrawed = (WIDTH - 200 < width);
				}

				if (isDrawed) {
					g.drawString(message.substring(0, count), 100, 40 + y);
					message = message.substring(count);
					y += TRUE_TYPE_FONT_HEIGHT + 6;
					count = 0;
				} else {
					count++;
				}
			}

			return new TextureLoader().loadTexture(image);
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

	private void createFont() {
		try {
			System.out.println(new File(FONT_FILEPATH).exists());
			InputStream is = this.getClass().getResourceAsStream(FONT_FILEPATH);
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			is.close();
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
