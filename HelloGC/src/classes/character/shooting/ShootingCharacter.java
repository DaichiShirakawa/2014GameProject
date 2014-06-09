package classes.character.shooting;

import classes.character.GameCharacter;
import classes.character.shooting.ShootingCharacterImpl.ShootingTeam;
import classes.scene.ShootingScene;

public interface ShootingCharacter extends GameCharacter {

	ShootingTeam getTeam();

	void setTeam(ShootingTeam team);
	
	boolean isEnemyTeam(GameCharacter target);

	ShootingScene getParentScene();

	float getPower();

	float getHP();

	boolean zeroHP();

	float damage(float damage);

	void shoot(ShootingBulletCharacter bullet);

	void hitEffectTo(ShootingCharacter target);
}