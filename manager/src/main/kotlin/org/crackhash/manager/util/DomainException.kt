package org.crackhash.manager.util

open class DomainException: RuntimeException {

    val errorCode: String
    val args: Array<String>
    constructor(errorCode: String, args: Array<String>): super() {
        this.errorCode = "errors$errorCode"
        this.args = args
    }

    constructor(errorCode: String, args: Array<String>, message: String): super(message) {
        this.errorCode = "errors$errorCode"
        this.args = args
    }

    constructor(errorCode: String, args: Array<String>, message: String, cause: Throwable): super(message, cause) {
        this.errorCode = "errors$errorCode"
        this.args = args
    }

    constructor(errorCode: String, args: Array<String>, cause: Throwable): super(cause) {
        this.errorCode = "errors$errorCode"
        this.args = args
    }

    constructor(errorCode: String, args: Array<String>, message: String, cause: Throwable,
                enableSuppression: Boolean, writableStackTrace: Boolean
    ): super(message, cause, enableSuppression, writableStackTrace)
    {
        this.errorCode = "errors$errorCode"
        this.args = args
    }
}