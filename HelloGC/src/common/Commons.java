package common;

import io.MyKeyboard;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * ゲーム全体に関わる各種定数の定義
 * およびRandom等の汎用オブジェクトを保持
 * 
 * @author shirakawa
 * 
 */
public final class Commons {
	private Commons() {
		// 隠蔽
	}

	public static final String PRODUCT_TITLE = "応募作品";

	/**
	 * ウィンドウ・描写領域
	 */
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 900;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	// public static final int WINDOW_WIDTH = 640;
	// public static final int WINDOW_HEIGHT = 320;
	// public static final int WIDTH = 320;
	// public static final int HEIGHT = 240;
	public static final int DEPTH = 200;

	/**
	 * 汎用座標
	 */
	public static final int CENTER_X = WIDTH / 2;
	public static final int CENTER_Y = HEIGHT / 2;

	/**
	 * リソースへのパス
	 */
	public static final String RESOURCE_FOLDER_STRING = "./resources/";
	public static final String IMAGE_FOLDER_STRING = RESOURCE_FOLDER_STRING
			+ "image/";
	public static final String WINDOWS_NATIVE_FOLDER_STRING = "./native/windows";
	public static final String FONT_MSGOTHIC_FILEPATH = RESOURCE_FOLDER_STRING
			+ "font/MSGOTHIC.TTC";
	public static final String FONT_RICTY_FILEPATH = RESOURCE_FOLDER_STRING
			+ "font/Ricty.ttf";

	/**
	 * FPS
	 */
	public static final int FPS = 60;

	/**
	 * IO
	 */
	public static final MyKeyboard KEYBOARD = MyKeyboard.getInstance();

	/**
	 * ゲーム利用
	 */
	public static final Random RND = new Random(System.nanoTime());

	/**
	 * 文字描写
	 */
	public static final float TRUE_TYPE_FONT_HEIGHT = 56;
	public static final DecimalFormat FORMAT_FOR_FLOAT_0D0 = new DecimalFormat(
			"0.0");

	/**
	 * 各キーの定義
	 * org.lwjgl.input.Keyboardに準拠
	 */
	public enum KEY {
		ESCAPE(1),
		ONE(2),
		TWO(3),
		THREE(4),
		FOUR(5),
		FIVE(6),
		SIX(7),
		SEVEN(8),
		EIGHT(9),
		NINE(10),
		ZERO(11),
		MINUS(12),
		EQUALS(13),
		BACK(14),
		TAB(15),
		Q(16),
		W(17),
		E(18),
		R(19),
		T(20),
		Y(21),
		U(22),
		I(23),
		O(24),
		P(25),
		LBRACKET(26),
		RBRACKET(27),
		RETURN(28),
		LCONTROL(29),
		A(30),
		S(31),
		D(32),
		F(33),
		G(34),
		H(35),
		J(36),
		K(37),
		L(38),
		SEMICOLON(39),
		APOSTROPHE(40),
		GRAVE(41),
		LSHIFT(42),
		BACKSLASH(43),
		Z(44),
		X(45),
		C(46),
		V(47),
		B(48),
		N(49),
		M(50),
		COMMA(51),
		PERIOD(52),
		SLASH(53),
		RSHIFT(54),
		MULTIPLY(55),
		LMENU(56),
		SPACE(57),
		CAPITAL(58),
		F1(59),
		F2(60),
		F3(61),
		F4(62),
		F5(63),
		F6(64),
		F7(65),
		F8(66),
		F9(67),
		F10(68),
		NUMLOCK(69),
		SCROLL(70),
		NUMPAD7(71),
		NUMPAD8(72),
		NUMPAD9(73),
		SUBTRACT(74),
		NUMPAD4(75),
		NUMPAD5(76),
		NUMPAD6(77),
		ADD(78),
		NUMPAD1(79),
		NUMPAD2(80),
		NUMPAD3(81),
		NUMPAD0(82),
		DECIMAL(83),
		F11(87),
		F12(88),
		F13(100),
		F14(101),
		F15(102),
		F16(103),
		F17(104),
		F18(105),
		KANA(112),
		F19(113),
		CONVERT(121),
		NOCONVERT(123),
		YEN(125),
		NUMPADEQUALS(141),
		CIRCUMFLEX(144),
		AT(145),
		COLON(146),
		UNDERLINE(147),
		KANJI(148),
		STOP(149),
		AX(150),
		UNLABELED(151),
		NUMPADENTER(156),
		RCONTROL(157),
		SECTION(167),
		NUMPADCOMMA(179),
		DIVIDE(181),
		SYSRQ(183),
		RMENU(184),
		FUNCTION(196),
		PAUSE(197),
		HOME(199),
		UP(200),
		PRIOR(201),
		LEFT(203),
		RIGHT(205),
		END(207),
		DOWN(208),
		NEXT(209),
		INSERT(210),
		DELETE(211),
		CLEAR(218),
		LMETA(219),
		LWIN(219),
		RMETA(220),
		RWIN(220),
		APPS(221),
		POWER(222),
		SLEEP(223);

		private final int intVal;

		private KEY(int intVal) {
			this.intVal = intVal;
		}

		public static KEY valueOf(int intVal) {
			for (KEY key : KEY.values()) {
				if (key.intVal == intVal) {
					return key;
				}
			}
			return null;
		}

		public int intVal() {
			return intVal;
		}
	}

}
