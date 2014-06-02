package scenes.flowerstorm;

import static common.Commons.*;
import static java.lang.Math.*;
import io.Key;

import java.util.Iterator;

import classes.GameObject;
import classes.scene.GameSceneImpl;
import main.FPSManager;
import texture.Texture;
import texture.TextureLoader;
import common.CommonMethod.BackGroundColor;

public class FlowerStormScene extends GameSceneImpl {

	private static final String IMAGE_PATH = IMAGE_FOLDER_STRING
			+ "dotTokiIcon.png";

	private static final int MIN_FLOWERS_PER_SECOND = 1;
	private static final int MAX_FLOWERS_PER_SECOND = 60;

	private static int flowerBornPerSecond = 20;

	private Texture flowerTexture;
	private float wind = 0f;
	private float maxWind = 0f;
	private static final float V_WIND = 2;

	public FlowerStormScene() {
		BackGroundColor.WHITE.set();
		flowerTexture = new TextureLoader().loadTexture(IMAGE_PATH);
	}

	@Override
	public void update() {
		processInput();
		updateWind();
		addFlowerIfNecessary();

		for (Iterator<GameObject> ite = getIterator(); ite.hasNext();) {
			FlowerCharacter flower = (FlowerCharacter) ite.next();
			flower.update(wind);
			if (flower.canDispose()) {
				ite.remove();
			}
		}
	}

	private void addFlowerIfNecessary() {
		if (FPSManager.totalFrame() % (FPS / flowerBornPerSecond) == 0) {
			add(new FlowerCharacter(flowerTexture));
		}
	}

	private void updateWind() {
		if ((maxWind < 0 && wind < maxWind) || (0 < maxWind && maxWind < wind)) {
			wind += -maxWind / (FPS * 2);
		} else {
			wind += maxWind / (FPS * 2);
		}
	}

	@Override
	protected void processInput() {
		if (Key.UP.isPressing()) {
			flowerBornPerSecond = min(flowerBornPerSecond + 1,
					MAX_FLOWERS_PER_SECOND);
		}
		if (Key.DOWN.isPressing()) {
			flowerBornPerSecond = max(flowerBornPerSecond - 1,
					MIN_FLOWERS_PER_SECOND);
		}
		if (Key.LEFT.isPressed()) {
			maxWind -= V_WIND;
		}
		if (Key.RIGHT.isPressed()) {
			maxWind += V_WIND;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		flowerTexture.dispose();
	}
}
