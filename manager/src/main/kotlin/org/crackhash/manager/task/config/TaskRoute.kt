package org.crackhash.manager.task.config

object TaskRoute {
    const val API = "/api"
    const val INTERNAL_API = "/internal/api"
    const val CREATE_TASK = "/hash/crack"
    const val GET_TASK = "/hash/status/requestId={id}"
    const val UPDATE_TASK = "/manager/hash/crack/request"
    const val CREATE_SUBTASK = "/worker/hash/crack/task"
    const val MANAGER_QUEUE = "manager-queue"
    const val WORKER_QUEUE = "worker-queue"
}