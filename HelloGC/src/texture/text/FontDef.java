package texture.text;

import static common.Commons.*;

public enum FontDef {
	DEFAULT(FONT_RICTY_FILEPATH, 24),
	MSGOTHIC_24(FONT_MSGOTHIC_FILEPATH, 24),
	MSGOTHIC_32(FONT_MSGOTHIC_FILEPATH, 32),
	MSGOTHIC_40(FONT_MSGOTHIC_FILEPATH, 40),
	MSGOTHIC_48(FONT_MSGOTHIC_FILEPATH, 48),
	MSGOTHIC_56(FONT_MSGOTHIC_FILEPATH, 56),
	MSGOTHIC_64(FONT_MSGOTHIC_FILEPATH, 64),
	RICTY_24(FONT_RICTY_FILEPATH, 24);

	public final String filePath;
	public final float size;

	private FontDef(String filePath, float size) {
		this.filePath = filePath;
		this.size = size;
	}
}