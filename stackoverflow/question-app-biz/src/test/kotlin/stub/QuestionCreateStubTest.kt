package stub

import QuestionProcessor
import common.context.SContext
import common.models.*
import common.stub.SStubs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class QuestionCreateStubTest {

    private val processor = QuestionProcessor()
    val id = IdModel("666")
    val title = "title 666"
    val description = "desc 666"

    @Test
    fun create() = runTest {

        val ctx = SContext(
            command = SCommand.CREATE,
            state = SState.NONE,
            workMode = WorkMode.STUB,
            stubCase = SStubs.SUCCESS,
            questionRequest = QuestionModel(
                id = id,
                title = title,
                text = description,
            ),
        )
        processor.exec(ctx)
        assertEquals("666", ctx.questionResponse.id.asString())
        assertEquals(title, ctx.questionResponse.title)
        assertEquals(description, ctx.questionResponse.text)
    }

   @Test
    fun badTitle() = runTest {
        val ctx = SContext(
            command = SCommand.CREATE,
            state = SState.NONE,
            workMode = WorkMode.STUB,
            stubCase = SStubs.BAD_TITLE,
            questionRequest = QuestionModel(
                id = id,
                title = "",
                text = description,
            ),
        )
        processor.exec(ctx)
        assertEquals("title", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badDescription() = runTest {
        val ctx = SContext(
            command = SCommand.CREATE,
            state = SState.NONE,
            workMode = WorkMode.STUB,
            stubCase = SStubs.BAD_DESCRIPTION,
            questionRequest = QuestionModel(
                id = id,
                title = title,
                text = "",
            ),
        )
        processor.exec(ctx)
        assertEquals("description", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = SContext(
            command = SCommand.CREATE,
            state = SState.NONE,
            workMode = WorkMode.STUB,
            stubCase = SStubs.DB_ERROR,
            questionRequest = QuestionModel(
                id = id,
            ),
        )
        processor.exec(ctx)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = SContext(
            command = SCommand.CREATE,
            state = SState.NONE,
            workMode = WorkMode.STUB,
            stubCase = SStubs.BAD_ID,
            questionRequest = QuestionModel(
                id = id,
                title = title,
                text = description,
            ),
        )
        processor.exec(ctx)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
}
