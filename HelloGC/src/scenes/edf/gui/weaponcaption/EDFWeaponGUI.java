package scenes.edf.gui.weaponcaption;

import scenes.edf.characters.EDFCharacterController;
import classes.character.GameCharacterImpl;
import common.LR;

/**
 * 左右両方の武器状態を表示するGUI
 * 
 * @author shirakawa
 * 
 */
public class EDFWeaponGUI extends GameCharacterImpl {
	EDFCharacterController scene;

	private OddWeaponGUI leftGUI;
	private OddWeaponGUI rightGUI;

	public EDFWeaponGUI(EDFCharacterController characters) {
		this.scene = characters;
		leftGUI = add(new OddWeaponGUI(characters.getShip(),
				characters.getWeapon(LR.LEFT), LR.LEFT));
		rightGUI = add(new OddWeaponGUI(characters.getShip(),
				characters.getWeapon(LR.RIGHT), LR.RIGHT));
	}

	@Override
	public boolean updateProcess() {
		if (scene.getWeapon(LR.LEFT) != leftGUI.getWeapon()) {
			leftGUI.setWeapon(scene.getWeapon(LR.LEFT));
		}
		if (scene.getWeapon(LR.RIGHT) != rightGUI.getWeapon()) {
			rightGUI.setWeapon(scene.getWeapon(LR.RIGHT));
		}
		return true;
	}

	@Override
	protected boolean canDisposeTexture() {
		return false;
	}
}
