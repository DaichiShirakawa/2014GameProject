package gobject.hello.shooting.bullet;

import static common.CommonLogic.*;
import static common.Commons.*;
import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.hello.shooting.GStgCharacter;
import gobject.hello.shooting.ShootingLogic;

import java.awt.Color;

import texture.AlphaBlend;
import texture.Texture;
import texture.TextureLoader;

public class NormalBullet extends GStgBullet {
	private static final int BULLET_SIZE = 10;
	private static final int RANGE = 200;

	private float sX_;
	private float sY_;
	private float xSpeed_ = 0;
	private float ySpeed_ = 5;
	private Color color_;
	private static Texture TEXTURE = new TextureLoader()
			.loadTexture(IMAGE_FOLDER_STRING + "flower.png");

	public NormalBullet(GStgCharacter shooter) {
		super();
		division_ = DIV_FRIENDLY;
		sX_ = shooter.getX();
		sY_ = shooter.getY();
		xSpeed_ = random(-0.5f, 0.5f);
		setX(sX_);
		setY(sY_);
		float rand = random(0f, 1.4f);
		float clR = 1f;
		float clG = (1f < rand) ? rand - 1f : 0f;
		float clB = (rand <= 1f) ? rand : 0f;
		rand = random(0.3f, 1f);
		clR += ((1f - clR) * rand);
		clG += ((1f - clG) * rand);
		clB += ((1f - clB) * rand);
		// ランダムで、色を黒に近づける
		rand = random(0.95f, 1f);
		clR *= rand;
		clG *= rand;
		clB *= rand;
		color_ = new Color(clR, clG, clB);
		setTexture(TEXTURE);
		setWidth(BULLET_SIZE);
		setHeight(BULLET_SIZE);
	}

	@Override
	public void update() {
		setVx(xSpeed_);
		setVy(ySpeed_);
		move();
		setAngle(getAngle() + 12);
		if (sqrt(pow((float) (sX_ - getX()), 2f)
				+ pow((float) (sY_ - getY()), 2f)) > RANGE) {
			setDispose();
		}
		GStgCharacter target = ShootingLogic.GetInstance().checkHit(this);
		if(target != null) {
			setDispose();
		}
	}

	@Override
	public void render() {
		glLoadIdentity();
		AlphaBlend.AlphaBlend.config(getTexture());
		setGlColor4f(color_, getAlpha());
		draw();
	}

}
