package gobject.scene.solarsystem;

import static common.Commons.*;
import gobject.GameObject;
import gobject.character.BasePoint;
import gobject.character.GameCharacter;
import gobject.character.star.ShootingStar;
import gobject.character.star.Star;
import gobject.character.text.TextCharacter;
import gobject.scene.GameSceneImpl;

import java.awt.Color;
import java.util.Iterator;

import texture.text.FontDef;
import common.CommonMethod;
import common.CommonMethod.BackGroundColor;
import common.Commons.KEY;

public class SolarSystemScene extends GameSceneImpl {
	private static final int INPUT_INTERVAL = 5;

	private double timeScale = 0.0001f;
	private int speedScale = 1;
	private double passageDays = 0;

	private GameCharacter stateCaption;

	public SolarSystemScene() {
		BackGroundColor.BLACK.set();

		Star parentStar;
		Star sun = new Star(null, "太", 1f, Color.orange, 0, 27.275);

		add(sun);
		add(sun.makeChild("水", 0.4f, 15, new Color(0.5f, 0.5f, 1f), 0.241,
				58.65));
		add(sun.makeChild("金", 0.5f, 30, Color.yellow, 0.615, 243.0187));

		parentStar = sun.makeChild("地", 0.6f, 45, new Color(0.5f, 1f, 0.5f), 1,
				0.997271);
		add(parentStar);

		add(parentStar.makeChild("月", 0.3f, 10, Color.white,
				27.31266666666666666 / (double) 365, 27.31266666666666666));

		parentStar = sun.makeChild("火", 0.5f, 60, Color.red, 1.881, 1.02595);
		add(parentStar);
		add(parentStar.makeChild("フ", 0.3f, 5, Color.white,
				0.3189 / (double) 365, 0.3189));
		add(parentStar.makeChild("ダ", 0.3f, 10, Color.white,
				1.2630 / (double) 365, 1.2630));

		parentStar = sun.makeChild("木", 0.8f, 75, new Color(0.9f, 0.6f, 0.5f),
				11.86, 0.4135);
		add(parentStar);
		add(parentStar.makeChild("メ", 0.3f, 5, Color.white,
				0.2948 / (double) 365, 0.2948));
		add(parentStar.makeChild("ア", 0.3f, 10, Color.white,
				0.2983 / (double) 365, 0.2983));
		add(parentStar.makeChild("マ", 0.3f, 15, Color.white,
				0.4981 / (double) 365, 0.4981));
		add(parentStar.makeChild("テ", 0.3f, 20, Color.white,
				0.6745 / (double) 365, 0.6745));
		add(parentStar.makeChild("イ", 0.3f, 25, Color.white,
				1.7691 / (double) 365, 1.7691));

		parentStar = sun.makeChild("土", 0.7f, 90, new Color(0.9f, 0.7f, 0.5f),
				29.46, 0.4264);
		add(parentStar);
		add(parentStar.makeChild("パ", 0.3f, 5, Color.white,
				0.5750 / (double) 365, 0.5750));
		add(parentStar.makeChild("ア", 0.3f, 10, Color.white,
				0.6020 / (double) 365, 0.6020));
		add(parentStar.makeChild("プ", 0.3f, 15, Color.white,
				0.6130 / (double) 365, 0.6130));
		add(parentStar.makeChild("パ", 0.3f, 20, Color.white,
				0.6290 / (double) 365, 0.6290));
		add(parentStar.makeChild("エ", 0.3f, 25, Color.white,
				0.6940 / (double) 365, 0.6940));

		parentStar = sun.makeChild("天", 0.4f, 105, Color.white, 84.01, 0.7181);
		add(parentStar);
		add(parentStar.makeChild("コ", 0.3f, 5, Color.white,
				0.3350 / (double) 365, 0.3350));
		add(parentStar.makeChild("オ", 0.3f, 10, Color.white,
				0.3760 / (double) 365, 0.3760));
		add(parentStar.makeChild("ビ", 0.3f, 15, Color.white,
				0.4350 / (double) 365, 0.4350));
		add(parentStar.makeChild("ク", 0.3f, 20, Color.white,
				0.4640 / (double) 365, 0.4640));
		add(parentStar.makeChild("デ", 0.3f, 25, Color.white,
				0.4740 / (double) 365, 0.4740));

		parentStar = sun.makeChild("海", 0.4f, 120, Color.blue, 164.79, 0.6712);
		add(parentStar);
		add(parentStar.makeChild("ナ", 0.3f, 5, Color.white,
				0.2960 / (double) 365, 0.2960));
		add(parentStar.makeChild("タ", 0.3f, 10, Color.white,
				0.3120 / (double) 365, 0.3120));
		add(parentStar.makeChild("デ", 0.3f, 15, Color.white,
				0.3330 / (double) 365, 0.3330));
		add(parentStar.makeChild("ガ", 0.3f, 20, Color.white,
				0.4290 / (double) 365, 0.4290));
		add(parentStar.makeChild("リ", 0.3f, 25, Color.white,
				0.5540 / (double) 365, 0.5540));

		stateCaption = new TextCharacter(getStateString(), FontDef.RICTY_48).setBasePont(
				BasePoint.LEFTTOP)
				.setX(5)
				.setY(HEIGHT - 5)
				.setScale(0.25F);
		add(stateCaption);
	}

	@Override
	public void update() {
		processInput();

		for (Iterator<GameObject> ite = getIterator(); ite.hasNext();) {
			GameObject go = ite.next();
			if (go instanceof Star) {
				((Star) go).update(getTimeScale());
			} else {
				go.update();
			}
		}

		passageDays += ((365 * FPS) / (double) 360) * getTimeScale();
		((TextCharacter) stateCaption).updateText(getStateString());

	}

	@Override
	protected void processInput() {
		if (KEYBOARD.getPressingFrameCount(KEY.UP) % INPUT_INTERVAL == 0) {
			speedScale++;
		}
		if (KEYBOARD.getPressingFrameCount(KEY.DOWN) % INPUT_INTERVAL == 0) {
			speedScale--;
		}
		if (KEYBOARD.getPressingFrameCount(KEY.LEFT) % INPUT_INTERVAL == 0) {
			speedScale -= 10;
		}
		if (KEYBOARD.getPressingFrameCount(KEY.RIGHT) % INPUT_INTERVAL == 0) {
			speedScale += 10;
		}
		if (KEYBOARD.isPressed(KEY.SPACE)) {
			speedScale = 0;
		}
		if (KEYBOARD.isPressed(KEY.S)) {
			add(new ShootingStar());
		}
	}

	private String getStateString() {
		return CommonMethod.floatTo0d0(passageDays) + "日経過/speed:" + speedScale;
	}

	private double getTimeScale() {
		return (double) speedScale * timeScale;
	}
}
