package scenes.tokishooting.stage;

import java.lang.reflect.InvocationTargetException;

import scenes.tokishooting.TSScene;

enum StageDef {
	STAGE_01(Stage1.class, "さかな"),
	STAGE_02(Stage2.class, "いか"),
	STAGE_03(Stage3.class, "とき"),
	STAGE_04(Stage4.class, "だいけっしゅう"),
	STAGE_05(Stage5.class, "ぜったい ほうい"),
	STAGE_FINAL(Stage6.class, "しゅくてき"),
	CLEAR(null, null);

	private Class<? extends StageBase> stageClass;
	private String descript;

	private StageDef(Class<? extends StageBase> stageClass, String descript) {
		this.stageClass = stageClass;
		this.descript = descript;
	}

	private StageBase getInstance(TSScene scene) {
		if (stageClass == null) {
			return null;
		}

		try {
			return stageClass.getConstructor(TSScene.class)
					.newInstance(scene);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.err.println("ステージの生成に失敗しました");
			e.printStackTrace();
		}
		return null;
	}

	private static StageDef currentStage = null;
	private static final StageDef FIRST_STAGE = STAGE_01;

	public static StageBase getNextStage(TSScene scene) {
		if (currentStage == null) {
			currentStage = FIRST_STAGE;
			return currentStage.getInstance(scene);
		}

		try {
			currentStage = values()[currentStage.ordinal() + 1];
			return currentStage.getInstance(scene);
		} catch (ArrayIndexOutOfBoundsException e) {
			currentStage = null;
			return null;
		}
	}

	public static String getStageName() {
		if (currentStage == null) {
			return "";
		}
		return currentStage.name();
	}

	public static String getStageDescript() {
		if (currentStage == null) {
			return "";
		}
		return currentStage.descript;
	}

	public static void reset() {
		currentStage = null;
	}

	public static boolean allClear() {
		return currentStage == CLEAR;
	}
}
