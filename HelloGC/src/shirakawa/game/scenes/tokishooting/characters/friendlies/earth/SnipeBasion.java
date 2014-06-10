package shirakawa.game.scenes.tokishooting.characters.friendlies.earth;

import static shirakawa.game.common.Commons.*;
import shirakawa.game.classes.scene.ShootingScene;
import shirakawa.game.scenes.tokishooting.weapons.sniper.TSSnipeWeapon;
import shirakawa.game.texture.TextureLoader;

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
