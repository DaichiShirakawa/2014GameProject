package scenes.flowerstorm;

import static common.CommonMethod.*;
import static common.Commons.*;
import classes.character.GameCharacterObjectImpl;
import classes.character.GameCharacterMoveMode;
import texture.Texture;

public class FlowerCharacter extends GameCharacterObjectImpl {
	private static final int DEFAULT_WIDTH = 64;
	private static final int DEFAULT_HEIGHT = 64;

	public FlowerCharacter(Texture texture) {
		setTexture(texture);
		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);
		setX(RANDOM.nextInt(WIDTH + getWidth() * 2) - getWidth());
		setY(-getHeight());
		setScale(random(0.3f, 2.0f));
		setAngle(RANDOM.nextInt(360));
		setAlpha(0.7f);
		setColor(generateCosmosColor());
		setMoveModeX(GameCharacterMoveMode.LOOP);
		setMoveModeY(GameCharacterMoveMode.UNLIMITED);

		setVx(random(-0.1f, 0.4f));
		setVy(getScale() * 2);
		setVAngle(4f);
	}

	public void update(float wind) {
		setX(getX() + (wind * getScale()));
		if (getPixcelY() > HEIGHT + getHeight() / 2) {
			setDestroy();
		}
		super.update();
	}
}