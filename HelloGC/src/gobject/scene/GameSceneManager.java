package gobject.scene;

import static common.Commons.*;
import gobject.scene.dottest.DotTestScene;
import gobject.scene.flowerstorm.FlowerStormScene;
import gobject.scene.shooting.ShootingScene;
import gobject.scene.solarsystem.SolarSystemScene;
import gobject.scene.texttest.TextTestScene;
import gobject.scene.title.TitleScene;

import common.Commons.KEY;

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
		GameScene newScene = SceneCollector.scanChangeScene();
		if (newScene != null) {
			currentScene.dispose();
			currentScene = newScene;
		}
		currentScene.update();
	}

	@Override
	public void render() {
		currentScene.render();
	}

	public enum SceneCollector {
		TITLE(KEY.ESCAPE, TitleScene.class),
		FLOWER_STORM(KEY.F1, FlowerStormScene.class),
		DOT_TEST(KEY.F2, DotTestScene.class),
		TEXT_TEST(KEY.F3, TextTestScene.class),
		SOLAR_SYSTEM(KEY.F4, SolarSystemScene.class),
		SHOOTING(KEY.F5, ShootingScene.class);

		private final KEY trigger;
		private Class<? extends GameScene> sceneClass;

		private SceneCollector(KEY trigger, Class<? extends GameScene> callClass) {
			this.trigger = trigger;
			this.sceneClass = callClass;
		}

		private GameScene newInstance() {
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
			for (SceneCollector scene : SceneCollector.values()) {
				if (KEYBOARD.isPressed(scene.trigger)) {
					return scene.newInstance();
				}
			}
			return null;
		}
	}
}
