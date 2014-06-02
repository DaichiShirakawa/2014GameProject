package scenes.shootingtest;

import texture.Texture;
import classes.character.ShootingCharacter;

public interface ShootingBulletCharacter {

	void hitEffect(ShootingCharacter target);

	void setTarget(ShootingCharacter target);

	float getBulletRange();

	int getBulletSize();

	Texture getBulletTexture();
	
	float getPower();
}