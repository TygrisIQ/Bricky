package screen

import engine.Input
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWWindowSizeCallback
import org.lwjgl.opengles.GLES
import org.lwjgl.opengles.GLES30

data class Window(
    var Height : Int,
    var Width : Int,
    var Title : String
){
    private var frames: Int = 0
    private var time : Long = 0
    private var window : Long = 0
    private var backgroundR : Float = 0.0F
    private var backgroundG : Float = 0.0F
    private var backgroundB : Float = 0.0F
    private var input = Input
    private var isResized : Boolean = false
    private lateinit var sizeCallback : GLFWWindowSizeCallback

    fun create(){
        if(!GLFW.glfwInit()){
            System.err.println("GLFW WAS NOT INITIALIZED!")
        }
        window = GLFW.glfwCreateWindow(this.Height,
            this.Width, this.Title, 0, 0)
        if (window.equals(0)){
            System.err.println("WINDOW WAS NOT CREATED!!!!!!")
        }
        val videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())
        GLFW.glfwSetWindowPos(window!!, (videoMode!!.width()
                - this.Width)  /2, (videoMode.height()- this.Height / 2))
        GLFW.glfwMakeContextCurrent(window)
        GLES.createCapabilities()
        GLES30.glEnable(GLES30.GL_DEPTH_TEST)

        GLFW.glfwShowWindow(window)
        createCallbacks();
        GLFW.glfwSwapInterval(1)
        time = System.currentTimeMillis()
    }
    private fun createCallbacks() {
        sizeCallback = object : GLFWWindowSizeCallback() {
            override operator fun invoke(window: Long, w: Int, h: Int) {
                Width = w
                Height = h
                isResized = true
            }
        }
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback())
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMovement())
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonCallback())
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback())
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback)
    }

    fun update(){
        if(isResized){
            GLES30.glViewport(0,0,Width, Height)
            isResized = false
        }
        GLES30.glClearColor(backgroundR,
        backgroundG,
        backgroundB, 1.0f)
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT or
        GLES30.GL_DEPTH_BUFFER_BIT)

        GLFW.glfwPollEvents()
        frames++
        if (System.currentTimeMillis() > time + 1000){
            GLFW.glfwSetWindowTitle(
                window, this.
                Title + " | FPS: " + frames)
        }
    }
    fun swapBuffers(){
        GLFW.glfwSwapBuffers(window)
    }
    fun closeRequest() : Boolean{
        return GLFW.glfwWindowShouldClose(window)
    }
    fun setBackgroundColor(r: Float, g: Float, b: Float) {
        backgroundR = r
        backgroundG = g
        backgroundB = b
    }

    fun destroy(){
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}