package scenes.edf.characters.friendlies.earth;

import static common.Commons.*;
import scenes.edf.weapons.normal.EDFNormalWeapon;
import texture.TextureLoader;
import classes.scene.ShootingScene;

class NormalBasion extends EDFBasionBase {

	public NormalBasion(ShootingScene scene, EDFEarth earth) {
		super(scene, earth, EDFNormalWeapon.class);
		setTexture(TextureLoader.loadTexture(NAOKO_FOLDER_STRING + "base-apple.png"));
		setWidth(15);
		setHeight(15);
		setOffsetY(23);
	}

	@Override
	protected float getPlaceAngle() {
		return 0;
	}

}
