package classes.character.shooting;

import classes.character.GameCharacter;
import classes.character.shooting.ShootingObjectImpl.TEAM;
import classes.scene.ShootingScene;

public interface ShootingObject extends GameCharacter {

	TEAM getTeam();

	void setTeam(TEAM team);
	
	boolean isEnemyForces(ShootingObject target);

	ShootingScene getParentScene();

	float getPower();

	float getHP();

	boolean zeroHP();

	float damage(float damage);

	void shoot(ShootingBulletCharacter bullet);

	boolean checkHitAndAction(ShootingObject target);
	
	void hitEffectTo(ShootingObject target);
}