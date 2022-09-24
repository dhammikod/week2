import model.hewan

open class ayam(namahewan: String, jenishewan: String, usiahewan: Int, imageuri: String, id : Int):
    hewan(namahewan, jenishewan, usiahewan, imageuri, id) {
    override fun interaksi():String {
        return "KOCK KOCK KOCK KOCK"
    }
}