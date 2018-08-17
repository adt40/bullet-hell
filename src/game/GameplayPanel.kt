package game

import java.awt.Color
import java.awt.Graphics
import java.util.*
import java.util.stream.Collectors
import javax.swing.JPanel

class GameplayPanel(private val fileName: String, windowSize: Vector): JPanel() {

    private val fileReader = LvlFileReader(fileName)
    private val player = Player(windowSize.x.toInt(), windowSize.y.toInt())

    private val activeProjectiles = ArrayList<Projectile>()

    //maps time to a list of actions to be added to activeProjectiles at that time
    //tree map is sorted by key so the first entry in .keys is the smallest time
    private val projectileActionMap = fileReader.readToActionMap()

    private var currentTime: Long = 0
    private var playGame = true

    init {
        isFocusable = true
        background = Color.WHITE
        addKeyListener(player)
        val timer = Timer()
        val deltaTime: Long = 17;
        class Task(): TimerTask() {
            override fun run() {
                if (playGame) {
                    update(deltaTime)
                    currentTime += deltaTime
                }
            }
        }
        timer.schedule(Task(), 500, deltaTime)
    }

    fun update(deltaTime: Long) {

        player.move(deltaTime)

        for (projectile in activeProjectiles) {
            projectile.move(deltaTime)
        }

        //remove projectiles outside the window
        checkBorder()

        //add projectiles that need to be displayed in this frame
        addNewProjectiles(deltaTime)

        checkForCollision()

        repaint()
    }

    private fun checkForCollision() {
        for (projectile in activeProjectiles) {
            if (Vector.distance(projectile.position, player.position) <= projectile.action.radius + player.radius) {
                playGame = false
                println("You lose")
            }
        }
    }

    private fun addNewProjectiles(deltaTime: Long) {
        val keys = TreeSet<Long>(projectileActionMap.keys)
        for (key in keys) {
            if (key > currentTime + deltaTime) {
                //stop checking once we know we are past this frame's possible data
                break
            }
            if (key <= currentTime) {
                val actions = projectileActionMap.remove(key)!!
                val projectiles = actions.stream()
                        .map { action -> Projectile(action) }
                        .collect(Collectors.toList())
                activeProjectiles.addAll(projectiles)
            }
        }
    }

    private fun checkBorder() {
        var i = 0
        while (i < activeProjectiles.size) {
            if (activeProjectiles[i].position.x < -width || activeProjectiles[i].position.y < -height || activeProjectiles[i].position.x > width * 2 || activeProjectiles[i].position.y > height) {
                activeProjectiles.removeAt(i)
            } else {
                i++
            }
        }
    }

    override fun paint(g: Graphics) {
        super.paint(g)
        for (projectile in activeProjectiles) {
            projectile.paint(g)
        }
        player.paint(g)
    }


}