package tuna.cinergyelite.main

import okhttp3.Interceptor
import okhttp3.Response
import tuna.cinergyelite.utils.PreferenceHelper
import tuna.core.constansts.Constants

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Add headers to the request
        val modifiedRequest = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .addHeader(
                "Authorization",
                "Bearer ${PreferenceHelper.getString(Constants.GUEST_TOKEN)}"
            )

            .build()

        return chain.proceed(modifiedRequest)
    }
}