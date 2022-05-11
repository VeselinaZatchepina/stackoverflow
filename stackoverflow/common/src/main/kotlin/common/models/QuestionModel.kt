package common.models

import java.time.Instant

data class QuestionModel (
    val id: IdModel = IdModel.NONE,
    val text: String = EMPTY_STRING,
    val rating: String = EMPTY_STRING,
    val creationDate: Instant = Instant.now(),
    val status: QuestionStatusModel = QuestionStatusModel.NONE
)

