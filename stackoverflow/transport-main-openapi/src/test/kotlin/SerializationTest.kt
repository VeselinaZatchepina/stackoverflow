import com.fasterxml.jackson.databind.ObjectMapper
import org.stackoverflow.openapi.models.Answer
import org.stackoverflow.openapi.models.BaseMessage
import org.stackoverflow.openapi.models.CreateAnswerRequest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationTest {
    private val requestId = "11111111-1111-1111-1111-111111111111"
    private val rating = "0"
    private val createRequest = CreateAnswerRequest(
        requestId = requestId,
        createAnswer = Answer(
            text = "answer",
            rating = rating,
            creationDate = "date"
        )
    )
    private val om = ObjectMapper()

    @Test
    fun serializationTest() {
        val json = om.writeValueAsString(createRequest)
        println(json)
        assertTrue("json must contain discriminator") {
            json.contains(""""messageType":"${createRequest::class.simpleName}"""")
        }
        assertTrue("json must serialize visibility field") {
            json.contains(""""rating":"$rating"""")
        }
        assertTrue("json must serialize messageId field") {
            json.contains(""""requestId":"$requestId"""")
        }
    }

    @Test
    fun deserializeTest() {
        val json = om.writeValueAsString(createRequest)
        val deserialized = om.readValue(json, BaseMessage::class.java) as CreateAnswerRequest

        assertEquals(rating, deserialized.createAnswer?.rating)
        assertEquals(requestId, deserialized.requestId)
    }
}
