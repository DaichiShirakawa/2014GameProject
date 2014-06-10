package shirakawa.game.scenes.tokishooting.gui.moneycaption;

import static shirakawa.game.common.Commons.*;

import java.awt.Color;

import shirakawa.game.classes.character.TextCharacter;
import shirakawa.game.scenes.tokishooting.TokiShootingScene;

/**
 * 所持金を表示
 * 
 * @author shirakawa
 *
 */
public class TSMoneyCaption extends TextCharacter {
	TokiShootingScene scene;

	public TSMoneyCaption(TokiShootingScene edfScene) {
		super(getCaption(edfScene.getMoney()));
		this.scene = edfScene;
		setScale(0.4f);
		setX(CENTER_X);
		setY(30);
		setColor(Color.orange.brighter());
	}

	private static String getCaption(int money) {
		return "$ " + money;
	}

	@Override
	protected boolean updateProcess() {
		updateText(getCaption(scene.getMoney()));
		return super.updateProcess();
	}

}