package edu.umb.homework2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    // count to keep track of the votes
    val red = MutableLiveData(0)
    val orange = MutableLiveData(0)
    val blue = MutableLiveData(0)
    val green = MutableLiveData(0)
    val total = MutableLiveData(0)

    // set up live data to access the vote count
    val redVotes: LiveData<Int> get() = red
    val orangeVotes: LiveData<Int> get() = orange
    val blueVotes: LiveData<Int> get() = blue
    val greenVotes: LiveData<Int> get() = green

    // take the vote percentages for each color and store them as strings
    val redPercentage = MutableLiveData("0.00%")
    val orangePercentage = MutableLiveData("0.00%")
    val bluePercentage = MutableLiveData("0.00%")
    val greenPercentage = MutableLiveData("0.00%")


    // setup the rankings
    val firstRank = MutableLiveData("1st: ")
    val secondRank = MutableLiveData("2nd: ")
    val thirdRank = MutableLiveData("3rd: ")
    val fourthRank = MutableLiveData("4th: ")

    // vote red is increased by 1 and totals updated
    fun voteRed() {
        red.value = (red.value ?: 0) +1
        updateTotal()
    }
    // vote orange is increased by 1 and totals updated
    fun voteOrange() {
        orange.value = (orange.value ?: 0) +1
        updateTotal()
    }
    // vote blue is increased by 1 and totals updated
    fun voteBlue() {
        blue.value = (blue.value ?: 0) +1
        updateTotal()
    }
    // vote green is increased by 1 and totals updated
    fun voteGreen() {
        green.value = (green.value ?: 0) +1
        updateTotal()
    }

    // update total votes
    fun updateTotal() {
        total.value = (red.value ?: 0) +
                (orange.value ?: 0) +
                (blue.value ?: 0) +
                (green.value ?: 0)
        updatePercent()
        updateRanks()
    }
     // update percentages
    fun updatePercent() {
        val total = total.value ?: 1
         val red = red.value ?: 0
         val orange = orange.value ?: 0
         val blue = blue.value ?: 0
         val green = green.value ?: 0

         // calculate percentages
         val redPer = red/total.toFloat()*100
         val orangePer = orange/total.toFloat()*100
         val bluePer = blue/total.toFloat()*100
         val greenPer = green/total.toFloat()*100

         //update the strings
         redPercentage.value = String.format("%.2f%%", redPer)
         orangePercentage.value = String.format("%.2f%%", orangePer)
         bluePercentage.value = String.format("%.2f%%", bluePer)
         greenPercentage.value = String.format("%.2f%%", greenPer)
    }
    // update ranks
    fun updateRanks(){
        // a map of the percentages of the colors
        val colorPer = mapOf(
            "Red" to (redPercentage.value?.removeSuffix("%")?.toFloat() ?: 0f),
            "Orange" to (orangePercentage.value?.removeSuffix("%")?.toFloat() ?: 0f),
            "Blue" to (bluePercentage.value?.removeSuffix("%")?.toFloat() ?: 0f),
            "Green" to (greenPercentage.value?.removeSuffix("%")?.toFloat() ?: 0f)
        )

        // sort
        val sort = colorPer.entries.sortedByDescending { it.value }

        sort.forEachIndexed { index, entry ->
            when (index) {
                0 -> firstRank.value = "1st: ${entry.key}"
                1 -> secondRank.value = "2nd: ${entry.key}"
                2 -> thirdRank.value = "3rd: ${entry.key}"
                3 -> fourthRank.value = "4th: ${entry.key}"
            }
        }
    }











}

