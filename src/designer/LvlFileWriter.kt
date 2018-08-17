package designer

import game.ProjectileAction
import java.io.BufferedWriter
import java.io.FileWriter
import java.util.*

class LvlFileWriter(private val fileName: String) {

    fun writeMapToFile(actionMap: ArrayList<TreeMap<Long, ArrayList<ProjectileAction>>>) {
        val fw = FileWriter(fileName)
        val bw = BufferedWriter(fw)
        for (tree in actionMap) {
            for (key in TreeSet<Long>(tree.keys)) {
                for (action in tree[key]!!) {
                    bw.write(key.toString() + " " + action.toString())
                    bw.newLine()
                }
            }
            bw.newLine()
        }
        bw.close()
    }
}