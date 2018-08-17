package game

import java.awt.Color
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.*

class Player(private val windowWidth: Int, private val windowHeight: Int): KeyListener {

    private val speed = 150.0
    val radius = 5
    private val pressedKeys = Arrays.asList(false, false, false, false)
    private var velocity = Vector(0.0, 0.0)
    var position = Vector((windowWidth / 2).toDouble(), (windowHeight - (windowHeight / 8)).toDouble())

    fun move(deltaTime: Long) {
        val delta = velocity * (deltaTime.toDouble() / 1000.0)
        if (position.x + delta.x > 0 && position.y + delta.y > 0 && position.x + delta.x < windowWidth && position.y + delta.y < windowHeight) {
            position += delta
        }
    }

    fun paint(g: Graphics) {
        val x = (position.x - radius).toInt()
        val y = (position.y - radius).toInt()
        val size = radius * 2
        g.color = Color(48, 5, 162)
        g.fillOval(x, y, size, size)
    }

    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent?) {
        if (e != null) {
            when (e.keyCode) {
                KeyEvent.VK_UP, KeyEvent.VK_W -> {
                    if (!pressedKeys[0]) velocity += Vector(0.0, -speed)
                    pressedKeys[0] = true
                }
                KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                    if (!pressedKeys[1]) velocity += Vector(-speed, 0.0)
                    pressedKeys[1] = true
                }
                KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                    if (!pressedKeys[2]) velocity += Vector(0.0, speed)
                    pressedKeys[2] = true
                }
                KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                    if (!pressedKeys[3]) velocity += Vector(speed, 0.0)
                    pressedKeys[3] = true
                }
            }
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        if (e != null) {
            when (e.keyCode) {
                KeyEvent.VK_UP, KeyEvent.VK_W -> {
                    velocity += Vector(0.0, speed)
                    pressedKeys[0] = false
                }
                KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                    velocity += Vector(speed, 0.0)
                    pressedKeys[1] = false
                }
                KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                    velocity += Vector(0.0, -speed)
                    pressedKeys[2] = false
                }
                KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                    velocity += Vector(-speed, 0.0)
                    pressedKeys[3] = false
                }
            }
        }
    }
}