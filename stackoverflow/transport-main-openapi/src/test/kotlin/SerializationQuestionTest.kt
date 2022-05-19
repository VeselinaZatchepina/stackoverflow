import com.fasterxml.jackson.databind.ObjectMapper
import org.stackoverflow.openapi.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializationQuestionTest {
    private val jsonSerializer = ObjectMapper()
    val dto = CreateQuestionRequest(
        requestId = "12345",
        createQuestion = CreatableQuestion(
            text = "text",
            title = "title",
        )
    )

    @Test
    fun productDeserializationTest() {
        val serializedString = jsonSerializer.writeValueAsString(dto)
        val deserializedDto = jsonSerializer.readValue(serializedString, IRequest::class.java)
        assertEquals("text", (deserializedDto as CreateQuestionRequest).createQuestion?.text)
    }
}
