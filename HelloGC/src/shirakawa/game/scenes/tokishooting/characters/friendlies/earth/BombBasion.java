package shirakawa.game.scenes.tokishooting.characters.friendlies.earth;

import static shirakawa.game.common.Commons.*;
import shirakawa.game.classes.scene.ShootingScene;
import shirakawa.game.scenes.tokishooting.weapons.bomb.TSBombWeapon;
import shirakawa.game.texture.TextureLoader;

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
