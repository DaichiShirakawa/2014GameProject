package scenes.tokishooting.characters.friendlies.earth;

import static common.Commons.*;
import scenes.tokishooting.weapons.bomb.TSBombWeapon;
import texture.TextureLoader;
import classes.scene.ShootingScene;

/**
 * ボム砦
 * 
 * @author shirakawa
 *
 */
class BombBasion extends TSBasionBase {

	public BombBasion(ShootingScene scene, TSEarth earth) {
		super(scene, earth, TSBombWeapon.class);
		setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "base-suika.png"));
		setWidth(20);
		setHeight(20);
		setOffsetY(20);
	}

	@Override
	protected float getPlaceAngle() {
		return 240;
	}

}
