package shirakawa.game.scenes.tokishooting.characters.friendlies.earth;

import static shirakawa.game.common.Commons.*;
import shirakawa.game.classes.scene.ShootingScene;
import shirakawa.game.scenes.tokishooting.weapons.normal.TSNormalWeapon;
import shirakawa.game.texture.TextureLoader;

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
