package scenes.edf;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import main.FPSManager;
import scenes.edf.weapons.BasicWeapon;
import texture.TextureLoader;
import classes.character.shooting.RotateShootingCharacter;
import classes.character.shooting.ShootingWeaponCharacter;
import classes.scene.ShootingScene;

public class EDFShip extends RotateShootingCharacter {
	// 戦艦テクスチャ
	private static final String TEXTURE_PATH = IMAGE_FOLDER_STRING
			+ "tokiIcon.png";

	// 基礎パラメータ
	private static final float ELEVATION = 30; // 画面中心点から戦艦までの半径
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
	private ShootingWeaponCharacter leftWeapon;
	private ShootingWeaponCharacter rightWeapon;

	public EDFShip(ShootingScene scene) {
		super(scene, 0);
		setTexture(TextureLoader.loadTexture(TEXTURE_PATH));
		setColor(new Color(0f, 0.8f, 1f));
		setY(ELEVATION);
		setWidth(SIZE);
		setHeight(SIZE);
		setTeam(TEAM.FRIEND_TEAM);
		setElevation(ELEVATION);
	}

	@Override
	public void update() {
		inputProcess();
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
	public void inputProcess() {
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

	public ShootingWeaponCharacter getRightWeapon() {
		return rightWeapon;
	}

	public ShootingWeaponCharacter getLeftWeapon() {
		return leftWeapon;
	}
}
