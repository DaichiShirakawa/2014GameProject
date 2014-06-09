package scenes.edf.characters.friendlies;

import static common.Commons.*;
import io.Key;

import java.awt.Color;

import main.FPSManager;
import scenes.edf.EDFScene;
import scenes.edf.weapons.EDFWeaponBase;
import scenes.edf.weapons.basic.EDFBasicWeapon;
import scenes.edf.weapons.sniper.EDFSnipeWeapon;
import texture.TextureLoader;
import classes.character.CircleCharacter;
import classes.character.shooting.ShootingRotateCharacter;
import common.LR;

public class EDFShip extends ShootingRotateCharacter {
	// 戦艦テクスチャ
	private static final String TEXTURE_PATH = IMAGE_FOLDER_STRING
			+ "tokiIcon.png";

	// 基礎パラメータ
	private static final int ELEVATION = 40; // 画面中心点から戦艦までの半径
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
	private EDFWeaponBase leftWeapon;
	private EDFWeaponBase rightWeapon;

	public EDFShip(EDFScene scene) {
		super(scene, 0);
		setTexture(TextureLoader.loadTexture(TEXTURE_PATH));
		setColor(new Color(0f, 0.8f, 1f));
		setY(ELEVATION);
		setWidth(SIZE);
		setHeight(SIZE);
		setTeam(ShootingTeam.FRIEND_TEAM);
		setOffsetY(ELEVATION);
		setAngle(0);

		equipLeft(new EDFSnipeWeapon(scene, this, LR.LEFT));
		equipRight(new EDFBasicWeapon(scene, this, LR.RIGHT));

		add(new CircleCharacter(ELEVATION * 2, ELEVATION * 2)).setAlpha(0.5f)
				.setColor(new Color(0f, 1f, 0.8f));
	}

	@Override
	protected boolean updateProcess() {
		processDash();
		return super.updateProcess();
	}

	private void processDash() {
		dashSpeed -= (dashSpeed + Math.signum(dashSpeed)) * 0.1;
		if (Math.abs(dashSpeed) < 0.1) {
			dashSpeed = 0;
			return;
		}
		setAngle(getAngle() + dashSpeed);
	}

	@Override
	public boolean inputProcess() {
		leftMoveProcess();
		rightMoveProcess();
		backToEarthProcess();
		shootProcess();

		return super.inputProcess();
	}

	private void leftMoveProcess() {
		if (!LEFT_MOVE.isPressing()) {
			return;
		}
		if (DASH.isPressing() && canDash()) {
			dashStart(DASH_START_SPEED);
			return;
		}
		setAngle(getAngle() + ROTATE_SPEED);
	}

	private void rightMoveProcess() {
		if (!RIGHT_MOVE.isPressing()) {
			return;
		}
		if (DASH.isPressing() && canDash()) {
			dashStart(-DASH_START_SPEED);
			return;
		}
		setAngle(getAngle() - ROTATE_SPEED);
	}

	private void backToEarthProcess() {
		if (BACK_TO_EARTH.isPressed()) {
			// TODO
		}
	}

	private void shootProcess() {
		if (((EDFScene) getParentScene()).isBreakTime()) {
			return;
		}
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

	private void dashStart(float dashSpeed) {
		this.dashSpeed = dashSpeed;
		dashStartFrame = FPSManager.totalFrame();
	}

	public void equipLeft(EDFWeaponBase weapon) {
		if (leftWeapon != null) {
			leftWeapon.destroy();
		}
		leftWeapon = add(weapon);
	}

	public void equipRight(EDFWeaponBase weapon) {
		if (rightWeapon != null) {
			rightWeapon.destroy();
		}
		rightWeapon = add(weapon);
	}

	public EDFWeaponBase getRightWeapon() {
		return rightWeapon;
	}

	public EDFWeaponBase getLeftWeapon() {
		return leftWeapon;
	}
}
