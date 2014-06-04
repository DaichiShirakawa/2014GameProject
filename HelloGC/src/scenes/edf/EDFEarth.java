package scenes.edf;

import static common.Commons.*;

import java.awt.Color;

import texture.text.FontDef;
import texture.text.TextTextureMaker;
import classes.character.TextCharacter;
import classes.character.shooting.ShootingCharacter;
import classes.scene.ShootingScene;

public class EDFEarth extends ShootingCharacter {
	private static final int SIZE = 45;
	private static final float JITEN = 0.1f;
	private static final Color COLOR = Color.green;

	private TextCharacter hpCaption;

	public EDFEarth(ShootingScene scene) {
		super(scene, Float.MAX_VALUE, 10);
		setTexture(TextTextureMaker.createText("○", FontDef.RICTY_64));
		setTeam(TEAM.FRIEND_TEAM);
		setX(CENTER_X);
		setY(CENTER_Y);
		setWidth(SIZE);
		setHeight(SIZE);
		setVAngle(JITEN);
		setColor(COLOR);

		hpCaption = new TextCharacter(getHpText());
		hpCaption.setX(CENTER_X)
				.setY(CENTER_Y)
				.setScale(0.3f)
				.setColor(getCaptionColor(getHP()));
	}

	private Color getCaptionColor(float hp) {
		return new Color(255, (int) (hp * 17.5), (int) (hp * 17.5));
	}

	@Override
	public void update() {
		super.update();
		hpCaption.updateText(getHpText());
		hpCaption.setColor(getCaptionColor(getHP()));
	}

	@Override
	public void render() {
		// TODO 自動生成されたメソッド・スタブ
		super.render();
		hpCaption.render();
	}

	private String getHpText() {
		return "♡" + (int) getHP() + " ";
	}

	@Override
	public void dispose() {
		super.dispose();
		hpCaption.dispose();
	}

	@Override
	protected void dead() {
		setColor(Color.red);
	}

}
