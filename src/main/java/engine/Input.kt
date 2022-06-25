package engine

import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.GLFW_RELEASE


object Input {
    private val keyArray :
            BooleanArray =
        BooleanArray(GLFW.GLFW_KEY_LAST)
    private val buttons :
            BooleanArray =
        BooleanArray(GLFW.GLFW_MOUSE_BUTTON_LAST)
    private var mouseX :
            Double = 0.0
    private var mouseY :
            Double = 0.0
    private var scrollX :
            Double = 0.0
    private var scrollY :
            Double = 0.0


    private lateinit var keyboard :
            GLFWKeyCallback
    private lateinit var mouseMove:
            GLFWCursorPosCallback
    private lateinit var mouseButtons :
            GLFWMouseButtonCallback
    private lateinit var mouseScroll :
            GLFWScrollCallback

    @JvmStatic
    fun Input(){
        keyboard = object :GLFWKeyCallback(){
            override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
                keyArray[key] = (action != GLFW_RELEASE)
            }
        }
        mouseMove = object :GLFWCursorPosCallback(){
            override fun invoke(window: Long, xpos: Double, ypos: Double) {
                mouseX = xpos
                mouseY = ypos
            }
        }
        mouseButtons = object : GLFWMouseButtonCallback(){
            override fun invoke(window: Long, button: Int, action: Int, mods: Int) {
                buttons[button] = (action != GLFW_RELEASE)
            }

        }
        mouseScroll = object : GLFWScrollCallback(){
            override fun invoke(window: Long, xoffset: Double, yoffset: Double) {
                scrollX += xoffset;
                scrollY += yoffset;
            }

        }
    }


    @JvmStatic
    fun isKeyDown(key: Int): Boolean{
        return keyArray[key] /*Unresolved reference: keyArray*/
    }



    @JvmStatic
    fun isButtonDown(button: Int): Boolean{
        return buttons[button]
    }

    @JvmStatic
    fun freeInput(){
        keyboard.free()
        mouseButtons.free()
        mouseMove.free()
        mouseScroll.free()
        System.out.println("ATTEMPTED TO FREE INPUT!")
    }


    fun getMouseX():Double{
        return mouseX
    }


    fun getMouseY(): Double{
        return mouseY
    }

    fun getScrollX(): Double {
        return scrollX
    }

    fun getScrollY(): Double {
        return scrollY
    }

    fun getKeyboardCallback(): GLFWKeyCallback{
        return keyboard
    }

    fun getMouseMovement(): GLFWCursorPosCallback{
        return mouseMove
    }
    fun getMouseButtonCallback(): GLFWMouseButtonCallback{
        return mouseButtons
    }
    fun getMouseScrollCallback(): GLFWScrollCallback? {
        return mouseScroll
    }

}