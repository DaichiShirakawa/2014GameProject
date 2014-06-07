package classes.character.shooting;

import static common.Commons.*;
import classes.character.GameCharacter;
import classes.scene.ShootingScene;

public abstract class ShootingRotateCharacter extends ShootingCharacterImpl {
	private float offsetY;
	private float vOffsetY;
	private float offsetX;
	private float vOffsetX;

	public ShootingRotateCharacter(ShootingScene scene, float power) {
		super(scene, power);
	}

	public ShootingRotateCharacter(ShootingScene scene, float power, float hp) {
		super(scene, power, hp);
	}

	@Override
	protected boolean updateProcess() {
		setOffsetY(getOffsetY() + getVOffsetY());
		setOffsetX(getOffsetX() + getVOffsetX());
		return super.updateProcess();
	}

	@Override
	public GameCharacter setAngle(float angle) {
		super.setAngle(angle);
		double theta = Math.toRadians(angle);
		// thisから見た縦方向の移動
		setX(CENTER_X + getOffsetY() * (float) Math.sin(-theta));
		setY(CENTER_Y + getOffsetY() * (float) Math.cos(-theta));
		// thisから見た横方向の移動
		setX(getX() + getOffsetX() * (float) Math.cos(theta));
		setY(getY() + getOffsetX() * (float) Math.sin(theta));
		return this;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public ShootingRotateCharacter setOffsetY(float offsetY) {
		this.offsetY = offsetY;
		return this;
	}

	public float getVOffsetY() {
		return vOffsetY;
	}

	public ShootingRotateCharacter setVOffsetY(float vOffsetY) {
		this.vOffsetY = vOffsetY;
		return this;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public float getVOffsetX() {
		return vOffsetX;
	}

	public void setVOffsetX(float vOffsetX) {
		this.vOffsetX = vOffsetX;
	}
	
}