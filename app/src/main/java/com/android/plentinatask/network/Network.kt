package com.android.plentinatask.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiService: ApiService by lazy {
    Network.retrofit.create(
        ApiService::class.java
    )
}

object Network {


    const val REQUIRE_AUTHENTICATION = "Require-Authentication"
    lateinit var retrofit: Retrofit

    var authToken: String? = null
    var apiSessionId: String? = null

    fun init(baseUrl: String, isDebug: Boolean = false) {
        retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(buildClient(isDebug))
            .build()
    }

    private fun buildClient(isDebug: Boolean): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(ApiInterceptor)
//        client.addInterceptor(ReceivedCookiesInterceptor(LavaApp.applicationContext()))
        if (isDebug) {
            client.addInterceptor(logging)
        }
        return client.build()
    }


    object ApiInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()


            request = request.newBuilder()
                .addHeader("Cookie", apiSessionId ?: "")
                .addHeader("authorizationKey", "as@dL8]Rn3\$2S!anR")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()

            return chain.proceed(request)
        }

    }
}
