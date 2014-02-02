package gobject.weather;

import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GOCharacter;
import gobject.GameObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import texture.AlphaBlend;
import texture.Texture;
import texture.TextureLoader;

public class Flowers implements GameObject {
	private static final String imageFileName = "tokiIcon.png";
	private static final float ROTATE_PER_SECOND = 0.6f;
	private static final int TOKI_PER_SECOND = 30;

	private static Texture texture;
	private ArrayList<Flower> flowers;

	public Flowers() {
		try {
			texture = new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
					+ imageFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		flowers = new ArrayList<>();
	}

	@Override
	public void update() {
		if (frameCount % (FPS / TOKI_PER_SECOND) == 0) {
			flowers.add(new Flower());
		}
		for (Iterator<Flower> ite = flowers.iterator(); ite.hasNext();) {
			Flower flower = ite.next();
			flower.setVRotate(ROTATE_PER_SECOND);
			flower.update();
			if (flower.doRelease()) {
				ite.remove();
			}
		}
	}

	@Override
	public void render() {
		// 設定を初期化する
		glLoadIdentity();
		// アルファブレンドで表示する
		AlphaBlend.AlphaBlend.config(texture);
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
		texture.dispose();
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

		public Flower() {
			setWidth(DEFAULT_WIDTH);
			setScale(random(0.2f, 1.0f));
			setAngle(rnd.nextInt(360));
			setHeight(DEFAULT_HEIGHT);
			setX(rnd.nextInt(WIDTH + getWidth() * 2) - getWidth());
			setY(-getHeight());
			clR = random(0.7f, 1f);
			clG = random(0.4f, 0.6f);
			clB = random(0.7f, 0.9f);
			float rand = random(0.3f, 1f);
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

		public void setVRotate(float rotateAngle) {
			vRotate = rotateAngle;
		}

		@Override
		public void update() {
			setX(getX() + getVx());
			setAngle(getAngle() + (360 / 60 * vRotate * (1.5f - getScale())));
			setY(getY() + 1 + getVy() * (1f - getScale()));
			if (getY() > HEIGHT + getHeight() / 2) {
				release = true;
			}
		}

		@Override
		public void render() {
			// xy原点の指定
			glTranslatef(getX(), getY(), 0);
			// 回転
			glRotatef(getAngle(), 0, 0, 1);

			glColor4f(clR, clG, clB, 1f);
			texture.bind();

			// 四角のポリゴンとする
			glBegin(GL_QUADS);

			draw(texture);

			glEnd();
		}

	}
}
