import common.context.SContext
import common.models.IdModel
import common.models.QuestionModel
import kotlinx.datetime.Clock
import org.stackoverflow.openapi.models.CreatableQuestion
import org.stackoverflow.openapi.models.CreateQuestionRequest
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MappingTest {

    @Test
    fun createUserQueryMappingTest() {
        val query = CreateQuestionRequest(
            requestId = "12345",
            createQuestion = CreatableQuestion(
                title = "Ivan",
                text = "ivan@mail.ru",
            )
        )
        val context = SContext().fromTransport(query)
        assertEquals("Ivan", context.questionRequest.title)
        assertEquals("ivan@mail.ru", context.questionRequest.text)
    }

    @Test
    fun createUserResponseMappingTest() {
        val context = SContext(
            questionResponse = QuestionModel(
                id = IdModel("11111111-1111-1111-1111-111111111id1"),
                title = "Ivan",
                text = "ivan@mail.ru",
                ownerId = "1",
                creationDate = Clock.System.now(),
                rating = "100"
            ),
        )
        val response = context.toTransportQuestion() as QuestionModel
        assertEquals("Ivan", response.text)
        assertEquals("ivan@mail.ru", response.title)
    }
}
