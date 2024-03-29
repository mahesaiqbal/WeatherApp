package com.mahesaiqbal.weatherapp.data.source.remote

class ApiResponse<T>(
    val status: StatusResponse,
    val body: T?,
    val message: String?
) {
    companion object {

        fun <T> success(body: T?): ApiResponse<T> {
            return ApiResponse(StatusResponse.SUCCESS, body, null)
        }

        fun <T> error(msg: String, body: T?): ApiResponse<T> {
            return ApiResponse(StatusResponse.ERROR, body, msg)
        }
    }
}
