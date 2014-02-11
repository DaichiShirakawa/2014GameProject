package gobject.weather;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GCharacterObect;
import gobject.GLogicObject;
import gobject.GameObject;

import java.util.Iterator;

import texture.AlphaBlend;
import texture.Texture;
import texture.TextureLoader;

public class Flowers extends GLogicObject {
	private static final String imageFileName = "flower.png";
	private static int tokiPerSecond = 20;

	private static Texture flowerTexture;
	private float wind = 0f;
	private float vWind = 0f;

	public Flowers() {
		flowerTexture = new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
				+ imageFileName);
		// アルファブレンドで表示する
		AlphaBlend.AlphaBlend.config(flowerTexture);
	}

	@Override
	public void update() {
		if (getFrameCount() % (FPS / tokiPerSecond) == 0) {
			addChild(new Flower(flowerTexture));
		}
		for (Iterator<GameObject> ite = children_.iterator(); ite.hasNext();) {
			Flower flower = (Flower) ite.next();
			flower.update(wind);
			if (flower.canDispose()) {
				ite.remove();
			}
		}

		wind += vWind / FPS;
		if (Math.abs(wind) > Math.abs(vWind)) {
			wind = (wind * vWind < 0) ? wind + vWind : wind;
		}

		if (keyboard.isPress(KEY_UP)){
			if (tokiPerSecond < 60)
				tokiPerSecond++;
		} else if (keyboard.isPress(KEY_DOWN)) {
			if (tokiPerSecond > 1)
				tokiPerSecond--;
		} else if (keyboard.isPressed(KEY_LEFT)) {
			vWind -= 1;
		} else if (keyboard.isPressed(KEY_RIGHT)) {
			vWind += 1;
		}
	}

	@Override
	public void render() {
		// 設定を初期化する
		glLoadIdentity();
		for (GameObject flower : children_) {
			// 行列スタックに現在の行列を退避させ、新しい行列に対してモデルビュー変換を開始する
			glPushMatrix();
			((Flower)flower).render();
			// 行列スタックからもとの行列を取り出す
			glPopMatrix();
		}
	}

	@Override
	public void terminate() {
		flowerTexture.dispose();
	}

	private class Flower extends GCharacterObect {
		private static final int DEFAULT_WIDTH = 64;
		private static final int DEFAULT_HEIGHT = 64;
		private float vRotate;
		private float clR, clG, clB;

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
				setDispose(true);
			}
		}

		public void update(float wind) {
			setX(getX() + getVx() + wind);
			setAngle(getAngle() + (360 / 60 * vRotate * (1.3f - getScale())));
			setY(getY() + 1 + getVy() * (1f - getScale()));
			if (getY() > HEIGHT + getHeight() / 2) {
				setDispose(true);
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
