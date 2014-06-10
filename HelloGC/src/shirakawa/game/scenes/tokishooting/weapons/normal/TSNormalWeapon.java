package shirakawa.game.scenes.tokishooting.weapons.normal;

import static shirakawa.game.common.Commons.*;
import shirakawa.game.classes.character.shooting.ShootingRotateCharacter;
import shirakawa.game.common.LR;
import shirakawa.game.scenes.tokishooting.TokiShootingScene;
import shirakawa.game.scenes.tokishooting.weapons.TSBulletBase;
import shirakawa.game.scenes.tokishooting.weapons.TSWeaponBase;
import shirakawa.game.texture.Texture;
import shirakawa.game.texture.TextureLoader;

/**
 * 通常弾
 * 
 * @author shirakawa
 *
 */
public class TSNormalWeapon extends TSWeaponBase {
	static final float DISTANCE_FROM_OWNER = 8;
	static final int SHOOT_DELAY_FRAME = 18;
	public static final int MAX_CHARGE = 50;
	public static final Texture WEAPON_TEXTURE = TextureLoader.loadTexture(TOKISHOOTING_FOLDER_STRING + "weapon-apple.png");
	private static final int RELOAD_COST = 1;

	public TSNormalWeapon(TokiShootingScene scene, ShootingRotateCharacter owner,
			LR equipLR) {
		super(scene, owner, equipLR);
		setTexture(WEAPON_TEXTURE);
		setHeight(20);
		setWidth(10);
	}

	@Override
	protected float getDistanceFromOwner() {
		return DISTANCE_FROM_OWNER;
	}

	@Override
	protected int getShootDelayFrame() {
		return SHOOT_DELAY_FRAME;
	}

	@Override
	public int getMaxBullet() {
		return MAX_CHARGE;
	}

	@Override
	protected TSBulletBase getBulletInstance() {
		return new NormalBullet(getParentScene(), this);
	}

	@Override
	public int getReloadCost() {
		return RELOAD_COST;
	}
}
