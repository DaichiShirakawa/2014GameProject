package scenes.edf.stage;

import java.lang.reflect.InvocationTargetException;

import scenes.edf.EDFScene;

enum StageDef {
	STAGE_01(EDFStage1.class),
	STAGE_02(EDFStage2.class), 
	CLEAR(null);
	private Class<? extends EDFStageBase> stageClass;

	private StageDef(Class<? extends EDFStageBase> stageClass) {
		this.stageClass = stageClass;
	}

	private EDFStageBase getInstance(EDFScene scene) {
		if(stageClass == null) {
			return null;
		}
		
		try {
			return stageClass.getConstructor(EDFScene.class)
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

	public static EDFStageBase getNextStage(EDFScene scene) {
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

	public static void reset() {
		currentStage = null;
	}

	public static boolean allClear() {
		return currentStage == CLEAR;
	}
}
