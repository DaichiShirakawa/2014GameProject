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
	 * seconds秒後に破棄するタイマーをセットする。
	 * @param seconds
	 */
	void destroyAfter(float seconds);

	/**
	 * 破棄タイマーがセットされていれば、
	 * 破棄されるまでの残りフレーム数を返す。
	 * タイマーがセットされていなければ-1を返す。
	 * @return 破棄されるまでの残りフレーム数
	 */
	int getDestroyTimerFrame();

	/**
	 * targetと自分が接触しているかの判定。
	 * デフォルトではx, yを中心点とした横width縦heightの矩形で判定する。
	 * @return
	 */
	boolean checkHit(GameCharacter target);

}