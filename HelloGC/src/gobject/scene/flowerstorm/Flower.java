package gobject.scene.flowerstorm;

import static common.CommonMethod.*;
import static common.Commons.*;
import gobject.character.GameCharacterImpl;
import gobject.character.MoveMode;
import texture.Texture;

public class Flower extends GameCharacterImpl {
	private static final int DEFAULT_WIDTH = 64;
	private static final int DEFAULT_HEIGHT = 64;

	public Flower(Texture texture) {
		setTexture(texture);
		setWidth(DEFAULT_WIDTH);
		setHeight(DEFAULT_HEIGHT);
		setX(RND.nextInt(WIDTH + getWidth() * 2) - getWidth());
		setY(-getHeight());
		setScale(random(0.2f, 0.9f));
		setAngle(RND.nextInt(360));
		setAlpha(0.7f);
		setColor(generateCosmosColor());
		setMoveModeX(MoveMode.LOOP);
		setMoveModeY(MoveMode.UNLIMITED);

		setVx(random(-0.1f, 0.4f));
		setVy(getScale() * 2);
		setVAngle(4f);
	}

	public void update(float wind) {
		setX(getX() + (wind * getScale()));
		if (getPixcelY() > HEIGHT + getHeight() / 2) {
			setDispose();
		}
		super.update();
	}
}