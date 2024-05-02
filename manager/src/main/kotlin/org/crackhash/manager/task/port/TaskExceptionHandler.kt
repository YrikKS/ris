package org.crackhash.manager.task.port

//@RestControllerAdvice(assignableTypes = [TaskController::class])
//class TaskExceptionHandler(private val translator: DomainExceptionTranslator) {
//
//    @ExceptionHandler(TaskException::class)
//    fun handleException(exception: TaskNotFoundException, locale: Locale): Mono<ResponseEntity<ProblemDetail>> =
//        Mono.just(translator.toProblemDetail(HttpStatus.BAD_REQUEST, exception, locale))
//
//
//}