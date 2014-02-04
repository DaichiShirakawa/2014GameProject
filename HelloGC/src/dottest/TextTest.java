package dottest;

import texture.TextTexture;
import gobject.GOCharacter;
import static main.Commons.*;
import static org.lwjgl.opengl.GL11.*;
public class TextTest extends GOCharacter{

	public TextTest() {
		setTexture(new TextTexture().createTextTexture("testてすと"));
		setX(WIDTH / 2);
		setY(HEIGHT / 2);
		setWidth(WIDTH);
		setHeight(HEIGHT);
	}

	@Override
	public void update() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void render() {
		glColor4f(0, 0, 1, 1f);
		draw();
	}

}
