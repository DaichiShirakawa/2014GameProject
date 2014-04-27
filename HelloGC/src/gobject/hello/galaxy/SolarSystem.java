package gobject.hello.galaxy;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GameCharacter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import texture.TextTexture;
import texture.Texture;
import texture.TextureLoader;

public class SolarSystem extends GameCharacter {
    private List<Star> stars;
    private double timeScale = 0.0001f;
    private int inputScale = 1;
    private static final int INPUT_INTERVAL = 5;

    private Texture tokiTexture;
    private Texture fontTexture;
    private double keika = 0;
    private float tokiy;
    private float tokix;

    public SolarSystem() {
        Star star;
        stars = new ArrayList<Star>();
        Star sun = new Star(null, "太", 1f, Color.orange, 0, 27.275);
        stars.add(sun);
        stars.add(sun.makeChild("水", 0.4f, 15, new Color(0.5f, 0.5f, 1f),
                0.241, 58.65));
        stars.add(sun.makeChild("金", 0.5f, 30, Color.yellow, 0.615, 243.0187));

        star = sun.makeChild("地", 0.6f, 45, new Color(0.5f, 1f, 0.5f), 1,
                0.997271);
        stars.add(star);

        stars.add(star.makeChild("月", 0.3f, 10, Color.white,
                27.31266666666666666 / (double) 365, 27.31266666666666666));

        star = sun.makeChild("火", 0.5f, 60, Color.red, 1.881, 1.02595);
        stars.add(star);
        stars.add(star.makeChild("フ", 0.3f, 5, Color.white,
                0.3189 / (double) 365, 0.3189));
        stars.add(star.makeChild("ダ", 0.3f, 10, Color.white,
                1.2630 / (double) 365, 1.2630));

        star = sun.makeChild("木", 0.8f, 75, new Color(0.9f, 0.6f, 0.5f),
                11.86, 0.4135);
        stars.add(star);
        stars.add(star.makeChild("メ", 0.3f, 5, Color.white, 0.2948 / (double) 365, 0.2948));
        stars.add(star.makeChild("ア", 0.3f, 10, Color.white, 0.2983 / (double) 365, 0.2983));
        stars.add(star.makeChild("マ", 0.3f, 15, Color.white, 0.4981 / (double) 365, 0.4981));
        stars.add(star.makeChild("テ", 0.3f, 20, Color.white, 0.6745 / (double) 365, 0.6745));
        stars.add(star.makeChild("イ", 0.3f, 25, Color.white, 1.7691 / (double) 365, 1.7691));

        star = sun.makeChild("土", 0.7f, 90, new Color(0.9f, 0.7f, 0.5f),
                29.46, 0.4264);
        stars.add(star);
        stars.add(star.makeChild("パ", 0.3f, 5, Color.white, 0.5750 / (double) 365, 0.5750));
        stars.add(star.makeChild("ア", 0.3f, 10, Color.white, 0.6020 / (double) 365, 0.6020));
        stars.add(star.makeChild("プ", 0.3f, 15, Color.white, 0.6130 / (double) 365, 0.6130));
        stars.add(star.makeChild("パ", 0.3f, 20, Color.white, 0.6290 / (double) 365, 0.6290));
        stars.add(star.makeChild("エ", 0.3f, 25, Color.white, 0.6940 / (double) 365, 0.6940));

        star = sun.makeChild("天", 0.4f, 105, Color.white, 84.01, 0.7181);
        stars.add(star);
        stars.add(star.makeChild("コ", 0.3f, 5, Color.white, 0.3350 / (double) 365, 0.3350));
        stars.add(star.makeChild("オ", 0.3f, 10, Color.white, 0.3760 / (double) 365, 0.3760));
        stars.add(star.makeChild("ビ", 0.3f, 15, Color.white, 0.4350 / (double) 365, 0.4350));
        stars.add(star.makeChild("ク", 0.3f, 20, Color.white, 0.4640 / (double) 365, 0.4640));
        stars.add(star.makeChild("デ", 0.3f, 25, Color.white, 0.4740 / (double) 365, 0.4740));

        star = sun.makeChild("海", 0.4f, 120, Color.blue, 164.79, 0.6712);
        stars.add(star);
        stars.add(star.makeChild("ナ", 0.3f, 5, Color.white, 0.2960 / (double) 365, 0.2960));
        stars.add(star.makeChild("タ", 0.3f, 10, Color.white, 0.3120 / (double) 365, 0.3120));
        stars.add(star.makeChild("デ", 0.3f, 15, Color.white, 0.3330 / (double) 365, 0.3330));
        stars.add(star.makeChild("ガ", 0.3f, 20, Color.white, 0.4290 / (double) 365, 0.4290));
        stars.add(star.makeChild("リ", 0.3f, 25, Color.white, 0.5540 / (double) 365, 0.5540));

        fontTexture = new TextTexture().createTextTexture(keika + "日経過/speed:"
                + inputScale, FONT_HEIGHT, FONT_HEIGHT, Color.white);
        tokiTexture = new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
                + "dotTokiIcon.png");
    }

    @Override
    public void update() {
        for (Star star : stars) {
            star.update(getTimeScale());
        }
        keika += ((365 * FPS) / (double) 360) * getTimeScale();
        fontTexture.dispose();
        fontTexture = new TextTexture().createTextTexture("d:" + (int) keika
                + "/s:" + inputScale, 600, FONT_HEIGHT, Color.white);

        if (KEYBOARD.getPressLength(KEY_UP) % INPUT_INTERVAL == 0) {
            inputScale++;
        }
        if (KEYBOARD.getPressLength(KEY_DOWN) % INPUT_INTERVAL == 0) {
            inputScale--;
        }
        if (KEYBOARD.getPressLength(KEY_LEFT) % INPUT_INTERVAL == 0) {
            inputScale -= 10;
        }
        if (KEYBOARD.getPressLength(KEY_RIGHT) % INPUT_INTERVAL == 0) {
            inputScale += 10;
        }
        if (KEYBOARD.isPressed(KEY_SPACE)) {
            inputScale = 0;
        }

        tokix -= 3;
        tokiy -= 2;
    }

    @Override
    public void render() {
        for (Star star : stars) {
            star.render();
        }

        glLoadIdentity();
        glTranslatef(60, HEIGHT - 10, 0);
        drawTexture(fontTexture, 100, 10);
        glLoadIdentity();
        glTranslatef(tokix, tokiy, 0);
        drawTexture(tokiTexture, 20, 20);
    }

    @Override
    public void dispose() {
        tokiTexture.dispose();
        fontTexture.dispose();
        super.dispose();
    }

    public double getTimeScale() {
        return (double) inputScale * timeScale;
    }
}
