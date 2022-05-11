import common.context.SContext
import common.models.CommonErrorModel
import common.models.IError
import common.models.IdModel
import common.models.UserModel
import org.stackoverflow.openapi.models.CreateUserRequest
import org.stackoverflow.openapi.models.User
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MappingTest {

    @Test
    fun createUserQueryMappingTest() {
        val query = CreateUserRequest(
            requestId = "12345",
            createUser = User(
                id = "11111111-1111-1111-1111-111111111id1",
                name = "Ivan",
                email = "ivan@mail.ru",
                rating = "100",
            )
        )
        val context = SContext().setQuery(query)
        assertEquals("12345", context.onRequest)
        assertEquals(query.createUser?.id, context.userRequest.id.asString())
        assertEquals("Ivan", context.userRequest.name)
        assertEquals("ivan@mail.ru", context.userRequest.email)
        assertEquals("100", context.userRequest.rating)
    }

    @Test
    fun createUserResponseMappingTest() {
        val context = SContext(
            onRequest = "12345",
            userResponse = UserModel(
                id = IdModel("11111111-1111-1111-1111-111111111id1"),
                name = "Ivan",
                email = "ivan@mail.ru",
                rating = "100"
            ),
            errors = mutableListOf(CommonErrorModel(level = IError.Level.WARNING)),
        )
        val response = context.toCreateUserResponse()
        assertEquals("12345", response.requestId)
        assertEquals(context.userResponse.id.asString(), response.createdUser?.id)
        assertEquals("Ivan", response.createdUser?.name)
        assertEquals("ivan@mail.ru", response.createdUser?.email)
        assertEquals(1, response.errors?.size)
    }
}
