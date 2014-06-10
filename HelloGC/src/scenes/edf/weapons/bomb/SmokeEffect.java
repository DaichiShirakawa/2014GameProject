package scenes.edf.weapons.bomb;

import static common.CommonMethod.*;
import static common.Commons.*;

import java.awt.Color;

import texture.Texture;
import texture.TextureLoader;
import classes.character.shooting.BasicEffect;
import classes.character.shooting.ShootingCharacter;
import classes.scene.ShootingScene;

class SmokeEffect extends BasicEffect {
	private static final Texture SMOKETEXTURE = TextureLoader.loadTexture(NAOKO_FOLDER_STRING
			+ "smoke.png");
	private boolean reverse;

	public SmokeEffect(ShootingScene parentScene, ShootingCharacter source,
			boolean reverse) {
		super(parentScene, source);
		this.reverse = reverse;
		float tmp = random(0.5f, 1);
		setColor(new Color(tmp, tmp, tmp));
		setWidth(20);
		setHeight(20);
		setScale(tmp = random(0.4f, 0.7f));
		setTexture(SMOKETEXTURE);
	}

	@Override
	protected void setMoveSpeeds() {
		if (reverse) {
			setVX(-getShooter().getVX() * 0.3f);
			setVY(-getShooter().getVY() * 0.3f);
		} else {
			setVX(getShooter().getVX() * 0.3f);
			setVY(getShooter().getVY() * 0.3f);
		}
	}

}