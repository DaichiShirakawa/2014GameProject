package gobject.character;

import gobject.GameObject;

import java.awt.Color;

import texture.Texture;

/**
 * キャラクターの挙動を定義
 * 
 * @author shirakawa
 *
 */
public interface GameCharacter extends GameObject {

	int getX();

	void setX(float x);

	int getY();

	void setY(float y);

	float getVx();

	void setVx(float vx);

	float getVy();

	void setVy(float vy);

	int getWidth();

	void setWidth(int width);

	int getHeight();

	void setHeight(int height);

	float getScale();

	void setScale(float scale);

	float getAngle();

	void setAngle(float angle);

	float getAlpha();

	void setAlpha(float alpha);

	float getvScale();

	void setvScale(float vScale);

	float getvAngle();

	void setvAngle(float vAngle);

	float getvAlpha();

	void setvAlpha(float vAlpha);

	Texture getTexture();

	void setTexture(Texture texture);

	Color getTextureColor();

	void setTextureColor(Color textureColor);

	void setXMoveMode(MoveMode moveMode);

	void setYMoveMode(MoveMode moveMode);

	void setDispose();

	/**
	 * seconds秒経過後に破棄するタイマーをセットする。
	 */
	void disposeAfter(float seconds);

	int getDisposeTimer();

	boolean isEnable();

	void move();

	void draw();

	void draw(Texture texture);

	void show();

	void hide();

	void enable();

	void disable();

	boolean checkHit(GameCharacter target);

}