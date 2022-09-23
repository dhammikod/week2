package model


open class hewan(
    namahewan: String,
    jenishewan: String,
    usiahewan: Int,
    imageuri: String
){
    var namahewan:String = namahewan
    var jenishewan:String = namahewan
    var usiahewan:Int = usiahewan
    var imageuri:String = imageuri

    fun makanan(s: biji): String {
        return "makanan biji"
    }

    fun <Int> makanan(i: rumput): String {
        return "makanan rumput"
    }

    open fun interaksi():String {
        return ""
    }
}