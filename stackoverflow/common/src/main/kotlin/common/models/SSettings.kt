package common.models

import common.repo.IQuestionRepository


data class SSettings(
    val repoStub: IQuestionRepository = IQuestionRepository.NONE,
    val repoTest: IQuestionRepository = IQuestionRepository.NONE,
    val repoProd: IQuestionRepository = IQuestionRepository.NONE,
)
