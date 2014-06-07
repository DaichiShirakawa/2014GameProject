package classes.character.shooting;

import classes.character.GameCharacter;
import classes.character.shooting.ShootingCharacterImpl.SHOOTING_TEAM;
import classes.scene.ShootingScene;

public interface ShootingCharacter extends GameCharacter {

	SHOOTING_TEAM getTeam();

	void setTeam(SHOOTING_TEAM team);
	
	boolean isEnemyTeam(GameCharacter target);

	ShootingScene getParentScene();

	float getPower();

	float getHP();

	boolean zeroHP();

	float damage(float damage);

	void shoot(ShootingBulletCharacter bullet);

	void hitEffectTo(ShootingCharacter target);
}