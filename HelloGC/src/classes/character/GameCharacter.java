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
public interface GameCharacter extends GameObject {
	/**
	 * フィールドのgetter/setter
	 */

	GameCharacterBasePoint getBasePoint();

	GameCharacter setBasePont(GameCharacterBasePoint basePoint);

	GameCharacter setMoveModeX(GameCharacterMoveMode moveMode);

	GameCharacter setMoveModeY(GameCharacterMoveMode moveMode);

	float getX();

	GameCharacter setX(float x);

	float getY();

	GameCharacter setY(float y);

	float getVX();

	GameCharacter setVx(float vx);

	float getVY();

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

	float getVScale();

	GameCharacter setVScale(float vScale);

	float getVAngle();

	GameCharacter setVAngle(float vAngle);

	float getVAlpha();

	GameCharacter setVAlpha(float vAlpha);

	Texture getTexture();

	GameCharacter setTexture(Texture texture);

	Color getColor();

	GameCharacter setColor(Color textureColor);

	/**
	 * destroy関係
	 */
	// seconds秒経過後にdestroyするタイマーをセット
	void destroyAfter(float seconds);

	int getDestroyTimerFrame();

	boolean checkHit(GameCharacter target);

}