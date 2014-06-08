package scenes.edf.stage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import scenes.edf.EDFScene;
import scenes.edf.enemies.EDFEnemy;
import classes.GameObjectImpl;
import classes.character.shooting.ShootingCharacter;
import common.LR;

public abstract class EDFStageBase extends GameObjectImpl {
	private EDFScene scene;
	private long stageFrame = 0;
	private List<SpawnData> spawns = new LinkedList<>();
	private List<ShootingCharacter> spawnedEnemies = new LinkedList<>();

	public EDFStageBase(EDFScene parentScene) {
		this.scene = parentScene;
		createSpawns();
	}

	protected abstract void createSpawns();

	protected final void addSpawn(Class<? extends EDFEnemy> enemyClass,
			int stageFrame, float angle, LR lr) {
		spawns.add(new SpawnData(enemyClass, stageFrame, angle, lr));
	}

	@Override
	public boolean updateProcess() {
		stageFrame++;
		for (Iterator<SpawnData> ite = spawns.iterator(); ite.hasNext();) {
			SpawnData data = ite.next();
			if (data.canSpawn(stageFrame)) {
				doSpawn(data);
				ite.remove();
			}
		}
		return true;
	}

	private void doSpawn(SpawnData spawnData) {
		spawnedEnemies.add(scene.addCharacter(spawnData.spawnTo(scene)));
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
