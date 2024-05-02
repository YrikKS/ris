package org.crackhash.manager.task.domain.exception

class TaskNotFoundException(id: String): TaskException(".not_found", arrayOf(id), "Task with id=$id not found")