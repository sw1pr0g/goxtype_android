package com.sw1pr0g.goxtype_android.startslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sw1pr0g.goxtype_android.R
import kotlin.properties.Delegates

class StartSliderAdapter(
    private val data: List<Slide>
) : RecyclerView.Adapter<StartSliderAdapter.StartSliderViewHolder>() {

    class StartSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sliderStartImageView: ImageView = view.findViewById(R.id.slider_start_image_view)
        val sliderStartHeading: TextView = view.findViewById(R.id.slider_start_heading)
        val sliderStartDescription: TextView = view.findViewById(R.id.slider_start_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartSliderViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.start_slide_layout, parent, false)

        return StartSliderViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: StartSliderViewHolder, position: Int) {
        val slide = data[position]

        holder.sliderStartImageView.setImageResource(slide.imageResourceId)
        holder.sliderStartHeading.setText(slide.headingResourceId)
        holder.sliderStartDescription.setText(slide.descriptionResourceId)
    }

    override fun getItemCount(): Int = data.size

}