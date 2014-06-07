package classes.scene;

import java.util.List;

import classes.GameObject;
import classes.character.shooting.ShootingObject;

abstract public class ShootingScene extends GameScene {
	@Override
	public boolean updateProcess() {
		checkHit();
		return true;
	}

	private void checkHit() {
		List<GameObject> childrenList = getChildrenCopy();

		for (int i = 0; i < childrenList.size() - 1; i++) {
			for (int j = i + 1; j < childrenList.size(); j++) {
				try {
					ShootingObject so1 = (ShootingObject) childrenList.get(i);
					ShootingObject so2 = (ShootingObject) childrenList.get(j);
					if(!so1.checkHit(so2)){
						continue;
					}
					so1.hitEffectTo(so2);
					so2.hitEffectTo(so1);
				} catch (ClassCastException e) {
					continue;
				}
			}
		}
	}
}
