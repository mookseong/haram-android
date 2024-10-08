package com.space.core_ui.extension

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.DisplayMetrics
import androidx.databinding.ViewDataBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ParametersBuilder
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.DividerGrayLineDecoration
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.databinding.ViewUiHeaderBinding
import com.space.shared.type.DividerType
import org.apache.commons.io.FileUtils
import java.io.File

fun Context.createFileFromUri(name: String, uri: Uri): File? {
    return try {
        val stream = contentResolver.openInputStream(uri)
        val file =
            //"${name}_${System.currentTimeMillis()}",
            File.createTempFile(
                name,
                ".${name.split(".")[1]}",
                cacheDir
            )
        FileUtils.copyInputStreamToFile(
            stream,
            file
        )
        file
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Context.getFileName(uri: Uri): String? {
    var fileName: String? = null
    if (uri.scheme.equals("content")) {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
                fileName = it.getString(columnIndex)
            }
            it.close()
        }
    } else if (uri.scheme.equals("file")) {
        fileName = uri.path?.let { path -> File(path).name }
    }
    return fileName
}

fun Context.dpToPixel(dp: Float): Float {
    return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun Context.dpToPixelInt(dp: Float): Int {
    return (dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

inline fun FirebaseAnalytics.logEvent(
    name: kotlin.String,
    crossinline block: ParametersBuilder.() -> kotlin.Unit
) {
/* compiled code */
}
