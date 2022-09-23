import model.hewan

open class kambing(namahewan: String, jenishewan: String, usiahewan: Int, imageuri: String):
    hewan(namahewan, jenishewan, usiahewan, imageuri) {
    override fun interaksi():String {
        return "MBEEE MBEEEE"
    }
}