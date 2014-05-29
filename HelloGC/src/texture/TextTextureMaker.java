package texture;

import static common.Commons.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 受け取った文字列をテクスチャ化して返す
 * 
 * @author shirakawa
 * 
 */
public class TextTextureMaker {
	private TextTextureMaker() {
		//隠蔽
	}
	
	private static Font font = createFont(FONT_FILEPATH);

	public static Texture createTextTexture(String str, int width, int height,
			Color color) {

		BufferedImage image = null;
		Graphics2D g = null;
		try {
			image = new TextureLoader().createImageData(width, height);

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

	private static Font createFont(String filepath) {
		InputStream is = null;
		try {
			is = new FileInputStream(filepath);
			return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(
					TRUE_TYPE_FONT_HEIGHT);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
