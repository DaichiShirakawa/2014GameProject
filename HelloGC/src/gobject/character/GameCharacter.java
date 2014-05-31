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

	BasePoint getBasePoint();

	GameCharacter setBasePont(BasePoint basePoint);

	float getX();

	int getPixcelX();

	GameCharacter setX(float x);

	float getY();

	int getPixcelY();

	GameCharacter setY(float y);

	float getVx();

	GameCharacter setVx(float vx);

	float getVy();

	GameCharacter setVy(float vy);

	int getWidth();

	GameCharacter setWidth(int width);

	int getHeight();

	GameCharacter setHeight(int height);

	float getScale();

	GameCharacter setScale(float scale);

	float getAngle();

	GameCharacter setAngle(float angle);

	float getAlpha();

	GameCharacter setAlpha(float alpha);

	float getvScale();

	GameCharacter setvScale(float vScale);

	float getvAngle();

	GameCharacter setVAngle(float vAngle);

	float getvAlpha();

	GameCharacter setvAlpha(float vAlpha);

	Texture getTexture();

	GameCharacter setTexture(Texture texture);

	Color getColor();

	GameCharacter setColor(Color textureColor);

	GameCharacter setMoveModeX(MoveMode moveMode);

	GameCharacter setMoveModeY(MoveMode moveMode);

	void setDispose();

	/**
	 * seconds秒経過後に破棄するタイマーをセットする。
	 */
	void disposeAfter(float seconds);

	int getDisposeTimer();

	boolean isEnable();

	void move();

	void draw();

	GameCharacter show();

	GameCharacter hide();
	
	GameCharacter toggleVisible();

	boolean isVisible();

	void enable();

	void disable();

	boolean checkHit(GameCharacter target);

}