import model.hewan

open class kambing(namahewan: String, jenishewan: String, usiahewan: Int, imageuri: String, id : Int):
    hewan(namahewan, jenishewan, usiahewan, imageuri, id) {
    override fun interaksi():String {
        return "MBEEE MBEEEE"
    }
}