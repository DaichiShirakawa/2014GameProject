package classes.character;

public enum GameCharacterBasePoint {
	CENTER {
		@Override
		public float getX(float x, int width) {
			return x;
		}

		@Override
		public float getY(float y, int height) {
			return y;
		}
	},
	LEFTTOP {
		@Override
		public float getX(float x, int width) {
			return x + (width / 2);
		}

		@Override
		public float getY(float y, int height) {
			return y - (height / 2);
		}
	},
	LEFTBOTTOM {
		@Override
		public float getX(float x, int width) {
			return x + (width / 2);
		}

		@Override
		public float getY(float y, int height) {
			return y + (height / 2);
		}
	},
	RIGHTTOP {
		@Override
		public float getX(float x, int width) {
			return x - (width / 2);
		}

		@Override
		public float getY(float y, int height) {
			return y - (height / 2);
		}
	};
	abstract public float getX(float x, int width);

	abstract public float getY(float y, int height);
}
