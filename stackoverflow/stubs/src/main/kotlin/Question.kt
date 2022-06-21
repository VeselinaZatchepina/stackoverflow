import common.NONE
import common.models.IdModel
import common.models.QuestionModel
import common.models.QuestionStatusModel
import kotlinx.datetime.Instant

object Question {

    fun getModel() = stubReady()

    private fun stubReady() = QuestionModel(
        id = IdModel(id = "777"),
        title = "Как купить слона?",
        text = "Хочу купить слона, но не знаю как...",
        ownerId = "888",
        rating = "200",
        status = QuestionStatusModel.OPENED,
        creationDate = Instant.NONE
    )

    fun getModels() = listOf(
        stubReady(),
        stubReady(),
        stubReady(),
    )
}