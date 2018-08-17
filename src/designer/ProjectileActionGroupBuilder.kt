package designer

import game.ProjectileAction
import game.Vector
import java.util.*

class ProjectileActionGroupBuilder {
    companion object {
        fun line(time: Pair<Long, Long>,
                 position: Pair<Vector, Vector>,
                 angle: Pair<Double, Double>,
                 speed: Pair<Double, Double>,
                 radius: Int,
                 numBullets: Int)
                : TreeMap<Long, ArrayList<ProjectileAction>> {

            val actionMap = TreeMap<Long, ArrayList<ProjectileAction>>()

            val timeInterp = (time.second - time.first) / numBullets.toDouble()
            val positionInterp = (position.second - position.first) * (1 / numBullets.toDouble())
            val angleInterp = (angle.second - angle.first) / numBullets.toDouble()
            val speedInterp = (speed.second - speed.first) / numBullets.toDouble()

            for (i in 0 until numBullets) {
                val newTime = (time.first + (timeInterp * i)).toLong()
                val newOrigin = position.first + (positionInterp * i)
                val newAngle = angle.first + (angleInterp * i)
                val newSpeed = speed.first + (speedInterp * i)

                val action = ProjectileAction.fromAngle(newOrigin, newAngle, newSpeed, radius)
                if (!actionMap.containsKey(newTime)) {
                    actionMap[newTime] = ArrayList()
                }
                actionMap[newTime]!!.add(action)
            }

            return actionMap
        }

        fun arc(time: Pair<Long, Long>,
                origin: Vector,
                speed: Pair<Double, Double>,
                arcAngle: Pair<Double, Double>,
                angleOffset: Pair<Double, Double>,
                distFromOrigin: Pair<Double, Double>,
                radius: Int,
                numBullets: Int)
                : TreeMap<Long, ArrayList<ProjectileAction>> {

            val actionMap = TreeMap<Long, ArrayList<ProjectileAction>>()

            val timeInterp = (time.second - time.first) / numBullets.toDouble()
            val speedInterp = (speed.second - speed.first) / numBullets.toDouble()
            val arcAngleInterp = (arcAngle.second - arcAngle.first) / numBullets.toDouble()
            val angleOffsetInterp = (angleOffset.second - angleOffset.first) / numBullets.toDouble()
            val distInterp = (distFromOrigin.second - distFromOrigin.first) / numBullets.toDouble()

            for (i in 0 until numBullets) {
                val newTime = (time.first + (timeInterp * i)).toLong()
                val newSpeed = speed.first + (speedInterp * i)
                val newArcAngle = arcAngle.first + (arcAngleInterp * i)
                val newAngleOffset = angleOffset.first + (angleOffsetInterp * i)
                val newDist = distFromOrigin.first + (distInterp * i)

                val x = Math.cos(Math.toRadians(newArcAngle - 90)) * newDist
                val y = Math.sin(Math.toRadians(newArcAngle + 90)) * newDist
                val dist = Vector(x, y)
                val position = origin + dist

                val action = ProjectileAction.fromAngle(position, newArcAngle + newAngleOffset, newSpeed, radius)
                if (!actionMap.containsKey(newTime)) {
                    actionMap[newTime] = ArrayList()
                }
                actionMap[newTime]!!.add(action)
            }

            return actionMap
        }

        fun directedArc(time: Pair<Long, Long>,
                        origin: Vector,
                        angle: Pair<Double, Double>,
                        speed: Pair<Double, Double>,
                        arcAngle: Pair<Double, Double>,
                        distFromOrigin: Pair<Double, Double>,
                        radius: Int,
                        numBullets: Int)
                : TreeMap<Long, ArrayList<ProjectileAction>> {

            val actionMap = TreeMap<Long, ArrayList<ProjectileAction>>()

            val timeInterp = (time.second - time.first) / numBullets.toDouble()
            val angleInterp = (angle.second - angle.first) / numBullets.toDouble()
            val speedInterp = (speed.second - speed.first) / numBullets.toDouble()
            val arcAngleInterp = (arcAngle.second - arcAngle.first) / numBullets.toDouble()
            val distInterp = (distFromOrigin.second - distFromOrigin.first) / numBullets.toDouble()

            for (i in 0 until numBullets) {
                val newTime = (time.first + (timeInterp * i)).toLong()
                val newAngle = angle.first + (angleInterp * i)
                val newSpeed = speed.first + (speedInterp * i)
                val newArcAngle = arcAngle.first + (arcAngleInterp * i)
                val newDist = distFromOrigin.first + (distInterp * i)

                val x = Math.cos(Math.toRadians(newArcAngle - 90)) * newDist
                val y = Math.sin(Math.toRadians(newArcAngle + 90)) * newDist
                val dist = Vector(x, y)
                val position = origin + dist

                val action = ProjectileAction.fromAngle(position, newAngle, newSpeed, radius)
                if (!actionMap.containsKey(newTime)) {
                    actionMap[newTime] = ArrayList()
                }
                actionMap[newTime]!!.add(action)
            }

            return actionMap

        }
    }
}