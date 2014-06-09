package scenes.edf.stage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import scenes.edf.EDFScene;
import scenes.edf.characters.enemies.EDFEnemyBase;
import classes.GameObjectImpl;
import classes.character.shooting.ShootingCharacter;
import common.LR;

public abstract class EDFStageBase extends GameObjectImpl {
	private EDFScene scene;
	private long stageFrame = 0;
	private List<EDFSpawnData> spawns = new LinkedList<>();
	private List<ShootingCharacter> spawnedEnemies = new LinkedList<>();

	public EDFStageBase(EDFScene parentScene) {
		this.scene = parentScene;
		createSpawns();
	}

	protected abstract void createSpawns();

	protected final void addSpawn(Class<? extends EDFEnemyBase> enemyClass,
			int stageFrame, float angle, LR lr) {
		spawns.add(new EDFSpawnData(enemyClass, stageFrame, angle, lr));
	}

	@Override
	public boolean updateProcess() {
		for (Iterator<EDFSpawnData> ite = spawns.iterator(); ite.hasNext();) {
			EDFSpawnData data = ite.next();
			if (data.canSpawn(stageFrame)) {
				doSpawn(data);
				ite.remove();
			}
		}
		stageFrame++;
		return true;
	}

	private void doSpawn(EDFSpawnData spawnData) {
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
}
