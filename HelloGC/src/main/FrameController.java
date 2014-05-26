package main;

import static common.CommonLogic.*;
import static common.Commons.*;

import org.lwjgl.opengl.Display;

public class FrameController {
	private static final long FRAME_PERIOD_NS =  (long) (1.0 / FPS * 1_000_000_000L);
	private static final long INTERVAL_CAP_NS = 1_000_000_000L;
	private long fpsCalcInterval_ns = 0L;
	private long prevFrameTime_ns = 0L;
	private double calculatedFPS = 0.0;

	public void update() {
		fpsCalcInterval_ns += FRAME_PERIOD_NS;
		incFrameCount();

		if (fpsCalcInterval_ns >= INTERVAL_CAP_NS) {
			long currentFrameTime_ns = System.nanoTime();
			long realElapsedTime_ns = currentFrameTime_ns - prevFrameTime_ns;

			calculatedFPS = ((double) (getFrameCount()) / realElapsedTime_ns) * 1_000_000_000L;

			resetFrameCount();
			fpsCalcInterval_ns = 0L;
			prevFrameTime_ns = currentFrameTime_ns;
		}

		Display.update();
		Display.sync(FPS);
		Display.setTitle("FRAME:" + String.valueOf(getFrameCount())
				+ "FPS:" + formatDouble0d0(calculatedFPS));
	}
}
