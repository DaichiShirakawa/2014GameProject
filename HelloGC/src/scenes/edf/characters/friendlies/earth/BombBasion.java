package scenes.edf.characters.friendlies.earth;

import static common.Commons.*;
import scenes.edf.weapons.bomb.EDFBombWeapon;
import texture.TextureLoader;
import classes.scene.ShootingScene;

class BombBasion extends EDFBasionBase {

	public BombBasion(ShootingScene scene, EDFEarth earth) {
		super(scene, earth, EDFBombWeapon.class);
		setTexture(TextureLoader.loadTexture(NAOKO_FOLDER_STRING + "base-suika.png"));
		setWidth(20);
		setHeight(20);
		setOffsetY(20);
	}

	@Override
	protected float getPlaceAngle() {
		return 240;
	}

}
