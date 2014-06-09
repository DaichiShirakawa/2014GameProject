package classes.character;

import java.awt.Color;

public class DamagePopupCharacter extends TextCharacter {
	private static final int AGE_FRAME = 1;
	private static final int CHANGE_COLOR_SPAN = 10;

	private int changeColorFrame = CHANGE_COLOR_SPAN;

	public DamagePopupCharacter(GameCharacter target, int damage) {
		super(String.valueOf(-damage));
		setX(target.getX());
		setY(target.getY() + target.getHeight() / 2);
		setScale(0.25f);
		setVY(1);
		setColor(Color.red);
		destroyAfter(AGE_FRAME);
	}

	@Override
	protected boolean updateProcess() {
		setVY(getVY() * 0.9f);
		if (changeColorFrame == 0) {
			setColor(getColor() == Color.red ? Color.orange : Color.red);
			changeColorFrame = CHANGE_COLOR_SPAN;
		}
		changeColorFrame--;
		return super.updateProcess();
	}
}
