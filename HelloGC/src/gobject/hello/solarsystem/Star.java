package gobject.hello.solarsystem;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GameCharacter;

import java.awt.Color;

import texture.TextTexture;

public class Star extends GameCharacter {
    private Star parentStar;
    private String caption_;
    private double kAngle_ = 0;
    private double jAngle_ = 0;
    private double koten_ = 0.1f;
    private double jiten_ = 0.1f;
    private Color color_;

    public Star(Star parentStar, String caption, Float scale, Color color,
            double koten, double jiten) {
        this.parentStar = parentStar;
        this.caption_ = caption;
        this.color_ = color;
        setScale(scale);
        setX(WIDTH / 2);
        setY(HEIGHT / 2);
        setTexture(new TextTexture().createTextTexture(this.caption_,
                FONT_HEIGHT, FONT_HEIGHT, this.color_));
        setWidth(24);
        setHeight(24);
        setAngle(0);
        kAngle_ = 0; //random(0, 360);
        jAngle_ = 0; //random(0, 360);
        this.koten_ = koten;
        this.jiten_ = jiten;
    }

    public Star makeChild(String caption, float scale, float hankei,
            Color color, double koten, double jiten) {
        Star childStar = new Star(this, caption, scale, color, koten,
                jiten);
        childStar.setX(0);
        childStar.setY(hankei);
        return childStar;
    }

    @Override
    public void update() {
    }

    public void update(double timeScale) {
        jAngle_ += ((365 * FPS) / jiten_) * timeScale;
        kAngle_ += (FPS / koten_) * timeScale;
    }

    @Override
    public void render() {
        glLoadIdentity();
        setTranslate(this);
        setGlColor4f(color_, 1f);
        glRotatef((float) jAngle_, 0, 0, 1);
        drawTexture(getTexture(), getWidth(), getHeight());
    }

    private void setTranslate(Star star) {
        if (star.parentStar == null) {
            glTranslatef(star.getX(), star.getY(), 0);
        } else {
            setTranslate(star.parentStar);
            glRotatef((float) star.kAngle_, 0, 0, 1);
            glTranslatef(star.getX(), star.getY(), 0);
            glRotatef((float) -star.kAngle_, 0, 0, 1);
        }
    }

    public double getJAngle() {
        return jAngle_;
    }

    public double getKAngle() {
        return kAngle_;
    }

}
