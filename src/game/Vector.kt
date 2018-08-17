package game

data class Vector(var x: Double, var y: Double) {
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    operator fun plus(rhs: Vector): Vector {
        return Vector(x + rhs.x, y + rhs.y);
    }

    operator fun minus(rhs: Vector): Vector {
        return Vector(x - rhs.x, y - rhs.y);
    }

    operator fun times(rhs: Double): Vector {
        return Vector(x * rhs, y * rhs)
    }

    operator fun times(rhs: Int): Vector {
        return Vector(x * rhs, y * rhs)
    }


    operator fun times(rhs: Vector): Double {
        return x * rhs.x + y * rhs.y
    }

    fun magnitude(): Double {
        return Math.sqrt(x * x + y * y)
    }

    fun normalize(): Vector {
        return Vector(x / magnitude(), y / magnitude())
    }

    companion object {
        fun distance(a: Vector, b: Vector): Double {
            return Math.sqrt(Math.pow(b.x - a.x, 2.0) + Math.pow(b.y - a.y, 2.0))
        }
    }
}