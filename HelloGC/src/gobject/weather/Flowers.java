package gobject.weather;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GameCharacter;
import gobject.GameUnitManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import texture.AlphaBlend;
import texture.Texture;
import texture.TextureLoader;

public class Flowers extends GameUnitManager {
    private List<Flower> flowers;
    private static final String IMAGE_FILENAME = "flower.png";
    private static int tokiPerSecond = 20;

    private static Texture flowerTexture;
    private float wind = 0f;
    private float vWind = 0f;

    @SuppressWarnings("unchecked")
    public Flowers() {
        flowerTexture = new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
                + IMAGE_FILENAME);
        // アルファブレンドで表示する
        AlphaBlend.AlphaBlend.config(flowerTexture);
        flowers = addControlList(new ArrayList<Flower>());
    }

    @Override
    public void update() {
        if (getFrameCount() % (FPS / tokiPerSecond) == 0) {
            flowers.add(new Flower(flowerTexture));
        }
        for (Iterator<Flower> ite = flowers.iterator(); ite.hasNext();) {
            Flower flower = ite.next();
            flower.update(wind);
            if (flower.canDispose()) {
                ite.remove();
            }
        }

        wind += vWind / FPS;
        if (Math.abs(wind) > Math.abs(vWind)) {
            wind = (wind * vWind < 0) ? wind + vWind : wind;
        }

        if (KEYBOARD.isPress(KEY_UP)) {
            if (tokiPerSecond < 60) {
                tokiPerSecond++;
            }
        }
        if (KEYBOARD.isPress(KEY_DOWN)) {
            if (tokiPerSecond > 1) {
                tokiPerSecond--;
            }
        }
        if (KEYBOARD.isPressed(KEY_LEFT)) {
            vWind -= 1;
        }
        if (KEYBOARD.isPressed(KEY_RIGHT)) {
            vWind += 1;
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
    public void dispose() {
        flowerTexture.dispose();
    }

    private class Flower extends GameCharacter {
        private static final int DEFAULT_WIDTH = 64;
        private static final int DEFAULT_HEIGHT = 64;

        public Flower(Texture texture) {
            setTexture(texture);
            setWidth(DEFAULT_WIDTH);
            setHeight(DEFAULT_HEIGHT);
            setX(RND.nextInt(WIDTH + getWidth() * 2) - getWidth());
            setY(-getHeight());
            setScale(random(0.2f, 1.0f));
            setAngle(RND.nextInt(360));
            setAlpha(0.8f);
            setColor(generateColorCosmos());
            setXMoveMode(MOVEMODE.LOOP);
            setYMoveMode(MOVEMODE.LOOP);

            setVx(random(-0.1f, 0.4f));
            setVy(1 + (1 - getScale()));
            setvAngle(6f * (1.3f - getScale()));
        }

        @Override
        public void update() {
            if (getY() > HEIGHT + getHeight() / 2) {
                setDispose();
            }
        }

        public void update(float wind) {
            setX(getX() + wind);
            if (getY() > HEIGHT + getHeight() / 2) {
                setDispose();
            }
            float tmp;
            tmp = getX() + getWidth();
            if (tmp < 0) {
                setX(WIDTH + getWidth() + tmp);
            }
            tmp = getX() - WIDTH - getWidth();
            if (tmp > 0) {
                setX(-getWidth() + tmp);
            }
            super.update();
        }

        @Override
        public void render() {
            setGlColor4f(getColor(), getAlpha());
            draw();
        }

    }
}
