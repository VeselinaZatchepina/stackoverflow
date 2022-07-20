import common.context.SContext
import common.models.SError
import common.models.SSettings
import common.models.WorkMode
import common.stub.SStubs

class QuestionService(
    val settings: SSettings = SSettings()
) {

    private val processor = QuestionProcessor(settings)

    suspend fun exec(context: SContext) = processor.exec(context)

    suspend fun createAd(context: SContext) = processor.exec(context)
    suspend fun readAd(context: SContext) = processor.exec(context)
    suspend fun updateAd(context: SContext) = processor.exec(context)
    suspend fun deleteAd(context: SContext) = processor.exec(context)
    suspend fun searchAd(context: SContext) = processor.exec(context)
}