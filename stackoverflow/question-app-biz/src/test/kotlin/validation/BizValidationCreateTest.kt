package validation

import QuestionProcessor
import common.models.SCommand
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BizValidationCreateTest {

    private val processor = QuestionProcessor()
    private val command = SCommand.CREATE

    @Test fun correctTitle() = validationTitleCorrect(command, processor)

}

