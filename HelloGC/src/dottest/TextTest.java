package dottest;

import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import gobject.GOCharacter;
import texture.TextTexture;

public class TextTest extends GOCharacter {

	public TextTest() {
		setX(0);
		setY(0);
		setWidth(200);
		setHeight(30);
		setTexture(new TextTexture().createTextTexture("aasd■てすとn", getWidth(),
				getHeight()));
		// try {
		// setTexture(new TextureLoader().loadTexture(IMAGE_FOLDER_STRING +
		// "tokiIcon.png"));
		// } catch (IOException e) {
		// // TODO 自動生成された catch ブロック
		// e.printStackTrace();
		// }
	}

	@Override
	public void update() {
//		setTexture(new TextTexture().createTextTexture("ときtoki" + frameCount,
//				getWidth(), getHeight()));
	}

	@Override
	public void render() {
		glColor4f(0, 0, 0, 1);
		draw();
	}

}
