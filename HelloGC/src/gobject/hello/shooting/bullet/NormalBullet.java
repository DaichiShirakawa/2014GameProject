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
	private GStgCharacter target_ = null;

	public NormalBullet(GStgCharacter shooter) {
		super();
		setDivision(DIVISION.FRIENDLY);
		startX_ = shooter.getX();
		startY_ = shooter.getY();
		setX(startX_);
		setY(startY_);
		setColor(generateColorCosmos());
		setTexture(TEXTURE);
		AlphaBlend.AlphaBlend.config(getTexture());
		setWidth(BULLET_SIZE);
		setHeight(BULLET_SIZE);

		setVx(random(-0.5f, 0.5f));
		setVy(ySpeed_);
		setvAngle(12);
	}

	@Override
	public void update() {
		if(!isEnable()) {
			if(target_ != null && getDisposeTimer() % (FPS / 2) == 0) {
				ShootingLogic.GetInstance().shoot(new Effect(target_));
			}
			super.update();
			return;
		}
		if (sqrt(pow((float) (startX_ - getX()), 2f)
				+ pow((float) (startY_ - getY()), 2f)) > RANGE) {
			setDispose();
		}
		super.update();
		checkHit();
	}

	@Override
	public void render() {
		setGlColor4f(getColor(), getAlpha());
		super.render();
	}

	protected void checkHit() {
		List<GStgCharacter> list = null;
		switch (getDivision()) {
		case FRIENDLY:
			list = ShootingLogic.GetInstance().getEnemies();
			break;
		case ENEMY:
			list = ShootingLogic.GetInstance().getFriendries();
			break;
		default:
			return;
		}

		for (GStgCharacter target : list) {
			if (checkHit(target)) {
				hitEffect(target);
			}
		}
	}

	@Override
	protected void hitEffect(GStgCharacter target) {
		for (int i = 0; i < 3; i++)
			ShootingLogic.GetInstance().shoot(new Effect(this));
		setDisposeTimer(1);
		target_ = target;
		disable();
		target.damage();
	}

	protected class Effect extends GStgBullet {
		protected Effect(GStgCharacter target_) {
			setX(target_.getX());
			setY(target_.getY());
			setTexture(target_.getTexture());
			setWidth(target_.getWidth() / 3);
			setHeight(target_.getHeight() / 3);
			setScale(random(0.5f, 2f));
			setColor(target_.getColor());
			
			float tmp = RND.nextInt(361);
			setVx(4 * (float)sin(tmp) * random(0.2f, 1f));
			setVy(4 * (float)cos(tmp) * random(0.2f, 1f));
			setAngle(tmp);
			setDisposeTimer(0.8f * random(0.5f, 1.5f));
		}

		@Override
		public void update() {
			setVx(getVx() * 0.95f);
			setVy(getVy() * 0.95f);
			super.update();
		}

		@Override
		public void render() {
			setGlColor4f(getColor(), getAlpha());
			super.render();
		}

		@Override
		protected void checkHit() {
			return;
		}

		@Override
		protected void hitEffect(GStgCharacter target) {
			return;
		}

	}
}
