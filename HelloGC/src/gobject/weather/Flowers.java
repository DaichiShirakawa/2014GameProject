package gobject.weather;

import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GOCharacter;
import gobject.GameObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import texture.AlphaBlend;
import texture.Texture;
import texture.TextureLoader;

public class Flowers implements GameObject {
	private static final String imageFileName = "tokiIcon.png";
	private static int tokiPerSecond = 20;

	private static Texture flowerTexture;
	private ArrayList<Flower> flowers;
	private float wind = 0f;
	private float vWind = 0f;

	public Flowers() {
		try {
			flowerTexture = new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
					+ imageFileName);
			// アルファブレンドで表示する
			AlphaBlend.AlphaBlend.config(flowerTexture);
		} catch (IOException e) {
			e.printStackTrace();
		}
		flowers = new ArrayList<>();
	}

	@Override
	public void update() {
		if (frameCount % (FPS / tokiPerSecond) == 0) {
			flowers.add(new Flower(flowerTexture));
		}
		for (Iterator<Flower> ite = flowers.iterator(); ite.hasNext();) {
			Flower flower = ite.next();
			flower.update(wind);
			if (flower.doRelease()) {
				ite.remove();
			}
		}

			wind += vWind / FPS;
			if(Math.abs(wind) > Math.abs(vWind)) {
				wind = (wind * vWind < 0) ? wind + vWind : wind;
			}

		while (Keyboard.next()) {

			if ((Keyboard.getEventKey() == Keyboard.KEY_UP)
					&& (Keyboard.getEventKeyState())) {
				if (tokiPerSecond < 60)
					tokiPerSecond++;
			} else if ((Keyboard.getEventKey() == Keyboard.KEY_DOWN)
					&& (Keyboard.getEventKeyState())) {
				if (tokiPerSecond > 1)
					tokiPerSecond--;
			} else if ((Keyboard.getEventKey() == Keyboard.KEY_LEFT)
					&& (Keyboard.getEventKeyState())) {
				vWind -= 1;
			} else if ((Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
					&& (Keyboard.getEventKeyState())) {
				vWind += 1;
			} else {
				continue;
			}
			break;
		}
	}

	@Override
	public void render() {
		// 設定を初期化する
		glLoadIdentity();
		for (Flower flower : flowers) {
			// 行列スタックに現在の行列を退避させ、新しい行列に対してモデルビュー変換を開始する
			glPushMatrix();
			flower.render();
			// 行列スタックからもとの行列を取り出す
			glPopMatrix();
		}
	}

	@Override
	public void terminate() {
		flowerTexture.dispose();
	}

	private class Flower extends GOCharacter {
		private static final int DEFAULT_WIDTH = 64;
		private static final int DEFAULT_HEIGHT = 64;
		private float vRotate;
		private float clR, clG, clB;

		private boolean release = false;

		public boolean doRelease() {
			return release;
		}

		public Flower(Texture texture) {
			setTexture(texture);
			setWidth(DEFAULT_WIDTH);
			setScale(random(0.2f, 1.0f));
			setAngle(rnd.nextInt(360));
			vRotate = 1f;
			setHeight(DEFAULT_HEIGHT);
			setX(rnd.nextInt(WIDTH + getWidth() * 2) - getWidth());
			setY(-getHeight());
			float rand = random(0f, 1.4f);
			clR = 1f;
			clG = (1f < rand) ? rand - 1f : 0f;
			clB = (rand <= 1f) ? rand : 0f;
			rand = random(0.3f, 1f);
			clR += ((1f - clR) * rand);
			clG += ((1f - clG) * rand);
			clB += ((1f - clB) * rand);
			// ランダムで、色を黒に近づける
			rand = random(0.95f, 1f);
			clR *= rand;
			clG *= rand;
			clB *= rand;

			setVx(random(-0.1f, 0.4f));
			setVy(1f);
		}

		@Override
		public void update() {
			setX(getX() + getVx());
			setAngle(getAngle() + (360 / 60 * vRotate * (1.3f - getScale())));
			setY(getY() + 1 + getVy() * (1f - getScale()));
			if (getY() > HEIGHT + getHeight() / 2) {
				release = true;
			}
		}

		public void update(float wind) {
			setX(getX() + getVx() + wind);
			setAngle(getAngle() + (360 / 60 * vRotate * (1.3f - getScale())));
			setY(getY() + 1 + getVy() * (1f - getScale()));
			if (getY() > HEIGHT + getHeight() / 2) {
				release = true;
			}
			float tmp;
			if ((tmp = getX() + getWidth()) < 0) {
				setX(WIDTH + getWidth() + tmp);
			}
			if ((tmp = getX() - WIDTH - getWidth()) > 0) {
				setX(-getWidth() + tmp);
			}
		}

		@Override
		public void render() {
			glColor4f(clR, clG, clB, 0.8f);
			draw();
		}

	}
}
