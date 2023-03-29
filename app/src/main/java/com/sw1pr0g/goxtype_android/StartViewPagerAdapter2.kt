package com.sw1pr0g.goxtype_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sw1pr0g.goxtype_android.databinding.ActivityStartBinding
import com.sw1pr0g.goxtype_android.databinding.SliderStartLayoutBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class StartViewPagerAdapter2(private var image: List<Int>,
                             private var heading: List<Int>,
                             private var description: List<Int>) : RecyclerView.Adapter<StartViewPagerAdapter2.Pager2ViewHolder>() {
    private lateinit var binding: SliderStartLayoutBinding

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.slider_start_image_view)
        val itemHeading: TextView = itemView.findViewById(R.id.slider_start_heading)
        val itemDescription: TextView = itemView.findViewById(R.id.slider_start_description)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Pager2ViewHolder {
        binding = SliderStartLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slider_start_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return image.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {

        holder.itemImage.setImageResource(image[position])
        holder.itemHeading.setText(heading[position])
        holder.itemDescription.setText(description[position])

        /*holder.apply {
            binding.sliderStartImageView.setImageResource(image[position])
            binding.sliderStartHeading.setText(heading[position])
            binding.sliderStartDescription.setText(description[position])
        }*/
    }

}