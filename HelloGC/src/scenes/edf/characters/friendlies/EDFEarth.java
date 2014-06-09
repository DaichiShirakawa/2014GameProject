package scenes.edf.characters.friendlies;

import static common.Commons.*;

import java.awt.Color;

import classes.character.CircleCharacter;
import classes.character.TextCharacter;
import classes.character.shooting.ShootingCharacterImpl;
import classes.scene.ShootingScene;

public class EDFEarth extends ShootingCharacterImpl {
	private static final int SIZE = 35;
	private static final float JITEN = 0.1f;
	private static final Color COLOR = Color.green;

	private TextCharacter hpCaption;

	public EDFEarth(ShootingScene scene) {
		super(scene, Float.MAX_VALUE, 10);
//		 TextTextureMaker.createText("○", FontDef.RICTY_64)
		setTexture(new CircleCharacter(SIZE * 2, SIZE * 2, 4f).getTexture());
		setTeam(ShootingTeam.FRIEND_TEAM);
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(SIZE);
		setHeight(SIZE);
		setVAngle(JITEN);
		setColor(COLOR);

		hpCaption = add(new TextCharacter(getHpText()));
		hpCaption.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.3f)
				.setColor(getCaptionColor(getHP()));
	}

	private Color getCaptionColor(float hp) {
		return new Color(255, (int) (hp * 17.5), (int) (hp * 17.5));
	}

	@Override
	protected boolean updateProcess() {
		hpCaption.updateText(getHpText());
		hpCaption.setColor(getCaptionColor(getHP()));
		return super.updateProcess();
	}

	private String getHpText() {
		return "♡" + (int) getHP() + " ";
	}

	@Override
	protected void destroyProcess() {
		super.destroyProcess();
		setColor(Color.red);
	}
}