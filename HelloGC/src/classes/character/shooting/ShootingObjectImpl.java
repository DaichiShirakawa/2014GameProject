package classes.character.shooting;

import java.util.HashSet;
import java.util.Set;

import classes.character.GameCharacter;
import classes.character.GameCharacterObjectImpl;
import classes.scene.ShootingScene;

public abstract class ShootingObjectImpl extends GameCharacterObjectImpl
		implements ShootingObject {
	private ShootingScene parentScene;
	private TEAM team;
	private float power;
	private float hp;
	private boolean undead = false;
	private Set<ShootingObject> hittedObjects = new HashSet<>();

	public enum TEAM {
		FRIEND_TEAM,
		ENEMY_TEAM,
		NO_TEAM,
	}

	public ShootingObjectImpl(ShootingScene scene, float power) {
		this.parentScene = scene;
		this.power = power;
		this.undead = true;
	}

	public ShootingObjectImpl(ShootingScene scene, float power, float hp) {
		this.parentScene = scene;
		this.power = power;
		this.hp = hp;
	}

	@Override
	public TEAM getTeam() {
		return team;
	}

	@Override
	public void setTeam(TEAM division) {
		this.team = division;
	}

	@Override
	public boolean isEnemyForces(GameCharacter target) {
		if (!(target instanceof ShootingCharacter)) {
			return false;
		}
		switch (getTeam()) {
		case FRIEND_TEAM:
			return ((ShootingObjectImpl) target).getTeam() == TEAM.ENEMY_TEAM;
		case ENEMY_TEAM:
			return ((ShootingObjectImpl) target).getTeam() == TEAM.FRIEND_TEAM;
		default:
			return false;
		}
	}

	@Override
	public ShootingScene getParentScene() {
		return parentScene;
	}

	protected void setParentScene(ShootingScene parentScene) {
		this.parentScene = parentScene;
	}

	@Override
	public float getPower() {
		return power;
	}

	@Override
	public boolean zeroHP() {
		return !undead && hp == 0;
	}

	@Override
	public float getHP() {
		return hp;
	}

	@Override
	public float damage(float damage) {
		if (undead) {
			return Float.MAX_VALUE;
		}
		hp -= damage;
		if (hp < 0) {
			hp = 0;
		}
		return hp;
	}

	@Override
	protected boolean canDisposeTexture() {
		return false;
	}

	@Override
	public void shoot(ShootingBulletCharacter bullet) {
		bullet.setParentScene(parentScene);
		parentScene.add(bullet);
	}

	@Override
	public boolean checkHit(GameCharacter target) {
		boolean notShootingCharacter = !(target instanceof ShootingCharacter);
		boolean notEnemyForces = !isEnemyForces(target);
		boolean notHit = !super.checkHit(target);
		if (notShootingCharacter || notEnemyForces
				|| hittedObjects.contains(target) || notHit) {
			return false;
		}
		return true;
	}

	@Override
	public void hitEffectTo(ShootingObject target) {
		hittedObjects.add(target);
		target.damage(getPower());
	}
}
