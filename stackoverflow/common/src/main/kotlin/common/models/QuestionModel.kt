package common.models

import common.EMPTY_STRING
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class QuestionModel (
    var id: IdModel = IdModel.NONE,
    var title: String = EMPTY_STRING,
    var text: String = EMPTY_STRING,
    var rating: String = EMPTY_STRING,
    var ownerId: String = EMPTY_STRING,
    var creationDate: Instant = Clock.System.now(),
    var status: QuestionStatusModel = QuestionStatusModel.OPENED
)

