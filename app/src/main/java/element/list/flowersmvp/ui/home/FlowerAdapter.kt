package element.list.flowersmvp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import element.list.flowersmvp.R
import element.list.flowersmvp.constants.Constants
import element.list.flowersmvp.model.Flower
import kotlinx.android.synthetic.main.flower_item.view.*


class FlowerAdapter(var context: Context) : RecyclerView.Adapter<FlowerAdapter.ViewHolder>() {
    var listItems: MutableList<Flower> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.flower_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.englishName.text = listItems[position].name
        holder.latinName.text = listItems[position].latinName
        holder.sightings.text = listItems[position].sightings.toString()

        var options = RequestOptions()
        options = options.transform(CenterCrop())
        Glide.with(context)
            .load(Constants.HTTPS_PROTOCOL_PREFIX + listItems[position].profilePicture)
            .apply(options)
            .into(holder.flowerImage)
    }

    fun setData(listItems: MutableList<Flower>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    fun addData(listItems: MutableList<Flower>) {
        this.listItems.addAll(listItems)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var englishName: TextView = view.englishName
        var latinName: TextView = view.latinName
        var sightings: TextView = view.sightings
        var flowerImage: ImageView = view.flowerImage
    }

}