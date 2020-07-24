package com.kun.easytra

import android.util.Base64
import java.security.SignatureException
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

enum class NetworkState {
    IDLE,
    LOADING,
    ERROR
}

object Utils {
    @Throws(SignatureException::class)
    fun getHmacSha1Signature(xData: String, AppKey: String): String? {
        return try {
            // get an hmac_sha1 key from the raw key bytes
            val signingKey =
                SecretKeySpec(AppKey.toByteArray(charset("UTF-8")), "HmacSHA1")

            // get an hmac_sha1 Mac instance and initialize with the signing key
            val mac: Mac = Mac.getInstance("HmacSHA1")
            mac.init(signingKey)

            // compute the hmac on input data bytes
            val rawHmac: ByteArray = mac.doFinal(xData.toByteArray(charset("UTF-8")))
            Base64.encodeToString(rawHmac, Base64.NO_WRAP)
        } catch (e: Exception) {
            throw SignatureException("Failed to generate HMAC : " + e.message)
        }
    }

    fun getServerTimeInXDateFormat(): String? {
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat =
            SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return dateFormat.format(calendar.time)
    }
}