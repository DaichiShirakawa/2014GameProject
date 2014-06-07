package scenes.edf;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import scenes.edf.enemies.EDFEnemy;
import classes.GameObjectImpl;
import classes.character.shooting.ShootingCharacter;

import common.LR;

public class EDFStage extends GameObjectImpl {
	private EDFScene scene;
	private long frame = 0;
	private List<SpawnData> spawns = new LinkedList<>();
	private List<ShootingCharacter> spawnedEnemies = new LinkedList<>();
	{
		spawns.add(new SpawnData(EDFEnemy.class, 60, 0, LR.LEFT));
		spawns.add(new SpawnData(EDFEnemy.class, 60, 0, LR.RIGHT));
		spawns.add(new SpawnData(EDFEnemy.class, 180, 00, LR.LEFT));
		spawns.add(new SpawnData(EDFEnemy.class, 180, 00, LR.RIGHT));
		spawns.add(new SpawnData(EDFEnemy.class, 300, 180, LR.LEFT));
		spawns.add(new SpawnData(EDFEnemy.class, 300, 180, LR.RIGHT));
		spawns.add(new SpawnData(EDFEnemy.class, 420, 180, LR.LEFT));
		spawns.add(new SpawnData(EDFEnemy.class, 420, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 330, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 340, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 350, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 360, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 370, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 380, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 390, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 400, 180, LR.RIGHT));
	}

	public EDFStage(EDFScene parentScene) {
		this.scene = parentScene;
	}

	@Override
	public boolean updateProcess() {
		if(scene.isBreakTime()) {
			return false;
		}
		frame++;
		for (Iterator<SpawnData> ite = spawns.iterator(); ite.hasNext();) {
			SpawnData data = ite.next();
			if (data.canSpawn(frame)) {
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
