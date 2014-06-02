package classes.scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import scenes.shootingtest.ShootingBulletCharacterImpl;
import classes.GameObject;
import classes.character.ShootingCharacter;
import classes.character.ShootingCharacter.DIVISION;

abstract public class ShootingScene extends GameSceneImpl {
	private List<ShootingCharacter> friendlieCharas = new ArrayList<>();
	private List<ShootingCharacter> enemieCharas = new ArrayList<>();
	private List<ShootingBulletCharacterImpl> bullets = new ArrayList<>();
	// update()中はオブジェクトリストをIteratorで回すことになる。
	// update()中に管理下キャラからオブジェクト生成の要請があった場合、
	// すぐにadd()せず一旦ここに格納しておく。
	private List<GameObject> generatingObjects = new ArrayList<>();

	public void shoot(ShootingBulletCharacterImpl bullet) {
		generatingObjects.add(bullet);
	}

	@Override
	public void update() {
		// シューティング用に工夫するのでsuper.update();はしない
		processInput();
		processGenerateObjects();
		processCheckHit();
		processUpdateObjects();
	}

	private void processGenerateObjects() {
		for (GameObject go : generatingObjects) {
			add(go);
		}
		generatingObjects.clear();
	}

	private void processCheckHit() {
		for (ShootingBulletCharacterImpl bullet : bullets) {
			List<ShootingCharacter> targetList;
			switch (bullet.getDivision()) {
			case FRIENDLY:
				targetList = enemieCharas;
				break;
			case ENEMY:
				targetList = friendlieCharas;
				break;
			default:
				continue;
			}
			for (ShootingCharacter target : targetList) {
				if (bullet.checkHit(target)) {
					bullet.hitEffect(target);
				}
			}
		}
	}

	private void processUpdateObjects() {
		for (Iterator<GameObject> ite = getIterator(); ite.hasNext();) {
			GameObject go = ite.next();
			go.update();
			if (go.canDispose()) {
				go.dispose();
				ite.remove();
				friendlieCharas.remove(go);
				enemieCharas.remove(go);
				bullets.remove(go);
				generatingObjects.remove(go); // 実装上意味ないハズだが、念のため
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends GameObject> T add(T go) {
		if (go instanceof ShootingBulletCharacterImpl) {
			return (T) addShootingCharacter((ShootingBulletCharacterImpl) go);
		}
		if (go instanceof ShootingCharacter) {
			return (T) addShootingCharacter((ShootingCharacter) go);
		}
		return super.add(go);
	}

	private <T extends ShootingCharacter> T addShootingCharacter(T go) {
		super.add(go);

		if (go instanceof ShootingBulletCharacterImpl) {
			bullets.add((ShootingBulletCharacterImpl) go);
		} else if (go.getDivision() == DIVISION.FRIENDLY) {
			friendlieCharas.add(go);
		} else if (go.getDivision() == DIVISION.ENEMY) {
			enemieCharas.add(go);
		}

		return go;
	}
}
