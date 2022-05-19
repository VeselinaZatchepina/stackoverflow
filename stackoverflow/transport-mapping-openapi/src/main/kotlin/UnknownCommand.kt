import common.models.SCommand

class UnknownCommand(command: SCommand) : Throwable("Wrong command $command at mapping toTransport stage")