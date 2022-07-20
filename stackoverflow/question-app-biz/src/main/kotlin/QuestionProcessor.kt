import com.crowdproj.kotlin.cor.handlers.chain
import com.crowdproj.kotlin.cor.handlers.worker
import com.crowdproj.kotlin.cor.rootChain
import common.context.SContext
import common.models.IdModel
import common.models.SCommand
import general.initStatus
import general.operation
import stubs.*
import validation.*

class QuestionProcessor {

    suspend fun exec(ctx: SContext) = BuzinessChain.exec(ctx)

    companion object {
        private val BuzinessChain = rootChain<SContext> {
            initStatus("Инициализация статуса")

            // ok
            operation("Создание вопроса", SCommand.CREATE) {
                stubs("Обработка стабов") {
                    stubCreateSuccess("Имитация успешной обработки")
                    stubValidationBadTitle("Имитация ошибки валидации заголовка")
                    stubValidationBadDescription("Имитация ошибки валидации описание")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                chain {
                    title = "Валидация запроса"
                    worker("Копируем поля в questionValidating") { questionValidating = questionRequest.copy() }
                    worker("Очистка заголовка") { questionValidating.title = questionValidating.title.trim() }
                    worker("Очистка описания") { questionValidating.text = questionValidating.text.trim() }
                    // validate all fields for html TAGs to avoid
                    validateTitleNotEmpty("Проверка на непустой заголовок")
                    validateDescriptionNotEmpty("Проверка на непустое описание")

                    finishQuestionValidation("Успешное завершение процедуры валидации")
                }
            }

            // ok
            operation("Получить объявление", SCommand.READ) {
                stubs("Обработка стабов") {
                    stubReadSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                chain {
                    title = "Валидация запроса"
                    worker("Копируем поля в questionIdValidating") { questionIdValidating = questionRequestId }
                    validateOnlyIdNotEmpty("Проверка на непустой id")

                    finishQuestionValidation("Успешное завершение процедуры валидации")
                }
            }

            // ok
            operation("Изменить объявление", SCommand.UPDATE) {
                stubs("Обработка стабов") {
                    stubUpdateSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubValidationBadTitle("Имитация ошибки валидации заголовка")
                    stubValidationBadDescription("Имитация ошибки валидации описания")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                chain {
                    title = "Валидация запроса"
                    worker("Копируем поля в adValidating") { questionValidating = questionRequest.copy() }
                    worker("Очистка id") { questionValidating.id = IdModel(questionValidating.id.asString().trim()) }
                    worker("Очистка заголовка") { questionValidating.title = questionValidating.title.trim() }
                    worker("Очистка описания") { questionValidating.text = questionValidating.text.trim() }
                    validateIdNotEmpty("Проверка на непустой id")
                    validateTitleNotEmpty("Проверка на непустой заголовок")
                    validateDescriptionNotEmpty("Проверка на непустое описание")

                    finishQuestionValidation("Успешное завершение процедуры валидации")
                }
            }

            // ok
            operation("Удалить объявление", SCommand.DELETE) {
                stubs("Обработка стабов") {
                    stubDeleteSuccess("Имитация успешной обработки")
                    stubValidationBadId("Имитация ошибки валидации id")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                chain {
                    title = "Валидация запроса"
                    worker("Копируем поля в questionIdValidating") { questionIdValidating = questionRequestId }
                    validateOnlyIdNotEmpty("Проверка на непустой id")

                    finishQuestionValidation("Успешное завершение процедуры валидации")
                }
            }

            // ok
            operation("Поиск объявлений", SCommand.SEARCH) {
                stubs("Обработка стабов") {
                    stubSearchSuccess("Имитация успешной обработки")
                    stubDbError("Имитация ошибки работы с БД")
                    stubNoCase("Ошибка: запрошенный стаб недопустим")
                }
                chain {
                    title = "Валидация запроса"
                    finishQuestionValidation("Успешное завершение процедуры валидации")
                }
            }
        }.build()
    }
}