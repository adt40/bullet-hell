package game

import java.awt.Color
import java.awt.Graphics

class Projectile(val action: ProjectileAction) {

    var position: Vector = action.origin

    fun move(deltaTime: Long) {
        position += action.velocity * (deltaTime.toDouble() / 1000.0)
    }

    fun paint(g: Graphics) {
        val x = (position.x - action.radius).toInt()
        val y = (position.y - action.radius).toInt()
        val size = action.radius * 2
        g.color = Color.RED
        g.fillOval(x, y, size, size)
    }
}