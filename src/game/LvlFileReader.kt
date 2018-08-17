package game

import java.io.BufferedReader
import java.io.FileReader
import java.util.*

class LvlFileReader(private val fileName: String) {

    fun readToActionMap(): TreeMap<Long, ArrayList<ProjectileAction>> {
        val fr = FileReader(fileName)
        val br = BufferedReader(fr)
        val actionMap = TreeMap<Long, ArrayList<ProjectileAction>>()
        br.lines().forEach { line ->
            if (line != "" && line[0] != '#') {
                val split = line.split(' ')
                val time = split[0].toLong()
                val x = split[1].toDouble()
                val y = split[2].toDouble()
                val angle = split[3].toDouble()
                val speed = split[4].toDouble()
                val radius = split[5].toInt()
                val action = ProjectileAction.fromAngle(Vector(x, y), angle, speed, radius)

                if (actionMap[time] == null) {
                    actionMap[time] = ArrayList()
                }

                actionMap[time]!!.add(action)
            }
        }
        br.close()
        return actionMap
    }
}