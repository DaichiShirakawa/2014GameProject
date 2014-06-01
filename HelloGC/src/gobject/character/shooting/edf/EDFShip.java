package gobject.character.shooting.edf;

import static common.CommonMethod.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.character.GameCharacter;
import gobject.character.shooting.ShootingCharacter;
import gobject.character.shooting.edf.weapon.BasicWeapon;
import gobject.scene.shooting.ShootingScene;
import io.Key;

import java.awt.Color;

import main.FPSManager;
import texture.TextureLoader;

public class EDFShip extends ShootingCharacter {
	// 戦艦テクスチャ
	private static final String TEXTURE_PATH = IMAGE_FOLDER_STRING
			+ "tokiIcon.png";

	// 基礎パラメータ
	private static final float TURN_RADIUS = 60; // 画面中心点から戦艦までの半径
	private static final float ROTATE_SPEED = 0.5f;
	private static final int SIZE = 30;

	// 入力制御
	private static final Key LEFT_MOVE = Key.F;
	private static final Key RIGHT_MOVE = Key.J;
	private static final Key LEFT_WEAPON = Key.D;
	private static final Key RIGHT_WEAPON = Key.K;
	private static final Key SHOOT = Key.SPACE;
	private static final Key BACK_TO_EARTH = Key.B;

	// ダッシュ移動パラメータ
	private static final float DASH_START_SPEED = 5;
	private static final long DASH_DELAY_FRAME = 18;
	private float dashSpeed = 0;
	private long dashStartFrame;

	// 武器
	private static final int WEAPON_DISTANCE = 15;
	private BasicWeapon leftWeapon;
	private BasicWeapon rightWeapon;

	public EDFShip(ShootingScene scene) {
		super(scene);
		setTexture(new TextureLoader().loadTexture(TEXTURE_PATH));
		setColor(new Color(0f, 0.8f, 1f));
		setY(TURN_RADIUS);
		setWidth(SIZE);
		setHeight(SIZE);

		equipLeft(new BasicWeapon(getParentScene(), this));
		equipRight(new BasicWeapon(getParentScene(), this));
	}

	private void equipLeft(BasicWeapon weapon) {
		if(leftWeapon != null) {
			leftWeapon.dispose();
		}
		leftWeapon = getParentScene().add(weapon);
		leftWeapon.setX(-WEAPON_DISTANCE);
	}

	private void equipRight(BasicWeapon weapon) {
		if(rightWeapon != null) {
			rightWeapon.dispose();
		}
		rightWeapon = getParentScene().add(weapon);
		rightWeapon.setX(WEAPON_DISTANCE);
	}

	@Override
	public void update() {
		inputProcess();
		updateDash();
	}

	private void updateDash() {
		setAngle(getAngle() + dashSpeed);
		dashSpeed -= (dashSpeed * 0.07) + Math.signum(dashSpeed) * 0.1;
		if (Math.abs(dashSpeed) < 0.1) {
			dashSpeed = 0;
		}
	}
	
//	@Override
//	public GameCharacter setAngle(float angle) {
//		super.setAngle(angle);
//		
//		return this;
//	}

	@Override
	public void draw() {
		// 画面中央から指定方向を向き、TURN_RADIUS距離進んだ位置に描写
		glLoadIdentity();
		glTranslated(CENTER_X, CENTER_Y, 0);
		glRotatef(getAngle(), 0, 0, 1);
		glTranslatef(0, getY(), 0);
		setGlColor4f(getColor(), getAlpha());
		drawTexture(getTexture(), getWidth(), getHeight());
	}

	@Override
	protected void inputProcess() {
		leftMoveProcess();
		rightMoveProcess();
		leftDashProcess();
		rightDashProcess();
		backToEarthProcess();
		shootProcess();
	}

	private void leftMoveProcess() {
		if (LEFT_MOVE.isPressing()) {
			setAngle(getAngle() + ROTATE_SPEED);
		}
	}

	private void rightMoveProcess() {
		if (RIGHT_MOVE.isPressing()) {
			setAngle(getAngle() - ROTATE_SPEED);
		}
	}

	private void backToEarthProcess() {
		if (BACK_TO_EARTH.isPressed()) {
		}
	}

	private void shootProcess() {
		if (!SHOOT.isPressed()) {
			return;
		}
		if (LEFT_WEAPON.isPressed() && leftWeapon != null) {
			leftWeapon.shoot();
		}
		if (RIGHT_WEAPON.isPressed() && rightWeapon != null) {
			rightWeapon.shoot();
		}
	}

	private void leftDashProcess() {
		if (SHOOT.isPressing() || !LEFT_WEAPON.isPressing() || cantDash()) {
			return;
		}
		moveStart(DASH_START_SPEED);
	}

	private void rightDashProcess() {
		if (SHOOT.isPressing() || !RIGHT_WEAPON.isPressing() || cantDash()) {
			return;
		}
		moveStart(-DASH_START_SPEED);
	}

	private boolean cantDash() {
		return dashStartFrame > FPSManager.getFramesUntilStart()
				- DASH_DELAY_FRAME;
	}

	private void moveStart(float huga) {
		dashSpeed = huga;
		dashStartFrame = FPSManager.getFramesUntilStart();
	}
}
