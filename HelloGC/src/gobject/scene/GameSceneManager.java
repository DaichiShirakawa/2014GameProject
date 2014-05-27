package gobject.scene;

import static common.Commons.*;
import gobject.scene.dottest.DotTest;
import gobject.scene.flowerstorm.FlowerStormManager;
import gobject.scene.shooting.ShootingScene;
import gobject.scene.solarsystem.SolarSystemManager;
import gobject.scene.texttest.TextTestScene;
import gobject.scene.title.TitleScene;

/**
 * 各種シーンの切り替えを担当するシングルトン
 * 
 * @author shirakawa
 *
 */
public class GameSceneManager extends GameSceneImpl {
	private static GameSceneManager instance;
    private GameScene currentScene;
    private enum ControllScene{
    	//TODO クラスを渡す
    	TEST(1, FlowerStormManager.class);
    	private final int triggerKey;
    	private final Class<GameScene> sceneClass; 
    	private ControllScene(Class sceneClass, int triggerKey) {
    		this.triggerKey = triggerKey;
    		this.sceneClass = sceneClass;
    	}
    }

    private void GameSceneManager() {
        //隠蔽
    	currentScene = new TitleScene();
    }
    
    public static GameSceneManager getInstance() {
    	if(instance == null) {
    		instance = new GameSceneManager();
    	}
    	return instance;
    }

    @Override
    public void update() {
        sceneScan();
        currentScene.update();
    }

    private void sceneScan() {
        if (KEYBOARD.isPressed(KEY_F1)) {
            changeScene(new FlowerStormManager());
        } else if (KEYBOARD.isPressed(KEY_F2)) {
            changeScene(new DotTest());
        } else if (KEYBOARD.isPressed(KEY_F3)) {
            changeScene(new TextTestScene());
        } else if (KEYBOARD.isPressed(KEY_F4)) {
            changeScene(new SolarSystemManager());
        } else if (KEYBOARD.isPressed(KEY_F5)) {
            changeScene(ShootingScene.getInstance());
        }
    }

    private void changeScene(GameSceneImpl scene) {
        if (this.currentScene != null) {
            this.currentScene.dispose();
        }
        this.currentScene = scene;
    }

    @Override
    public void render() {
        currentScene.render();
    }
}
