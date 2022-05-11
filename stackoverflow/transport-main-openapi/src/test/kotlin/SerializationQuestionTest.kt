import com.fasterxml.jackson.databind.ObjectMapper
import org.stackoverflow.openapi.models.BaseMessage
import org.stackoverflow.openapi.models.CreateQuestionRequest
import org.stackoverflow.openapi.models.Question
import org.stackoverflow.openapi.models.QuestionStatus
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializationQuestionTest {
    private val jsonSerializer = ObjectMapper()
    val dto = CreateQuestionRequest(
        requestId = "12345",
        createQuestion = Question(
            text = "text",
            rating = "0",
            creationDate = "",
            status = QuestionStatus.OPEN
        )
    )

    @Test
    fun productDeserializationTest() {
        val serializedString = jsonSerializer.writeValueAsString(dto)
        val deserializedDto = jsonSerializer.readValue(serializedString, BaseMessage::class.java)
        assertEquals(QuestionStatus.OPEN, (deserializedDto as CreateQuestionRequest).createQuestion?.status)
    }
}
