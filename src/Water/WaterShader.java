package Water;

import org.lwjgl.util.vector.Matrix4f;
import Shaders.ShaderProgram;
import Tools.MatrixMath;
import Entities.Camera;

public class WaterShader extends ShaderProgram {

    private final static String VERTEX_FILE = "src/Water/WaterVertexShader.txt";
    private final static String FRAGMENT_FILE = "src/Water/WaterFragmentShader.txt";

    // Location of the movement and transformation variables in the shader code
    private int location_modelMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;

    // Water necessities
    private int location_reflectionTexture;
    private int location_refractionTexture;

    public WaterShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(0, "position");
    }

    @Override
    protected void getAllUniformLocations() {
        location_projectionMatrix = getUniformLocation("projectionMatrix");
        location_viewMatrix = getUniformLocation("viewMatrix");
        location_modelMatrix = getUniformLocation("modelMatrix");

        // Water necessities
        location_reflectionTexture = getUniformLocation("reflectionTexture");
        location_refractionTexture = getUniformLocation("refractionTexture");
    }

    public void loadProjectionMatrix(Matrix4f projection) {
        loadMatrix(location_projectionMatrix, projection);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = MatrixMath.createViewMatrix(camera);
        loadMatrix(location_viewMatrix, viewMatrix);
    }

    public void connectTextures() {
        super.loadInt(location_reflectionTexture, 0);
        super.loadInt(location_refractionTexture, 1);
    }

    public void loadModelMatrix(Matrix4f modelMatrix){
        loadMatrix(location_modelMatrix, modelMatrix);
    }
}