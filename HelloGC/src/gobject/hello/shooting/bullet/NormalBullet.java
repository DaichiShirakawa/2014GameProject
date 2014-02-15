package gobject.hello.shooting.bullet;

import static common.CommonLogic.*;
import static common.Commons.*;
import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.hello.shooting.GStgCharacter;
import gobject.hello.shooting.ShootingLogic;
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
	private static Texture TEXTURE = new TextureLoader()
			.loadTexture(IMAGE_FOLDER_STRING + "flower.png");

	public NormalBullet(GStgCharacter shooter) {
		super();
		setDivision(DIVISION.FRIENDLY);
		sX_ = shooter.getX();
		sY_ = shooter.getY();
		xSpeed_ = random(-0.5f, 0.5f);
		setX(sX_);
		setY(sY_);
		setColor(generateColorCosmos());
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
//			setDispose();
		}
	}

	@Override
	public void render() {
		glLoadIdentity();
		AlphaBlend.AlphaBlend.config(getTexture());
		setGlColor4f(getColor(), getAlpha());
		draw();
	}

}
