package com.example.examentvmaze.binding_adapter

import android.content.Context
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.examentvmaze.R
import com.example.examentvmaze.arrayStringConvertText
import com.example.examentvmaze.constant.AppConstant.ADAPTER_LIST
import com.example.examentvmaze.constant.AppConstant.CONCAT_TEXT
import com.example.examentvmaze.constant.AppConstant.IMAGE_GLIDE
import com.example.examentvmaze.constant.AppConstant.ADD_GENDERS
import com.example.examentvmaze.constant.AppConstant.ADD_SCHEDULE
import com.example.examentvmaze.constant.AppConstant.CONVERT_HTML
import com.example.examentvmaze.constant.AppConstant.SET_CONTEXT
import com.example.examentvmaze.constant.AppConstant.VISIBLE_DETAIL
import com.example.examentvmaze.constant.AppConstant.VISIBLE_PROGRESS
import com.example.examentvmaze.retrofit.model.TVMovie
import com.example.examentvmaze.retrofit.model.TVShedule


@BindingAdapter(ADAPTER_LIST)
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView?, data: T?) {
    if (recyclerView?.adapter is IBindingRecyclerAdapter<*>) {
        (recyclerView.adapter as IBindingRecyclerAdapter<T>).setData(data)
    }
}

@BindingAdapter(IMAGE_GLIDE)
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter(CONCAT_TEXT, SET_CONTEXT)
fun concatText(textView: TextView?, tvMovie: TVMovie?, context: Context?) {
    if (tvMovie != null) {
        with(tvMovie) {
            textView?.text = context?.getString(
                R.string.schedule_item,
                if (airdate.isNullOrEmpty()) show.schedule.time else airdate,
                if (airtime.isNullOrEmpty()) arrayStringConvertText(tvMovie.show.schedule.days) else airtime
            )
        }
    }
}

@BindingAdapter(CONVERT_HTML, SET_CONTEXT)
fun convertHtml(textView: TextView?, string: String?, context: Context?) = with(textView) {
    if (string != null) {
        textView?.visibility = View.VISIBLE
        textView?.maxLines = 10
        this?.text = Html.fromHtml(string)
    } else {
        textView?.text = context?.getString(R.string.synopsis_not_foud)
    }
}

@BindingAdapter(ADD_GENDERS, SET_CONTEXT)
fun addText(textView: TextView?, strings: ArrayList<String>?, context: Context?) {
    if (!strings.isNullOrEmpty()) {
        textView?.visibility = View.VISIBLE
        textView?.text = arrayStringConvertText(strings)
    } else {
        textView?.text = context?.getString(R.string.genders_not_foud)
    }
}

@BindingAdapter(ADD_SCHEDULE, SET_CONTEXT)
fun addTextSchedule(textView: TextView?, schedule: TVShedule?, context: Context?) {
    if (schedule != null) {
        textView?.visibility = View.VISIBLE
        textView?.text = schedule.time.plus(" | ")
            .plus(arrayStringConvertText(schedule.days))
    } else {
        textView?.text = context?.getString(R.string.schedules_not_foud)
    }
}

@BindingAdapter(VISIBLE_PROGRESS)
fun visibleProgres(progressBar: ProgressBar?, flag: Boolean?) {
    progressBar?.visibility = if (flag == true) View.VISIBLE else View.GONE
}

@BindingAdapter(VISIBLE_DETAIL)
fun visibleDetail(constraintLayout: ConstraintLayout?, flag: Boolean?) {
    constraintLayout?.visibility = if (flag == false) View.VISIBLE else View.GONE
}
