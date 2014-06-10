package scenes.edf.characters.friendlies.earth;

import static common.Commons.*;

import java.awt.Color;

import texture.Texture;
import texture.TextureLoader;
import classes.character.shooting.ShootingCharacterImpl;
import classes.scene.ShootingScene;

public class EDFEarth extends ShootingCharacterImpl {
	private static final int SIZE = 35;
	private static final float JITEN = 0.1f;
	private static final Texture TEXTURE = TextureLoader.loadTexture(NAOKO_FOLDER_STRING
			+ "earth.png");

	public EDFEarth(ShootingScene scene) {
		super(scene, Float.MAX_VALUE, 10);
		setTexture(TEXTURE);
		setTeam(ShootingTeam.FRIEND_TEAM);
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(SIZE);
		setHeight(SIZE);
		setVAngle(JITEN);

		add(new HPCaption(this));
		add(new NormalBasion(getParentScene(), this));
		add(new SnipeBasion(getParentScene(), this));
		add(new BombBasion(getParentScene(), this));
	}

	@Override
	protected void destroyProcess() {
		setColor(Color.orange);
	}
}