package scenes.tokishooting.characters.friendlies.earth;

import static common.Commons.*;
import scenes.tokishooting.weapons.normal.TSNormalWeapon;
import texture.TextureLoader;
import classes.scene.ShootingScene;

/**
 * パチンコ砦
 * 
 * @author shirakawa
 *
 */
class NormalBasion extends TSBasionBase {

	public NormalBasion(ShootingScene scene, TSEarth earth) {
		super(scene, earth, TSNormalWeapon.class);
		setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "base-apple.png"));
		setWidth(15);
		setHeight(15);
		setOffsetY(23);
	}

	@Override
	protected float getPlaceAngle() {
		return 0;
	}

}
