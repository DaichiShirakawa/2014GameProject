package gobject;

import static org.lwjgl.opengl.GL11.*;
import texture.Texture;

public abstract class GOCharacter implements GameObject {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private int width;
	private int height;
	private float scale;
	private float angle;
	private float alpha;
	private Texture texture;

	@Override
	public abstract void update();

	@Override
	public abstract void render();

	protected void draw(Texture texture) {
		// xy原点の指定
		glTranslatef(getX(), getY(), 0);
		// 回転
		glRotatef(getAngle(), 0, 0, 1);

		getTexture().bind();
		
		// 四角のポリゴンとする
		glBegin(GL_QUADS);

		texture.point(texture.getWidth(), 0);
		glVertex3f(getWidth() / 2, getHeight() / 2, 0);
		texture.point(0, 0);
		glVertex3f(-getWidth() / 2, getHeight() / 2, 0);
		texture.point(0, texture.getHeight());
		glVertex3f(-getWidth() / 2, -getHeight() / 2, 0);
		texture.point(texture.getWidth(), texture.getHeight());
		glVertex3f(getWidth() / 2, -getHeight() / 2, 0);

		glEnd();
	}

	protected void draw() {
		draw(getTexture());
	}

	@Override
	public void terminate() {
		//
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public int getWidth() {
		return (int) (width * scale);
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return (int) (height * scale);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
}
