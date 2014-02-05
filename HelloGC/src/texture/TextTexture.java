package texture;

import static main.Commons.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TextTexture {
	/**
	 * 外部フォントで表示したい文字列を書いたテクスチャーを生成する
	 */
	public Texture createTextTexture(String str, int width, int height, Color color) {

		BufferedImage image = null;
		Graphics2D g = null;
		try {
			image = new TextureLoader().createImageData(width, FONT_HEIGHT);

			// 透明色で塗りつぶし、BufferedImage を初期化する
			g = image.createGraphics();
//            g.setColor(new Color(1f, 1f, 1f, 1f));
//            g.fillRect(0, 0, width, height);

			// 外部フォントを使う準備をする
			g.setFont(font);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(color);

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
					int awidth = g.getFontMetrics().stringWidth(
							message.substring(0, count + 1));

					isDrawed = (width < awidth);
				}

				if (isDrawed) {
					g.drawString(message.substring(0, count), 0, FONT_HEIGHT - 6 + y);
					message = message.substring(count);
					y += FONT_HEIGHT + 6;
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
}
