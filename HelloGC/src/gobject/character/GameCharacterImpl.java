package gobject.character;

import static common.Commons.*;
import static common.CommonMethod.*;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Point;

import texture.Texture;

public abstract class GameCharacterImpl implements GameCharacter {
	private boolean disposeFlag = false;
	private int disposeTimer = -1;
	private boolean enableFlag = true;
	private boolean visible = true;

	private BasePoint basePoint = BasePoint.CENTER;
	private float x;
	private float vx;
	private float y;
	private float vy;
	private int width;
	private int height;
	private float scale = 1;
	private float vScale = 0;
	private float angle = 0;
	private float vAngle = 0;
	private float alpha = 1;
	private float vAlpha = 0;

	private Color color;
	private Texture texture;
	private MoveMode xMoveMode = MoveMode.UNLIMITED;
	private MoveMode yMoveMode = MoveMode.UNLIMITED;

	@Override
	public void update() {
		inputProcess();

		if (disposeTimer > 0) {
			disposeTimer--;
		}
		if (disposeTimer == 0) {
			setDispose();
		}
		if (!isEnable()) {
			return;
		}

		scale += vScale;
		angle += vAngle;
		alpha += vAlpha;
		if (alpha > 1f) {
			alpha = 1f;
			vAlpha = 0f;
		}
		if (alpha < 0f) {
			alpha = 0f;
			vAlpha = 0f;
		}
		move();
	}

	protected void inputProcess() {
		// 必要に応じてオーバーライドする
	}

	@Override
	public void move() {
		x = xMoveMode.move(WIDTH, width, x, vx);
		if (xMoveMode == MoveMode.DISPOSE_WITH_FADEOUT
				&& (x + width / 2 < 0 || x - width / 2 > WIDTH)) {
			setDispose();
		}

		y = yMoveMode.move(HEIGHT, height, y, vy);
		if (yMoveMode == MoveMode.DISPOSE_WITH_FADEOUT
				&& (y + height / 2 < 0 || y - height / 2 > HEIGHT)) {
			setDispose();
		}
	}

	@Override
	public void render() {
		if (!isEnable()) {
			return;
		}
		draw();
	}

	@Override
	public void dispose() {
		if (getTexture() != null) {
			getTexture().dispose();
		}
	}

	@Override
	public boolean canDispose() {
		return disposeFlag;
	}

	@Override
	public void draw() {
		if (!isVisible()) {
			return;
		}
		int x = getBasePoint().getX(getPixcelX(), getWidth());
		int y = getBasePoint().getY(getPixcelY(), getHeight());

		glLoadIdentity();
		glTranslatef(x, y, 0);
		glRotatef(getAngle(), 0, 0, 1);
		setGlColor4f(getColor(), getAlpha());

		drawTexture(getTexture(), getWidth(), getHeight());
	}

	@Override
	public Texture getTexture() {
		return texture;
	}

	@Override
	public GameCharacter setTexture(Texture texture) {
		this.texture = texture;
		return this;
	}

	@Override
	public BasePoint getBasePoint() {
		return basePoint;
	}

	@Override
	public GameCharacter setBasePont(BasePoint basePoint) {
		this.basePoint = basePoint;
		return this;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public int getPixcelX() {
		return (int) x;
	}

	@Override
	public GameCharacter setX(float x) {
		this.x = x;
		return this;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public int getPixcelY() {
		return (int) y;
	}

	@Override
	public GameCharacter setY(float y) {
		this.y = y;
		return this;
	}

	@Override
	public float getVx() {
		return vx;
	}

	@Override
	public GameCharacter setVx(float vx) {
		this.vx = vx;
		return this;
	}

	@Override
	public float getVy() {
		return vy;
	}

	@Override
	public GameCharacter setVy(float vy) {
		this.vy = vy;
		return this;
	}

	@Override
	public int getWidth() {
		return (int) (width * scale);
	}

	@Override
	public GameCharacter setWidth(int width) {
		assert (width % 2 == 0) : "widthは偶数でなくてはならない";
		this.width = width;
		return this;
	}

	@Override
	public int getHeight() {
		return (int) (height * scale);
	}

	@Override
	public GameCharacter setHeight(int height) {
		assert (height % 2 == 0) : "heightは偶数でなくてはならない";
		this.height = height;
		return this;
	}

	@Override
	public float getScale() {
		return scale;
	}

	@Override
	public GameCharacter setScale(float scale) {
		this.scale = scale;
		return this;
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public GameCharacter setAngle(float angle) {
		this.angle = angle;
		return this;
	}

	@Override
	public float getAlpha() {
		return alpha;
	}

	@Override
	public GameCharacter setAlpha(float alpha) {
		this.alpha = alpha;
		return this;
	}

	@Override
	public float getvScale() {
		return vScale;
	}

	@Override
	public GameCharacter setvScale(float vScale) {
		this.vScale = vScale;
		return this;
	}

	@Override
	public float getvAngle() {
		return vAngle;
	}

	@Override
	public GameCharacter setVAngle(float vAngle) {
		this.vAngle = vAngle;
		return this;
	}

	@Override
	public float getvAlpha() {
		return vAlpha;
	}

	@Override
	public GameCharacter setvAlpha(float vAlpha) {
		if (vAlpha > 1f) {
			vAlpha = 1f;
		}
		if (vAlpha < 0f) {
			vAlpha = 0f;
		}
		this.vAlpha = vAlpha;
		return this;
	}

	@Override
	public GameCharacter setMoveModeX(MoveMode moveMode) {
		this.xMoveMode = moveMode;
		return this;
	}

	@Override
	public GameCharacter setMoveModeY(MoveMode moveMode) {
		this.yMoveMode = moveMode;
		return this;
	}

	@Override
	public void setDispose() {
		disposeFlag = true;
		disable();
	}

	/**
	 * seconds秒経過後に破棄するタイマーをセットする。
	 */
	@Override
	public void disposeAfter(float seconds) {
		// 現状のロジックでは1/FPS秒程度の誤差がある。
		disposeTimer = (int) (FPS * seconds);
	}

	@Override
	public int getDisposeTimer() {
		return disposeTimer;
	}

	@Override
	public GameCharacter show() {
		visible = true;
		return this;
	}

	@Override
	public GameCharacter hide() {
		visible = false;
		return this;
	}

	@Override
	public GameCharacter toggleVisible() {
		visible = !visible;
		return this;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void enable() {
		enableFlag = true;
	}

	@Override
	public void disable() {
		enableFlag = false;
		hide();
	}

	@Override
	public boolean isEnable() {
		return enableFlag;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public GameCharacter setColor(Color color) {
		this.color = color;
		return this;
	}

	@Override
	public boolean checkHit(GameCharacter target) {
		if (!isEnable()) {
			return false;
		}
		Point selfP1 = new Point(getPixcelX() - getWidth() / 2, getPixcelY()
				+ getHeight() / 2);
		Point selfP2 = new Point(selfP1.x + getWidth(), selfP1.y);
		Point selfP3 = new Point(selfP1.x, selfP1.y - getHeight());

		Point targP1 = new Point(target.getPixcelX() - target.getWidth() / 2,
				target.getPixcelY() + target.getHeight() / 2);
		Point targP2 = new Point(targP1.x + target.getWidth(), targP1.y);
		Point targP3 = new Point(targP1.x, targP1.y - target.getHeight());

		if (selfP2.x >= targP1.x && selfP1.x <= targP2.x) {
			if (selfP3.y <= targP1.y && selfP1.y >= targP3.y) {
				return true;
			}
		}
		return false;
	}
}
