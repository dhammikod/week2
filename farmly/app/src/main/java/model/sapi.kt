import model.hewan

open class sapi(namahewan: String, jenishewan: String, usiahewan: Int, imageuri: String):
    hewan(namahewan, jenishewan, usiahewan, imageuri) {
    override fun interaksi():String {
        return "MOOOOOOOOOOOOO"
    }
}