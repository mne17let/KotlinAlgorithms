import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    val count = sc.nextLine().toInt()

    val lines = mutableListOf<String>()

    for (i in 1..count){
        lines.add(sc.nextLine())
    }

    val point = sc.nextLine().split(" ")
    val x = point[0].toInt()
    val y = point[1].toInt()

    val viewsAndParents = getViews(lines)

    val views = viewsAndParents.first
    val parents = viewsAndParents.second



    val hitView = hitTest(x, y, views, parents)

    println(hitView?.name ?: "null")
}

fun getViews(lines: List<String>): Pair<MutableList<View>, MutableList<ViewGroup>> {
    val rootLine = lines[0].split(" ")
    val root = ViewGroup(rootLine[0], rootLine[1].toInt(),
        rootLine[2].toInt(), rootLine[3].toInt(), rootLine[4].toInt())
    // rootLine[5] is parent name, which is null for the root view

    val listWithoutRoot = lines.drop(1)

    val listOfViews = mutableListOf<View>()
    listOfViews.add(root)

    val listOfParentsNames = mutableListOf<String>()

    for(i in listWithoutRoot){
        val currentViewLine = i.split(" ")

        val name = currentViewLine[0]
        val left = currentViewLine[1]
        val top = currentViewLine[2]
        val right = currentViewLine[3]
        val bottom = currentViewLine[4]
        val rootView = currentViewLine[5]

        if (rootView != "null"){
            listOfParentsNames.add(rootView)
        }

        val newView = View(name, left.toInt(), top.toInt(), right.toInt(), bottom.toInt(), rootView)

        listOfViews.add(newView)
    }

    val listOfParents = mutableListOf<ViewGroup>()

    for (parent in listOfParentsNames){
        for (view in listOfViews){
            if (parent == view.name){
                listOfParents.add(ViewGroup(view.name, view.left, view.top, view.right, view.bottom))
            }
        }
    }

    for (parent in listOfParents){
        for (child in listOfViews){
            if (parent.name == child.parent){
                parent.addView(child)
            }
        }
    }

    return Pair(listOfViews, listOfParents)
}

fun hitTest(x: Int, y: Int, views: MutableList<View>, parents: MutableList<ViewGroup>) : View? {

    val listOfGood = mutableListOf<View>()

    for (i in views){
        if (i.right > x && i.left < x && i.top < y && i.bottom > y){
            listOfGood.add(i)
        }
    }

    var result: View? = null

    if (listOfGood.isNotEmpty()){
        for (goodView in listOfGood){
            if (goodView !is ViewGroup){
                result = goodView
            }
        }

        if (result == null){
            result = listOfGood.last()
        }
    }


    return result
}

open class View(val name: String, val left: Int, val top: Int, val right: Int, val bottom: Int,
val parent: String? = null)

class ViewGroup(name: String, left: Int, top: Int, right: Int, bottom: Int
) : View(name, left, top, right, bottom) {

    private val children = mutableListOf<View>()

    fun getChildCount() = children.size
    fun getChildAt(index: Int) = children[index]
    fun addView(child: View) = children.add(child)
}