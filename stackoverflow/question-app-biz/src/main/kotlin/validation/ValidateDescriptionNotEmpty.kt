package validation

import com.crowdproj.kotlin.cor.ICorChainDsl
import com.crowdproj.kotlin.cor.handlers.worker
import common.context.SContext
import common.helpers.fail
import common.helpers.errorValidation

fun ICorChainDsl<SContext>.validateDescriptionNotEmpty(title: String) = worker {
    this.title = title
    on { questionValidating.text.isEmpty() }
    handle {
        fail(
            errorValidation(
            field = "description",
            violationCode = "empty",
            description = "field must not be empty"
        )
        )
    }
}
