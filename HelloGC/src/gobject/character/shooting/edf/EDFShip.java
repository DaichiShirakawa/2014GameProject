package gobject.character.shooting.edf;

import static common.CommonMethod.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.character.shooting.ShootingCharacter;
import gobject.scene.shooting.ShootingScene;

import java.awt.Color;

import main.FPSManager;
import texture.TextureLoader;

import common.Commons.KEY;

public class EDFShip extends ShootingCharacter {
	// 戦艦テクスチャ
	private static final String TEXTURE_PATH = IMAGE_FOLDER_STRING
			+ "tokiIcon.png";

	// 基礎パラメータ
	private static final float TURN_RADIUS = 60; // 画面中心点から戦艦までの半径
	private static final float ROTATE_SPEED = 0.5f;
	private static final int SIZE = 40;

	// 入力制御
	private static final KEY LEFT_MOVE = KEY.F;
	private static final KEY RIGHT_MOVE = KEY.J;
	private static final KEY LEFT_WEAPON = KEY.D;
	private static final KEY RIGHT_WEAPON = KEY.K;
	private static final KEY SHOOT = KEY.SPACE;
	private static final KEY BACK_TO_EARTH = KEY.B;

	// ダッシュ移動パラメータ
	private static final float DASH_START_SPEED = 5;
	private static final long DASH_DELAY_FRAME = 18;
	private float dashSpeed = 0;
	private long dashStartFrame;

	public EDFShip(ShootingScene scene) {
		super(scene);
		setTexture(new TextureLoader().loadTexture(TEXTURE_PATH));
		setColor(new Color(0f, 0.8f, 1f));
		setWidth(SIZE);
		setHeight(SIZE);
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

	@Override
	public void draw() {
		// 画面中央から指定方向を向き、TURN_RADIUS距離進んだ位置に描写
		glLoadIdentity();
		glTranslated(CENTER_X, CENTER_Y, 0);
		glRotatef(getAngle(), 0, 0, 1);
		glTranslatef(0, TURN_RADIUS, 0);
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
		if (KEYBOARD.isPressing(LEFT_MOVE)) {
			setAngle(getAngle() + ROTATE_SPEED);
		}
	}

	private void rightMoveProcess() {
		if (KEYBOARD.isPressing(RIGHT_MOVE)) {
			setAngle(getAngle() - ROTATE_SPEED);
		}
	}

	private void backToEarthProcess() {
		if (KEYBOARD.isPressed(BACK_TO_EARTH)) {
		}
	}

	private void shootProcess() {
		if (KEYBOARD.isPressed(SHOOT)) {
		}
	}

	private void leftDashProcess() {
		if (!KEYBOARD.isPressing(LEFT_WEAPON) || cantMove()) {
			return;
		}
		moveStart(DASH_START_SPEED);
	}

	private void rightDashProcess() {
		if (!KEYBOARD.isPressing(RIGHT_WEAPON) || cantMove()) {
			return;
		}
		moveStart(-DASH_START_SPEED);
	}

	private boolean cantMove() {
		return dashStartFrame > FPSManager.getFramesUntilStart()
				- DASH_DELAY_FRAME;
	}

	private void moveStart(float huga) {
		dashSpeed = huga;
		dashStartFrame = FPSManager.getFramesUntilStart();
	}
}
