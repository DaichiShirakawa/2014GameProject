package gobject.hello.shooting;

import static common.CommonLogic.*;
import static common.Commons.*;
import static java.lang.Math.*;

import java.awt.Color;

import texture.TextureLoader;

public class EnemyShip extends GStgCharacter{
	private int size_ = 32;
//	private int speed_ = 1;
	private float thita = 0;
	private boolean damaging_ = false;
	private float dmgVibMove_ = 0;
	private float dmgVibThita_ = 0;

	public EnemyShip() {
		setDivision(DIVISION.ENEMY);
		setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING
				+ "DotTokiIcon.png"));
		setWidth(size_);
		setHeight(size_);
		setX(WIDTH / 2);
		setY(HEIGHT - getHeight());
		setColor(new Color(1f, 0.6f, 0.6f));
//		setVx(speed_);
//		setVy(speed_);
		setXMoveMode(MOVEMODE.LOOP);
		setYMoveMode(MOVEMODE.LOOP);
	}

	@Override
	public void update() {
		thita += 1 / (float)FPS;
		setX(WIDTH / 2 + 100 * (float)sin(thita));
		setY(HEIGHT / 2 + 100 * (float)cos(thita));
		damageUpdate();
		super.update();
	}

	@Override
	public void render() {
		setGlColor4f(getColor(), getAlpha());
		draw();
	}

	@Override
	public void damage() {
		damaging_ = true;
		dmgVibMove_ = 5;
		dmgVibThita_ = 0;
		super.damage();
	}
	
	public void damageUpdate() {
		if(!damaging_) return;
		dmgVibMove_ *= 0.95f;
		dmgVibThita_ += 1;
		setX(getX() + dmgVibMove_ * (float)sin(dmgVibThita_));
		if(dmgVibMove_ < 0.5) damaging_ = false;
	}
}
