package game

data class ProjectileAction(val origin: Vector, val velocity: Vector, val radius: Int) {
    companion object {
        fun fromAngle(origin: Vector, angle: Double, speed: Double, radius: Int): ProjectileAction {
            val x = Math.cos(Math.toRadians(angle - 90)) * speed
            val y = Math.sin(Math.toRadians(angle + 90)) * speed
            val velocity = Vector(x, y)
            return ProjectileAction(origin, velocity, radius)
        }
    }

    override fun toString(): String {
        val speed = velocity.magnitude()
        val angle = Math.toDegrees(Math.atan2(velocity.x / speed, velocity.y / speed))

        return origin.x.toString() + " " +
                origin.y.toString() + " " +
                angle + " " +
                speed + " " +
                radius
    }
}