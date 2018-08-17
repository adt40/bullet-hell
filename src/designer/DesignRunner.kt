package designer

import game.ProjectileAction
import game.Vector
import java.util.*

fun main(args: Array<String>) {
    val fileName = "lvl3.dat"
    val writer = LvlFileWriter(fileName)

    val circle = ProjectileActionGroupBuilder.directedArc(
            time = Pair(0, 5000),
            origin = Vector(300, 50),
            angle = Pair(-30.0, 30.0),
            speed = Pair(100.0, 400.0),
            arcAngle = Pair(0.0, 2160.0),
            distFromOrigin = Pair(250.0, 250.0),
            radius = 5,
            numBullets = 100)
    val lines = ArrayList<TreeMap<Long, ArrayList<ProjectileAction>>>()
    for (i in 0 until 5) {
        val pos = if(i % 2 == 0) 0 else 50
        lines.add(ProjectileActionGroupBuilder.line(
                time = Pair(i.toLong() * 1000, i.toLong() * 1000),
                position = Pair(Vector(pos, 0), Vector(pos + 550, 0)),
                angle = Pair(0.0, 0.0),
                speed = Pair(200.0, 200.0),
                radius = 6,
                numBullets = 8))
    }

    val lineCircleLeft = ArrayList<TreeMap<Long, ArrayList<ProjectileAction>>>()
    for (i in 0 until 30) {
        val angleOffset = ((i - 15) * -2.4).toDouble()
        lineCircleLeft.add(ProjectileActionGroupBuilder.arc(
                time = Pair(5800 + i.toLong() * 100, 5800 + i.toLong() * 100),
                origin = Vector(300, 20),
                speed = Pair(300.0, 300.0),
                arcAngle = Pair(-60.0, 60.0),
                angleOffset = Pair(angleOffset, angleOffset),
                distFromOrigin = Pair(100.0, 100.0),
                radius = 7,
                numBullets = 8))
    }

    val lineCircleRight = ArrayList<TreeMap<Long, ArrayList<ProjectileAction>>>()
    for (i in 0 until 30) {
        val angleOffset = ((i - 15) * 2.4).toDouble()
        lineCircleRight.add(ProjectileActionGroupBuilder.arc(
                time = Pair(8800 + i.toLong() * 100, 8800 + i.toLong() * 100),
                origin = Vector(300, 20),
                speed = Pair(300.0, 300.0),
                arcAngle = Pair(-60.0, 60.0),
                angleOffset = Pair(angleOffset, angleOffset),
                distFromOrigin = Pair(100.0, 100.0),
                radius = 7,
                numBullets = 8))
    }

    val actionMap = ArrayList<TreeMap<Long, ArrayList<ProjectileAction>>>()
    actionMap.add(circle)
    actionMap.addAll(lines)
    actionMap.addAll(lineCircleLeft)
    actionMap.addAll(lineCircleRight)

    writer.writeMapToFile(actionMap)
}