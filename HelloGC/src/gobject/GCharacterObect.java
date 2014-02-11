package gobject;

import static common.CommonLogic.*;
import static common.Commons.*;
import static org.lwjgl.opengl.GL11.*;
import texture.Texture;

public abstract class GCharacterObect implements GameObject {
	private boolean disposeFlag_ = false;
	private float x_;
	private float y_;
	private float vx_;
	private float vy_;
	private int width_;
	private int height_;
	private float scale_ = 1;
	private float angle_ = 0;
	private float alpha_ = 1;
	private Texture texture_Texture;

	/**
	 * 移動モード 無制限 画面内(はみ出る) 画面内(はみ出ない) 画面外に出た時点でdispose
	 */
	protected static final int MOVEMODE_UNLIMITED = 0;
	protected static final int MOVEMODE_LIMITED_DISPLAY = 1;
	protected static final int MOVEMODE_LIMITED_INNER_DISPLAY = 2;
	protected static final int MOVEMODE_DISPOSE_WHEN_FADEOUT = 3;
	private int moveMode_ = MOVEMODE_UNLIMITED;

	// if(keyboard.isPress(KEY_UP)){
	// }
	// if(keyboard.isPress(KEY_DOWN)) {
	// }
	// if(keyboard.isPress(KEY_LEFT)) {
	// }
	// if(keyboard.isPress(KEY_RIGHT)) {
	// }
	// if(keyboard.isPress(KEY_SPACE)) {
	// }

	@Override
	public abstract void update();

	@Override
	public abstract void render();

	@Override
	public void terminate() {
		if (getTexture() != null)
			getTexture().dispose();
	}

	@Override
	public boolean canDispose() {
		return disposeFlag_;
	}

	protected void move() {
		x_ += vx_;
		y_ += vy_;
		switch (moveMode_) {
		case MOVEMODE_LIMITED_DISPLAY:
			if (x_ < 0)
				x_ = 0;
			if (x_ > WIDTH)
				x_ = WIDTH;
			if (y_ < 0)
				y_ = 0;
			if (y_ > HEIGHT)
				y_ = HEIGHT;
			break;
		case MOVEMODE_LIMITED_INNER_DISPLAY:
			if (x_ - width_ / 2 < 0)
				x_ = width_ / 2;
			if (x_ + width_ / 2 > WIDTH)
				x_ = WIDTH - width_ / 2;
			if (y_ - height_ / 2 < 0)
				y_ = height_ / 2;
			if (y_ + height_ / 2 > HEIGHT)
				y_ = HEIGHT - height_ / 2;
			break;
		case MOVEMODE_DISPOSE_WHEN_FADEOUT:
			if (x_ + width_ / 2 < 0 || x_ - width_ / 2 > WIDTH
					|| y_ + height_ / 2 < 0 || y_ - height_ / 2 > HEIGHT)
				setDispose(true);
			break;
		default:
			break;
		}
	}

	protected void draw(Texture texture) {
		// 設定を初期化する
		glLoadIdentity();
		// 原点の指定
		glTranslatef(getX(), getY(), 0);
		// 回転
		glRotatef(getAngle(), 0, 0, 1);

		drawTexture(texture, getWidth(), getHeight());
	}

	protected void draw() {
		draw(getTexture());
	}

	public Texture getTexture() {
		return texture_Texture;
	}

	public void setTexture(Texture texture) {
		this.texture_Texture = texture;
	}

	public float getX() {
		return x_;
	}

	public void setX(float x) {
		this.x_ = x;
	}

	public float getY() {
		return y_;
	}

	public void setY(float y) {
		this.y_ = y;
	}

	public float getVx() {
		return vx_;
	}

	public void setVx(float vx) {
		this.vx_ = vx;
	}

	public float getVy() {
		return vy_;
	}

	public void setVy(float vy) {
		this.vy_ = vy;
	}

	public int getWidth() {
		return (int) (width_ * scale_);
	}

	public void setWidth(int width) {
		this.width_ = width;
	}

	public int getHeight() {
		return (int) (height_ * scale_);
	}

	public void setHeight(int height) {
		this.height_ = height;
	}

	public float getScale() {
		return scale_;
	}

	public void setScale(float scale) {
		this.scale_ = scale;
	}

	public float getAngle() {
		return angle_;
	}

	public void setAngle(float angle) {
		this.angle_ = angle;
	}

	public float getAlpha() {
		return alpha_;
	}

	public void setAlpha(float alpha) {
		this.alpha_ = alpha;
	}

	public void setMoveMode(int moveMode) {
		this.moveMode_ = moveMode;
	}

	public void setDispose(boolean canDispose) {
		disposeFlag_ = canDispose;
	}
}
