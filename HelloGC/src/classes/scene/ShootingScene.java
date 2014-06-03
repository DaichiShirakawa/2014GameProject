package classes.scene;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import classes.GameObject;
import classes.character.shooting.ShootingBulletCharacter;
import classes.character.shooting.ShootingObject;
import classes.character.shooting.ShootingObjectImpl;
import classes.character.shooting.ShootingObjectImpl.TEAM;

abstract public class ShootingScene extends GameSceneImpl {
	private List<ShootingObject> friendlieCharas = new LinkedList<>();
	private List<ShootingObject> enemieCharas = new LinkedList<>();
	private List<ShootingBulletCharacter> bullets = new LinkedList<>();
	// update()中はオブジェクトリストをIteratorで回すことになる。
	// update()中に管理下キャラからオブジェクト生成の要請があった場合、
	// すぐにadd()せず一旦ここに格納しておく。
	private List<GameObject> generatingObjects = new LinkedList<>();

	public void shoot(ShootingBulletCharacter bullet) {
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
		List<ShootingObject> soList = new LinkedList<>();
		soList.addAll(enemieCharas);
		soList.addAll(friendlieCharas);
		soList.addAll(bullets);
		
		for(int i=0; i<soList.size()-1; i++) {
			for(int j=i+1; j<soList.size(); j++) {
				soList.get(i).checkHitAndAction(soList.get(j));
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
		if (go instanceof ShootingBulletCharacter) {
			return (T) addShootingCharacter((ShootingBulletCharacter) go);
		}
		if (go instanceof ShootingObjectImpl) {
			return (T) addShootingCharacter((ShootingObjectImpl) go);
		}
		return super.add(go);
	}

	private <T extends ShootingObject> T addShootingCharacter(T go) {
		super.add(go);

		if (go instanceof ShootingBulletCharacter) {
			bullets.add((ShootingBulletCharacter) go);
		} else if (go.getTeam() == TEAM.FRIEND_TEAM) {
			friendlieCharas.add(go);
		} else if (go.getTeam() == TEAM.ENEMY_TEAM) {
			enemieCharas.add(go);
		}

		return go;
	}
}
