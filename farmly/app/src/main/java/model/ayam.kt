import model.hewan

open class ayam(namahewan: String, jenishewan: String, usiahewan: Int, imageuri: String):
    hewan(namahewan, jenishewan, usiahewan, imageuri) {
    override fun interaksi():String {
        return "KOCK KOCK KOCK KOCK"
    }
}