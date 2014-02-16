package gobject.hello.shooting.bullet;

import static common.CommonLogic.*;
import static common.Commons.*;
import static java.lang.Math.*;
import gobject.hello.shooting.GStgCharacter;
import gobject.hello.shooting.ShootingLogic;

import java.util.List;

import texture.AlphaBlend;
import texture.Texture;
import texture.TextureLoader;

public class NormalBullet extends GStgBullet {
	private static final int BULLET_SIZE = 10;
	private static final int RANGE = 200;

	private float startX_;
	private float startY_;
	private float ySpeed_ = 5;
	private static Texture TEXTURE = new TextureLoader()
			.loadTexture(IMAGE_FOLDER_STRING + "flower.png");

	public NormalBullet(GStgCharacter shooter) {
		super();
		setDivision(DIVISION.FRIENDLY);
		startX_ = shooter.getX();
		startY_ = shooter.getY();
		setX(startX_);
		setY(startY_);
		setColor(generateColorCosmos());
		setTexture(TEXTURE);
		setWidth(BULLET_SIZE);
		setHeight(BULLET_SIZE);
		
		setVx(random(-0.5f, 0.5f));
		setVy(ySpeed_);
		setvAngle(12);
	}

	@Override
	public void update() {
		if (sqrt(pow((float) (startX_ - getX()), 2f)
				+ pow((float) (startY_ - getY()), 2f)) > RANGE) {
			setDispose();
		}
		super.update();
		checkHit();
	}

	@Override
	public void render() {
		AlphaBlend.AlphaBlend.config(getTexture());
		setGlColor4f(getColor(), getAlpha());
		draw();
	}

	protected void checkHit() {
		List<GStgCharacter> list = null;
		switch(getDivision()) {
		case FRIENDLY:
			list = ShootingLogic.GetInstance().getEnemies();
			break;
		case ENEMY:
			list = ShootingLogic.GetInstance().getFriendries();
			break;
		default:
			break;
		}
		
		for(GStgCharacter target : list) {
			if(checkHit(target)) {
				hitEffect(target);
			}
		}
	}

	@Override
	protected void hitEffect(GStgCharacter target) {
		setDispose();
	}
}
