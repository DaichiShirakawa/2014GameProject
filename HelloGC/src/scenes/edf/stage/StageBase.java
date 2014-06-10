package scenes.edf.stage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import scenes.edf.EDFScene;
import scenes.edf.characters.enemies.EDFEnemyBase;
import scenes.edf.characters.enemies.EDFEnemyBoss;
import scenes.edf.characters.enemies.EDFEnemyRotate;
import scenes.edf.characters.enemies.EDFEnemyStandard;
import scenes.edf.characters.enemies.EDFEnemyToki;
import classes.GameObjectImpl;
import classes.character.shooting.ShootingCharacter;
import common.LR;

abstract class StageBase extends GameObjectImpl {
	private EDFScene scene;
	private long stageFrame = 0;
	private List<SpawnData> spawns = new LinkedList<>();
	private List<ShootingCharacter> spawnedEnemies = new LinkedList<>();

	public StageBase(EDFScene parentScene) {
		this.scene = parentScene;
		createSpawns();
	}

	protected abstract void createSpawns();

	protected final void addSpawn(Class<? extends EDFEnemyBase> enemyClass,
			int stageFrame, float angle, LR lr) {
		spawns.add(new SpawnData(enemyClass, stageFrame, angle, lr));
	}

	@Override
	public boolean updateProcess() {
		for (Iterator<SpawnData> ite = spawns.iterator(); ite.hasNext();) {
			SpawnData data = ite.next();
			if (data.canSpawn(stageFrame)) {
				doSpawn(data);
				ite.remove();
			}
		}
		stageFrame++;
		return true;
	}

	private void doSpawn(SpawnData spawnData) {
		spawnedEnemies.add(scene.add(spawnData.spawnTo(scene)));
	}

	@Override
	protected void renderProcess() {
		return;
	}

	public boolean isClear() {
		if (!spawns.isEmpty()) {
			return false;
		}
		for (ShootingCharacter enemy : spawnedEnemies) {
			if (!enemy.isDestroyed()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 直進敵発生
	 */
	protected void standardSpawn(int frame, float angle) {
		addSpawn(EDFEnemyStandard.class, frame, angle, LR.LEFT);
	}

	/**
	 * 回転敵発生
	 */
	protected void rotateSpawn(int frame, float angle, LR lr) {
		addSpawn(EDFEnemyRotate.class, frame, angle, lr);
	}
	
	/**
	 * 中ボス発生
	 */
	protected void tokiSpawn(int frame, float angle, LR lr) {
		addSpawn(EDFEnemyToki.class, frame, angle, lr);
	}
	
	/**
	 * ボス発生
	 */
	protected void bossSpawn(int frame, float angle, LR lr) {
		addSpawn(EDFEnemyBoss.class, frame, angle, lr);
	}
}
