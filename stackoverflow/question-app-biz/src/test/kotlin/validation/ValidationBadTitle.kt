package validation

import QuestionProcessor
import common.context.SContext
import common.models.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@OptIn(ExperimentalCoroutinesApi::class)
fun validationTitleCorrect(command: SCommand, processor: QuestionProcessor) = runTest {
    val ctx = SContext(
        command = command,
        state = SState.NONE,
        workMode = WorkMode.TEST,
        questionRequest = QuestionModel(
            id = IdModel("123"),
            title = "abc",
            text = "abc",
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(SState.FAILING, ctx.state)
    assertEquals("abc", ctx.questionValidated.title)
}