package scenes.edf.characters.friendlies.earth;

import static common.Commons.*;
import scenes.edf.weapons.sniper.EDFSnipeWeapon;
import texture.TextureLoader;
import classes.scene.ShootingScene;

class SnipeBasion extends EDFBasionBase {

	public SnipeBasion(ShootingScene scene, EDFEarth earth) {
		super(scene, earth, EDFSnipeWeapon.class);
		setTexture(TextureLoader.loadTexture(NAOKO_FOLDER_STRING
				+ "base-tikuwa.png"));
		setWidth(20);
		setHeight(20);
		setOffsetY(20);
	}

	@Override
	protected float getPlaceAngle() {
		return 120;
	}

}
