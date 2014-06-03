package classes.character.shooting;

import static common.Commons.*;
import classes.character.GameCharacterObject;
import classes.scene.ShootingScene;

public abstract class RotateShootingCharacter extends ShootingCharacter {
	private float elevation;
	private float vElevation;

	public RotateShootingCharacter(ShootingScene scene, float power) {
		super(scene, power);
	}

	public RotateShootingCharacter(ShootingScene scene, float power, float hp) {
		super(scene, power, hp);
	}

	@Override
	public void update() {
		super.update();
		setElevation(getElevation() + getvElevation());
	}

	@Override
	public GameCharacterObject setAngle(float angle) {
		super.setAngle(angle);
		double theta = Math.toRadians(-angle);
		setX(CENTER_X + getElevation() * (float) Math.sin(theta));
		setY(CENTER_Y + getElevation() * (float) Math.cos(theta));
		return this;
	}

	public float getElevation() {
		return elevation;
	}

	public RotateShootingCharacter setElevation(float elevation) {
		this.elevation = elevation;
		return this;
	}

	public float getvElevation() {
		return vElevation;
	}

	public RotateShootingCharacter setVElevation(float vElevation) {
		this.vElevation = vElevation;
		return this;
	}

}