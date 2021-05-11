class Note {
    var id: Long = 0
    var content: String? = null

    internal constructor(
        content: String?
    ) {
        this.content = content
    }

    internal constructor(
        id: Long,
        content: String?
    ) {
        this.id = id
        this.content = content
    }

    internal  constructor()


}