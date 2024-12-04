import kotlinx.serialization.Serializable

@Serializable
data class SubjectResultData (
    val id: Long,
    val name: String,
    val dayOfWeek: Int,
    val startTime: String,
    val endTime: String,
    val classRoom: Int,
    val isLecture: Boolean,
    val teacherId: Long,
    val groupId: Long
)

