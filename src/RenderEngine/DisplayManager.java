package RenderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private static final int WIN_WIDTH = 1280; // Width of the window
    private static final int WIN_HEIGHT = 720; // Height of the window
    private static final int FPS = 120; // FPS Cap/Limit

    public static void createDisplay() {

        //  New context attributes
        ContextAttribs attribs = new ContextAttribs(3,2);
        attribs.withForwardCompatible(true); // Allow forward propagation
        attribs.withProfileCore(true);  // Allow use of the Profile Core

        // Set the Display attributes
        try {
            Display.setDisplayMode(new DisplayMode(WIN_WIDTH, WIN_HEIGHT));
            Display.create(new PixelFormat(), attribs);
        }
        catch (LWJGLException error) {
            error.printStackTrace();
        }

        // Set the viewport of the display (top-left & bottom-right)
        GL11.glViewport(0,0,WIN_WIDTH, WIN_HEIGHT);
    }

    public static void updateDisplay() {
        // Sync at 120 Hz
        Display.sync(FPS);
        Display.update();
    }

    public static void closeDisplay() {
        Display.destroy();
    }
}