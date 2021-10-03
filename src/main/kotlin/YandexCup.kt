import java.util.*

fun main(){

    val scanner = Scanner(System.`in`)

    val count = scanner.nextInt()
    val maxPeople = scanner.nextInt()
    val maxTime = scanner.nextInt()

    val list = mutableListOf<Int>()

    for (i in 1..count){
        val newZ = scanner.nextInt()
        list.add(newZ)
    }

    val map = mutableMapOf<Int, Int>()

    for (i in list){
        val currentTime = i
        for (j in 0..maxTime){
            val newCurrentTime = i + j
            if (map.containsKey(newCurrentTime)){
                var currentPeople = map[newCurrentTime]
                if (currentPeople != null){
                    var newCurrentPeople = currentPeople + 1
                    map.set(newCurrentTime, newCurrentPeople)
                }
            } else {
                var newCurrentPeople = 1
                map.set(newCurrentTime, newCurrentPeople)
            }
        }
    }

    println(map)

    for (i in list){
        var wasFalse = 0
        var currentStatus = false
        for (j in i..i + maxTime){
            if (map.containsKey(j)){
                if (map[j]!! > maxPeople){
                    println()
                    currentStatus = false
                    wasFalse = 1
                } else{
                    currentStatus = true
                }
            }
        }

        if(currentStatus == false && wasFalse == 1){
            println("NO")
        } else {
            println("YES")
        }
    }

    list.sort()

    for (i in list){
        println("Время записи = $i. Время, которое займёт = с $i по ${i + maxTime}")
    }

    println(list)

}