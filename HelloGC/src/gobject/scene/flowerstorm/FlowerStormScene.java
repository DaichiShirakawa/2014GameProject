package gobject.scene.flowerstorm;

import static common.Commons.*;
import static java.lang.Math.*;
import gobject.GameObject;
import gobject.scene.GameSceneImpl;

import java.util.Iterator;

import main.FPSManager;
import texture.Texture;
import texture.TextureLoader;

import common.CommonMethod.BackGroundColor;
import common.Commons.KEY;

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
			Flower flower = (Flower) ite.next();
			flower.update(wind);
			if (flower.canDispose()) {
				ite.remove();
			}
		}
	}

	private void addFlowerIfNecessary() {
		if (FPSManager.getFramesUntilStart() % (FPS / flowerBornPerSecond) == 0) {
			add(new Flower(flowerTexture));
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
		if (KEYBOARD.isPressing(KEY.UP)) {
			flowerBornPerSecond = min(flowerBornPerSecond + 1,
					MAX_FLOWERS_PER_SECOND);
		}
		if (KEYBOARD.isPressing(KEY.DOWN)) {
			flowerBornPerSecond = max(flowerBornPerSecond - 1,
					MIN_FLOWERS_PER_SECOND);
		}
		if (KEYBOARD.isPressed(KEY.LEFT)) {
			maxWind -= V_WIND;
		}
		if (KEYBOARD.isPressed(KEY.RIGHT)) {
			maxWind += V_WIND;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		flowerTexture.dispose();
	}
}
