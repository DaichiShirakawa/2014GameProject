package scenes.edf;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import common.LR;
import scenes.edf.enemies.EDFEnemy;
import classes.character.SimpleCharacter;
import classes.scene.ShootingScene;

public class EDFStage extends SimpleCharacter {
	private ShootingScene parentScene;
	private long frame = 0;
	private List<SpawnData> spawns = new LinkedList<>();
	private List<EDFEnemy> spawnedEnemies = new LinkedList<>();
	{
		spawns.add(new SpawnData(EDFEnemy.class, 60, 0, LR.LEFT));
		spawns.add(new SpawnData(EDFEnemy.class, 60, 0, LR.RIGHT));
		spawns.add(new SpawnData(EDFEnemy.class, 120, 00, LR.LEFT));
		spawns.add(new SpawnData(EDFEnemy.class, 120, 00, LR.RIGHT));
		spawns.add(new SpawnData(EDFEnemy.class, 240, 180, LR.LEFT));
		// spawns.add(new SpawnData(EDFEnemy.class, 240, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 310, 180, LR.LEFT));
		// spawns.add(new SpawnData(EDFEnemy.class, 320, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 330, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 340, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 350, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 360, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 370, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 380, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 390, 180, LR.RIGHT));
		// spawns.add(new SpawnData(EDFEnemy.class, 400, 180, LR.RIGHT));
	}

	public EDFStage(ShootingScene parentScene) {
		this.parentScene = parentScene;
	}

	@Override
	public void update() {
		frame++;
		for (Iterator<SpawnData> ite = spawns.iterator(); ite.hasNext();) {
			SpawnData data = ite.next();
			if (data.canSpawn(frame)) {
				doSpawn(data);
				ite.remove();
			}
		}
	}

	private void doSpawn(SpawnData spawnData) {
		spawnedEnemies.add(parentScene.add(spawnData.spawnTo(parentScene)));
	}

	public boolean isClear() {
		if(!spawns.isEmpty()) {
			return false;
		}
		for (EDFEnemy enemy : spawnedEnemies) {
			if(!enemy.isDisposed()) {
				return false;
			}
		}
		return true; 
	}
}
