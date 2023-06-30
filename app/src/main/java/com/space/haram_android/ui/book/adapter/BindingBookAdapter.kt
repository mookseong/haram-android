package com.space.haram_android.ui.book.adapter

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.haram_android.R
import com.space.haram_android.common.data.response.book.CategoryModel
import com.space.haram_android.common.data.response.book.data.BookKeepInfo
import com.space.haram_android.common.data.response.book.data.SearchResultModel
import com.space.haram_android.ui.book.home.BookCategoryRecycler
import com.space.haram_android.ui.book.info.BookDetailKeepRecycler
import com.space.haram_android.ui.book.search.BookSearchRecycler
import com.space.haram_android.util.DividerItemDecoration

object BindingBookAdapter {

    @JvmStatic
    @BindingAdapter("bookHomeAdapter")
    @SuppressLint("NotifyDataSetChanged")
    fun setBookItems(recyclerView: RecyclerView, item: List<CategoryModel>?) {
        if (recyclerView.adapter == null) {
            val adapter = item?.let { BookCategoryRecycler() }
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }
        if (item != null) {
            (recyclerView.adapter as BookCategoryRecycler).categoryModels =
                item as ArrayList<CategoryModel>
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }


    @JvmStatic
    @BindingAdapter("bookSearchAdapter")
    @SuppressLint("NotifyDataSetChanged")
    fun setSearchItems(recyclerView: RecyclerView, item: List<SearchResultModel>?) {
        if (recyclerView.adapter == null) {
            val adapter = item?.let { BookSearchRecycler() }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    R.drawable.line_divider,
                    50,
                    50
                )
            )
            recyclerView.isNestedScrollingEnabled = false

        }
        if (item != null) {
            (recyclerView.adapter as BookSearchRecycler).models =
                item as ArrayList<SearchResultModel>
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }


    @JvmStatic
    @BindingAdapter("bookDetailAdapter")
    @SuppressLint("NotifyDataSetChanged")
    fun setDetailItems(recyclerView: RecyclerView, item: List<BookKeepInfo>?) {
        if (recyclerView.adapter == null) {
            val adapter = item?.let { BookDetailKeepRecycler() }
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    R.drawable.line_divider,
                    50,
                    50
                )
            )
            recyclerView.isNestedScrollingEnabled = false

        }
        if (item != null) {
            (recyclerView.adapter as BookDetailKeepRecycler).models =
                item as ArrayList<BookKeepInfo>
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }



}