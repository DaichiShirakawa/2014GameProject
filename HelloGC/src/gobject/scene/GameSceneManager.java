package gobject.scene;

import gobject.scene.flowerstorm.FlowerStormScene;
import gobject.scene.shooting.edf.EDFScene;
import gobject.scene.shooting.test.ShootingTestScene;
import gobject.scene.solarsystem.SolarSystemScene;
import gobject.scene.text.TestScene;
import gobject.scene.title.TitleScene;
import io.Key;

/**
 * 各シーンの切り替えを担当するシングルトン
 * 
 * @author shirakawa
 * 
 */
public class GameSceneManager extends GameSceneImpl {
	private static GameSceneManager instance;

	private GameScene currentScene;

	private GameSceneManager() {
		// 隠蔽
		currentScene = new TitleScene();
	}

	public static GameSceneManager getInstance() {
		if (null == instance) {
			instance = new GameSceneManager();
		}
		return instance;
	}

	@Override
	public void update() {
		changeSceneIfNotNull(SceneCollection.scanChangeScene());
		currentScene.update();
	}

	public void changeSceneIfNotNull(GameScene newScene) {
		if (newScene == null) {
			return;
		}
		currentScene.dispose();
		currentScene = newScene;
	}

	public void changeSceneIfNotNull(SceneCollection nextscene) {
		changeSceneIfNotNull(nextscene.newInstance());
	}

	@Override
	public void render() {
		currentScene.render();
	}

	public enum SceneCollection {
		TITLE(Key.ESCAPE, TitleScene.class),
		TEST(Key.F1, TestScene.class),
		FLOWER_STORM(Key.F2, FlowerStormScene.class),
		SOLAR_SYSTEM(Key.F3, SolarSystemScene.class),
		SHOOTING_TEST(Key.F4, ShootingTestScene.class),
		EDF(Key.F5, EDFScene.class);

		private final Key trigger;
		private Class<? extends GameScene> sceneClass;

		private SceneCollection(Key trigger,
				Class<? extends GameScene> callClass) {
			this.trigger = trigger;
			this.sceneClass = callClass;
		}

		private  GameScene newInstance() {
			try {
				return sceneClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				System.err.println(sceneClass.getName()
						+ " クラスのインスタンス生成でエラーが発生しました。");
				e.printStackTrace();
				return null;
			}
		}

		public static GameScene scanChangeScene() {
			for (SceneCollection scene : SceneCollection.values()) {
				if (scene.trigger.isPressed()) {
					return scene.newInstance();
				}
			}
			return null;
		}
	}
}
