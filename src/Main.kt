import kotlin.random.Random
import kotlin.arrayOf as arrayOf

fun main() {
    println(sumOfSeries())
    println(logMinMax())
    val numToCheckOnTwiceEvenable = readln().toInt()
    println(TwiceEvenNumDeterminator().IsTwiceEven(numToCheckOnTwiceEvenable))

    println(maxLengthUniqStr(readln()))
    println(maxInRow(readln().toInt(), readln().toInt()))
}

fun sumOfSeries(): Int {
    val count = readln().toInt()
    var isPlus = true
    var sum = 0

    for (i in 0..<count){
        var number = readln().toInt()
        if (isPlus)
            sum += number
        else
            sum -= number
        isPlus = !isPlus
    }
    return sum
}

fun logMinMax(): String {
    val countRoad = readln().toInt()
    var minSizes = mapOf<Int, Int>()

    for (i in 1..countRoad)
    {
        var minTunnelSize = Int.MAX_VALUE // tunnel size cant be <0
        var countTunnel = readln().toInt()
        for (j in 0..<countTunnel){
            var tunnelSize = readln().toInt()
            if (tunnelSize<minTunnelSize) {
                minTunnelSize = tunnelSize
            }
        }
        minSizes = minSizes.plus(Pair(i, minTunnelSize))
    }
    var minInfo = minSizes.maxBy { size -> size.value }
    return "${minInfo.key} ${minInfo.value}"
}

class TwiceEvenNumDeterminator()
{
    fun IsTwiceEven(num: Int): Boolean {
        val sum = getDigitsSum(num)
        val product = getDigitsMult(num)
        return isEven(sum) and isEven(product)
    }

    private fun getDigitsSum(num: Int): Int {
        var digits = getDigitsArray(num)
        return digits.sum()
    }

    private fun getDigitsMult(num: Int): Int {
        var digits = getDigitsArray(num)
        return digits.reduce(Int::times)
    }

    private fun getDigitsArray(num: Int): List<Int> {
        var digitable = num
        val digits: List<Int> = ArrayList()
        while (digitable>0)
        {
            digits.addLast(digitable % 10)
            digitable /= 10
        }
        return digits
    }

    private fun isEven(num: Int): Boolean {
        return num%2==0
    }
}

fun maxLengthUniqStr(string: String): String {
    var strToSearch = string
    var start = 0
    var substrings: List<String> = ArrayList()
    var biggestUniq = StringBuilder()
    var i=0
    while (i<string.length) {
        if (string[i] !in biggestUniq)
        {
            biggestUniq.append(string[i])
            i++
        }
        else
        {
            substrings.addLast(biggestUniq.toString())
            var offset = biggestUniq.indexOfFirst { char -> char==string[i] }
            start += offset + 1
            biggestUniq.clear()
            i = start
        }
    }
    substrings.addLast(biggestUniq.toString())
    return substrings.maxBy { str -> str.length }
}

fun maxInRow(n: Int, m: Int) : List<Int>{
    val maxes : List<Int> = ArrayList()
    val array = getRandomArrays(n, m)
    println(array.contentDeepToString())
    val a = array.fold(maxes, { a, b -> a.plus(b.max()) })
    return a
}

fun getRandomArrays(n: Int, m: Int): Array<Array<Int>> {
    return Array(n) { Array(m){ Random.nextInt(0,100) } }
}