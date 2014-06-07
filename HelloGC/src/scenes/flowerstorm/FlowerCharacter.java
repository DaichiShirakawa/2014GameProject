package scenes.flowerstorm;

import static common.CommonMethod.*;
import static common.Commons.*;
import texture.Texture;
import classes.character.GameCharacterImpl;
import classes.character.GameCharacterMoveMode;

public class FlowerCharacter extends GameCharacterImpl {
	private static final int DEFAULT_WIDTH = 64;
	private static final int DEFAULT_HEIGHT = 64;
	private float wind;

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

	@Override
	protected boolean updateProcess() {
		setX(getX() + (wind * getScale()));
		if (getY() > HEIGHT + getHeight() / 2) {
			destroy();
		}
		return super.updateProcess();
	}

	public FlowerCharacter setWind(float wind) {
		this.wind = wind;
		return this;
	}

	@Override
	protected boolean canDisposeTexture() {
		return false;
	}
}