import common.context.SContext
import org.stackoverflow.openapi.models.IRequest
import org.stackoverflow.openapi.models.IResponse

class ConsumerStrategyV1 : ConsumerStrategy {
    override fun topics(config: AppKafkaConfig): InputOutputTopics {
        return InputOutputTopics(config.kafkaTopicInV1, config.kafkaTopicOutV1)
    }

    override fun serialize(source: SContext): String {
        val response: IResponse = source.toTransportQuestion()
        return apiV1ResponseSerialize(response)
    }

    override fun deserialize(value: String, target: SContext) {
        val request: IRequest = apiV1RequestDeserialize(value)
        target.fromTransport(request)
    }
}