package scenes.flowerstorm;

import static common.Commons.*;
import static java.lang.Math.*;
import io.Key;

import java.awt.Color;
import java.util.Iterator;

import main.FPSManager;
import texture.Texture;
import texture.TextureLoader;
import classes.GameObject;
import classes.character.GameCharacterBasePoint;
import classes.character.TextCharacter;
import classes.scene.GameScene;
import common.CommonMethod;
import common.CommonMethod.BackGroundColor;

public class FlowerStormScene extends GameScene {
	private enum TextureMode {
		FLOWER,
		TOKI,
		RANDOM;

		public TextureMode nextMode() {
			try {
				return values()[ordinal() + 1];
			} catch (ArrayIndexOutOfBoundsException e) {
				return values()[0];
			}
		}
	}

	private static final Texture FLOWER_TEXTURE = TextureLoader.loadTexture(IMAGE_FOLDER_STRING
			+ "flower.png");
	private static final Texture TOKI_TEXTURE = TextureLoader.loadTexture(IMAGE_FOLDER_STRING
			+ "dotTokiIcon.png");

	private static final int MIN_FLOWERS_PER_SECOND = 1;
	private static final int MAX_FLOWERS_PER_SECOND = 60;

	private static int flowerBornPerSecond = 10;

	private TextureMode textureMode = TextureMode.FLOWER;
	private float wind = 0f;
	private float maxWind = 0f;
	private static final float V_WIND = 2;

	private TextCharacter windText;
	private TextCharacter bornCountText;
	private TextCharacter textureModeText;

	public FlowerStormScene() {
		BackGroundColor.WHITE.set();

		windText = add(new TextCharacter());
		windText.setScale(0.3f)
				.setBasePont(GameCharacterBasePoint.LEFTTOP)
				.setX(5)
				.setY(HEIGHT - 5)
				.setColor(Color.black);

		bornCountText = add(new TextCharacter());
		bornCountText.setScale(0.3f)
				.setBasePont(GameCharacterBasePoint.LEFTTOP)
				.setX(5)
				.setY(HEIGHT - 5 - 15)
				.setColor(Color.black);

		textureModeText = add(new TextCharacter());
		textureModeText.setScale(0.3f)
				.setBasePont(GameCharacterBasePoint.LEFTTOP)
				.setX(5)
				.setY(HEIGHT - 5 - 30)
				.setColor(Color.black);

		add(new TextCharacter("input: ↑,↓,→,←,SPACE")).setScale(0.3f)
				.setBasePont(GameCharacterBasePoint.LEFTBOTTOM)
				.setX(5)
				.setY(5)
				.setColor(Color.black);
	}

	@Override
	public boolean updateProcess() {
		updateWind();
		addFlowerIfNecessary();

		for (Iterator<GameObject> ite = getIterator(); ite.hasNext();) {
			GameObject go = ite.next();
			if (go instanceof FlowerCharacter) {
				((FlowerCharacter) go).setWind(wind);
			}
		}
		windText.updateText("wind:" + CommonMethod.floatTo0d0(wind));
		bornCountText.updateText("born/s:" + flowerBornPerSecond);
		textureModeText.updateText("mode:" + textureMode.name());
		return true;
	}

	private void addFlowerIfNecessary() {
		if (FPSManager.totalFrame() % (FPS / flowerBornPerSecond) == 0) {
			add(new FlowerCharacter(getTexture(textureMode)));
		}
	}

	private Texture getTexture(TextureMode textureMode) {
		switch (textureMode) {
		case FLOWER:
			return FLOWER_TEXTURE;
		case TOKI:
			return TOKI_TEXTURE;
		case RANDOM:
			return RANDOM.nextBoolean() ? FLOWER_TEXTURE : TOKI_TEXTURE;
		}
		return null;
	}

	private void updateWind() {
		if ((maxWind < 0 && wind < maxWind) || (0 < maxWind && maxWind < wind)) {
			wind += -maxWind / (FPS * 2);
		} else {
			wind += maxWind / (FPS * 2);
		}
	}

	@Override
	public boolean inputProcess() {
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
		if (Key.SPACE.isPressed()) {
			textureMode = textureMode.nextMode();
		}
		return super.inputProcess();
	}
}
