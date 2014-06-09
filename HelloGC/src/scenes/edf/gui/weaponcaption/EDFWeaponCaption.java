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
public class EDFWeaponCaption extends GameCharacterImpl {
	EDFCharacterController scene;

	private AnWeaponGUI leftGUI;
	private AnWeaponGUI rightGUI;

	public EDFWeaponCaption(EDFCharacterController edfCharacterController) {
		this.scene = edfCharacterController;
		leftGUI = add(new AnWeaponGUI(LR.LEFT,
				edfCharacterController.getLeftWeapon()));
		rightGUI = add(new AnWeaponGUI(LR.RIGHT,
				edfCharacterController.getRightWeapon()));
	}

	@Override
	public boolean updateProcess() {
		if (scene.getLeftWeapon() != leftGUI.getWeapon()) {
			leftGUI.setWeapon(scene.getLeftWeapon());
		}
		if (scene.getRightWeapon() != rightGUI.getWeapon()) {
			rightGUI.setWeapon(scene.getRightWeapon());
		}
		return true;
	}

	@Override
	protected boolean canDisposeTexture() {
		return false;
	}
}
