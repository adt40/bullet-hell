package game

import javax.swing.JFrame
import javax.swing.WindowConstants

fun main(args: Array<String>) {

    val windowSize = Vector(600.0, 600.0)

    val panel = GameplayPanel("lvl3.dat", windowSize)
    val frame = JFrame()
    frame.contentPane.add(panel)
    frame.setSize(windowSize.x.toInt(), windowSize.y.toInt())
    frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    frame.isVisible = true
}