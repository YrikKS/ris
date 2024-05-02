package org.crackhash.manager.task.api.exception

class TaskRequestException(message: String): TaskException(".request", emptyArray(), message)