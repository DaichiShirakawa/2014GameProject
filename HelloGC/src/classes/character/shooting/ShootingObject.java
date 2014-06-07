package classes.character.shooting;

import classes.character.GameCharacter;
import classes.character.shooting.ShootingObjectImpl.TEAM;
import classes.scene.ShootingScene;

public interface ShootingObject extends GameCharacter {

	TEAM getTeam();

	void setTeam(TEAM team);
	
	boolean isEnemyForces(GameCharacter target);

	ShootingScene getParentScene();

	float getPower();

	float getHP();

	boolean zeroHP();

	float damage(float damage);

	void shoot(ShootingBulletCharacter bullet);

	void hitEffectTo(ShootingObject target);
}