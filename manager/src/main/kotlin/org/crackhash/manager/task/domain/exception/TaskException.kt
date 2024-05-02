package org.crackhash.manager.task.domain.exception

import org.crackhash.manager.util.DomainException

open class TaskException: DomainException {

    constructor(errorCode: String, args: Array<String>, message: String):
            super(".task$errorCode", args, message)

    constructor(errorCode: String, args: Array<String>, message: String, cause: Throwable):
            super(".task$errorCode", args, message, cause)
}