package classes.character;

import java.awt.Color;

import classes.GameObject;
import texture.Texture;

/**
 * キャラクターの挙動を定義
 * 
 * @author shirakawa
 * 
 */
public interface GameCharacterObject extends GameObject {

	GameCharacterBasePoint getBasePoint();

	GameCharacterObject setBasePont(GameCharacterBasePoint basePoint);

	float getX();

	int getPixcelX();

	GameCharacterObject setX(float x);

	float getY();

	int getPixcelY();

	GameCharacterObject setY(float y);

	float getVX();

	GameCharacterObject setVx(float vx);

	float getVY();

	GameCharacterObject setVy(float vy);

	int getWidth();

	GameCharacterObject setWidth(int width);

	int getHeight();

	GameCharacterObject setHeight(int height);

	float getScale();

	GameCharacterObject setScale(float scale);

	float getAngle();

	GameCharacterObject setAngle(float angle);

	float getAlpha();

	GameCharacterObject setAlpha(float alpha);

	float getVScale();

	GameCharacterObject setVScale(float vScale);

	float getVAngle();

	GameCharacterObject setVAngle(float vAngle);

	float getVAlpha();

	GameCharacterObject setVAlpha(float vAlpha);

	Texture getTexture();

	GameCharacterObject setTexture(Texture texture);

	Color getColor();

	GameCharacterObject setColor(Color textureColor);

	GameCharacterObject setMoveModeX(GameCharacterMoveMode moveMode);

	GameCharacterObject setMoveModeY(GameCharacterMoveMode moveMode);

	void setDispose();

	/**
	 * seconds秒経過後に破棄するタイマーをセットする。
	 */
	void disposeAfter(float seconds);
	
	boolean isDisposed();

	int getDisposeTimer();

	boolean isEnable();

	void move();

	void draw();

	GameCharacterObject show();

	GameCharacterObject hide();

	GameCharacterObject toggleVisible();

	boolean isVisible();

	void enable();

	void disable();

	boolean checkHit(GameCharacterObject target);

}