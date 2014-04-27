package common;

import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import texture.Texture;

public final class CommonLogic {
    private CommonLogic() {
        // 隠蔽
    }

    /**
     * min <= r <= max の範囲の乱数rを生成
     */
    public static float random(final float min, final float max) {
        float dist = max - min;
        return (float) (Math.random() * dist + min);
    }

    /**
     *背景クリア色の変更
     */
    public static void setClearColorWhite() {
        glClearColor(1f, 1f, 1f, 1f);
    }

    public static void setClearColorBlack() {
        glClearColor(0f, 0f, 0f, 1f);
    }

    /**
     * テクスチャつきポリゴンの描写
     */
    public static void drawTexture(final Texture texture, final int width, final int height) {
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
    public static String formatDouble0d0(final double val) {
        return FORMAT_FOR_FLOAT_0D0.format(val);
    }

    /**
     * Color→LWJGL互換用
     */
    public static void setGlColor4f(final Color color, final float alpha) {
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
