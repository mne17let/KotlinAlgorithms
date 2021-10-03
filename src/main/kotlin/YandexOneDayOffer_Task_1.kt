import java.util.*

fun main() {
    val sc = Scanner(System.`in`)

    val sizeInput = sc.nextLine().split(" ")
    val h = sizeInput[0].toInt()
    val w = sizeInput[1].toInt()

    val horizontalLinesY = sc.nextLine().split(" ").let {
        val array = IntArray(it.size)
        for (i in 0 until it.size) {
            array[i] = it[i].toInt()
        }
        array
    }

    val verticalLinesX = sc.nextLine().split(" ").let {
        val array = IntArray(it.size)
        for (i in 0 until it.size) {
            array[i] = it[i].toInt()
        }
        array
    }


    val area = maxArea(h, w, horizontalLinesY, verticalLinesX);
    println(area);
}

private fun maxArea(h: Int, w: Int, horizontalLinesY: IntArray, verticalLinesX: IntArray): Long {
    val horizSorted = horizontalLinesY.sorted()
    val vertSorted = verticalLinesX.sorted()

    val vert = mutableListOf<Int>()
    vert.add(0)
    vert.addAll(vertSorted)
    vert.add(w)

    val horiz = mutableListOf<Int>()
    horiz.add(0)
    horiz.addAll(horizSorted)
    horiz.add(h)

    var maxHorizDiff = 0L
    var maxVertDiff = 0L

    for (i in 0..horiz.size - 1){
        if (i == 0){
            maxHorizDiff = horiz[i].toLong()
        } else{
            if (horiz[i] - horiz[i - 1] > maxHorizDiff){
                maxHorizDiff = horiz[i].toLong() - horiz[i - 1].toLong()
            }
        }
    }

    for (i in 0..vert.size - 1){
        if (i == 0){
            maxVertDiff = vert[i].toLong()
        } else{
            if (vert[i] - vert[i - 1] > maxVertDiff){
                maxVertDiff = vert[i].toLong() - vert[i - 1].toLong()
            }
        }
    }

    return maxHorizDiff * maxVertDiff
}