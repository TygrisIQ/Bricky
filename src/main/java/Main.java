import engine.Input;
import engine.data.ConstantsKt;
import engine.data.Vector3f;
import engine.render.Mesh;
import engine.render.Renderer;
import engine.render.Vertex;
import org.lwjgl.glfw.GLFW;
import screen.Window;

public class Main implements Runnable{
    public Thread game;
    public static Window window;
    public static final int WIDTH = ConstantsKt.WINDOW_WIDTH,
            HEIGHT = ConstantsKt.WINDOW_HEIGHT;
    public static Input input;
    public static Renderer renderer;
    public Mesh mesh = new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-0.5f,  0.5f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, 0.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, 0.0f))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    });

    public static void main(String [] arg){
        new Main().start();
        Window window = new Window(2,3,"Ds");
    }
    private void start(){
        game = new Thread(this, "game");
        game.start();
    }
    private void update(){
        window.update();
        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
            System.out.println("X: " + Input.INSTANCE.getMouseX() + ", Y: "
                + Input.INSTANCE.getMouseY());
    }
    private void init(){
        Input.Input();
        window = new Window(WIDTH,HEIGHT,"BRICKY");
        window.setBackgroundColor(1.0f, 0.0f, 0.0f);
        renderer = new Renderer();
        window.create();
        mesh.create();
    }
    private void render(){
        window.swapBuffers();
        renderer.renderMesh(mesh);
    }
    @Override
    public void run() {
        init();
        while (!window.closeRequest() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
            update();
            render();
        }
        Input.freeInput();
        window.destroy();
    }
}
