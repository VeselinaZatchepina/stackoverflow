package common.models

import common.EMPTY_STRING
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class QuestionModel (
    var id: IdModel = IdModel.NONE,
    val title: String = EMPTY_STRING,
    val text: String = EMPTY_STRING,
    val rating: String = EMPTY_STRING,
    val ownerId: String = EMPTY_STRING,
    val creationDate: Instant = Clock.System.now(),
    val status: QuestionStatusModel = QuestionStatusModel.NONE
)

