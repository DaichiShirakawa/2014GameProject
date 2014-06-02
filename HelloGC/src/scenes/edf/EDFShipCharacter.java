package scenes.edf;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import classes.character.GameCharacter;
import classes.character.ShootingCharacter;
import classes.scene.ShootingScene;
import main.FPSManager;
import scenes.edf.weapons.BasicWeapon;
import texture.TextureLoader;

public class EDFShipCharacter extends ShootingCharacter {
	// 戦艦テクスチャ
	private static final String TEXTURE_PATH = IMAGE_FOLDER_STRING
			+ "tokiIcon.png";

	// 基礎パラメータ
	private static final float TURN_RADIUS = 30; // 画面中心点から戦艦までの半径
	private static final float ROTATE_SPEED = 0.75f;
	private static final int SIZE = 16;

	// 入力制御
	private static final Key LEFT_MOVE = Key.F;
	private static final Key RIGHT_MOVE = Key.J;
	private static final Key LEFT_WEAPON = Key.D;
	private static final Key RIGHT_WEAPON = Key.K;
	private static final Key DASH = Key.SPACE;
	private static final Key BACK_TO_EARTH = Key.B;

	// ダッシュ移動パラメータ
	private static final float DASH_START_SPEED = 5;
	private static final long DASH_DELAY_FRAME = 18;
	private float dashSpeed = 0;
	private long dashStartFrame;

	// 武器
	public static final float WEAPON_DISTANCE = 8; // 本体から武器までの距離
	private BasicWeapon leftWeapon;
	private BasicWeapon rightWeapon;

	public EDFShipCharacter(ShootingScene scene) {
		super(scene);
		setTexture(new TextureLoader().loadTexture(TEXTURE_PATH));
		setColor(new Color(0f, 0.8f, 1f));
		setY(TURN_RADIUS);
		setWidth(SIZE);
		setHeight(SIZE);
		setDivision(DIVISION.FRIENDLY);
	}

	@Override
	public void update() {
		processInput();
		processDash();
	}

	private void processDash() {
		setAngle(getAngle() + dashSpeed);
		dashSpeed -= (dashSpeed * 0.1) + Math.signum(dashSpeed) * 0.1;
		if (Math.abs(dashSpeed) < 0.1) {
			dashSpeed = 0;
		}
	}

	@Override
	public GameCharacter setAngle(float angle) {
		super.setAngle(angle);
		double theta = Math.toRadians(-angle);
		setX(CENTER_X + TURN_RADIUS * (float) Math.sin(theta));
		setY(CENTER_Y + TURN_RADIUS * (float) Math.cos(theta));
		return this;
	}

	@Override
	protected void processInput() {
		processLeftMove();
		processRightMove();
		rocessBackToEarth();
		processShoot();
	}

	private void processLeftMove() {
		if (!LEFT_MOVE.isPressing()) {
			return;
		}
		if (DASH.isPressing() && canDash()) {
			moveStart(DASH_START_SPEED);
			return;
		}
		setAngle(getAngle() + ROTATE_SPEED);
	}

	private void processRightMove() {
		if (!RIGHT_MOVE.isPressing()) {
			return;
		}
		if (DASH.isPressing() && canDash()) {
			moveStart(-DASH_START_SPEED);
			return;
		}
		setAngle(getAngle() - ROTATE_SPEED);
	}

	private void rocessBackToEarth() {
		if (BACK_TO_EARTH.isPressed()) {
		}
	}

	private void processShoot() {
		if (LEFT_WEAPON.isPressing() && leftWeapon != null) {
			leftWeapon.shoot();
		}
		if (RIGHT_WEAPON.isPressing() && rightWeapon != null) {
			rightWeapon.shoot();
		}
	}

	private boolean canDash() {
		return dashStartFrame + DASH_DELAY_FRAME <= FPSManager.totalFrame();
	}

	private void moveStart(float huga) {
		dashSpeed = huga;
		dashStartFrame = FPSManager.totalFrame();
	}

	public void equipLeft(BasicWeapon weapon) {
		if (leftWeapon != null) {
			leftWeapon.dispose();
		}
		leftWeapon = getParentScene().add(weapon);
	}

	public void equipRight(BasicWeapon weapon) {
		if (rightWeapon != null) {
			rightWeapon.dispose();
		}
		rightWeapon = getParentScene().add(weapon);
	}

	public BasicWeapon getRightWeapon() {
		return rightWeapon;
	}
	public BasicWeapon getLeftWeapon() {
		return leftWeapon;
	}
}
