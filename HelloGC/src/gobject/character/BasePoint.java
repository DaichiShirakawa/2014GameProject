package gobject.character;

public enum BasePoint {
	CENTER {
		@Override
		public int getX(int x, int width) {
			return x;
		}

		@Override
		public int getY(int y, int height) {
			return y;
		}
	},
	LEFTTOP {
		@Override
		public int getX(int x, int width) {
			return x + (width / 2);
		}

		@Override
		public int getY(int y, int height) {
			return y - (height / 2);
		}
	},
	LEFTBOTTOM {
		@Override
		public int getX(int x, int width) {
			return x + (width / 2);
		}

		@Override
		public int getY(int y, int height) {
			return y + (height / 2);
		}
	};
	abstract public int getX(int x, int width);

	abstract public int getY(int y, int height);
}
