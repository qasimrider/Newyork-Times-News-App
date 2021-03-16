
import okhttp3.*
import java.io.IOException


//class RequestInterceptor(private val requestHeaders: RequestHeaders) : Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val original: Request = chain.request()
//        val builder: Request.Builder = original
//                .newBuilder()
//                .headers(requestHeaders.headers.toHeaders())
//                .method(original.method, original.body)
//        val newRequest: Request = builder.build()
//        return chain.proceed(newRequest)
//    }
//}