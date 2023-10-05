package space.stanton.technicaltest.brightones.features.post.model

class Post (
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (title != other.title) return false
        if (body != other.body) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + userId
        result = 31 * result + title.hashCode()
        result = 31 * result + body.hashCode()
        return result
    }

    override fun toString(): String {
        return "Post(id=$id, userId=$userId, title='$title', content='$body')"
    }
}