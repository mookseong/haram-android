package com.space.core_ui.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.core_ui.EditType
import com.space.core_ui.util.dateToDateTime
import com.space.shared.LayoutType
import com.space.shared.util.formatToDate
import timber.log.Timber


@BindingAdapter("setImgUrl")
fun setImageUrl(
    imageView: ImageView,
    url: String?
) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}

@BindingAdapter("setImgUrl")
fun setImageUrl(
    imageView: ImageView,
    url: Drawable?
) {
    url?.let {
        Glide.with(imageView.context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}

@BindingAdapter("setImgUrl")
fun setImageUrl(
    imageView: ImageView,
    bitmap: Bitmap?
) {
    bitmap?.let {
        Glide.with(imageView.context)
            .load(bitmap)
            .centerCrop()
            .into(imageView)
    }
}


@BindingAdapter("layoutType")
fun setLayoutType(
    recyclerView: RecyclerView,
    type: LayoutType
) {
    recyclerView.setHasFixedSize(true)
    when (type) {
        LayoutType.HORIZONTAL -> {
            recyclerView.layoutManager =
                LinearLayoutManager(
                    recyclerView.context,
                    RecyclerView.HORIZONTAL,
                    false
                )
        }

        LayoutType.VERTICAL -> {
            recyclerView.layoutManager =
                LinearLayoutManager(
                    recyclerView.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }

        else -> {
            recyclerView.layoutManager =
                LinearLayoutManager(
                    recyclerView.context,
                    RecyclerView.VERTICAL,
                    false
                )
        }
    }
}

@BindingAdapter("setDate")
fun setDate(
    textView: TextView,
    date: String? = "",
) {
    try {
        date?.let {
            textView.text = formatToDate(date)
        }
    } catch (e: Exception) {
        Timber.d(e.message)
        textView.text = "정보 없음"
    }
}

@BindingAdapter("setDateTime")
fun setDateTime(
    textView: TextView,
    date: String? = "",
) {
    try {
        date?.let {
            textView.text = dateToDateTime(it)
        }
    } catch (e: Exception) {
        Timber.d(e.message)
        textView.text = "정보없음"
    }
}


@BindingAdapter("setEditType")
fun setEditType(
    editText: EditText,
    editType: EditType
) {
    editText.inputType = when (editType) {
        EditType.TEXT ->
            InputType.TYPE_CLASS_TEXT

        EditType.ID ->
            InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        EditType.NAME ->
            InputType.TYPE_TEXT_VARIATION_PERSON_NAME

        EditType.PHONE ->
            InputType.TYPE_CLASS_PHONE

        EditType.PASSWORD ->
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        EditType.NUMBER ->
            InputType.TYPE_CLASS_NUMBER
        else -> {
            InputType.TYPE_CLASS_TEXT
        }
    }
}
