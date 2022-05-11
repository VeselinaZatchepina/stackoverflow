package common.models

class UserModel(
    val id: IdModel = IdModel.NONE,
    val name: String = EMPTY_STRING,
    val email: String = EMPTY_STRING,
    val rating: String = EMPTY_STRING
)