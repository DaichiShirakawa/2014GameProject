package scenes.tokishooting.characters.friendlies.earth;

import static common.Commons.*;
import scenes.tokishooting.weapons.sniper.TSSnipeWeapon;
import texture.TextureLoader;
import classes.scene.ShootingScene;

/**
 * スナイパー砦
 * 
 * @author shirakawa
 *
 */
class SnipeBasion extends TSBasionBase {

	public SnipeBasion(ShootingScene scene, TSEarth earth) {
		super(scene, earth, TSSnipeWeapon.class);
		setTexture(TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING
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
