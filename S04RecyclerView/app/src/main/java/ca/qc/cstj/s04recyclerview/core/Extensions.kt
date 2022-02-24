package ca.qc.cstj.s04recyclerview.core

import android.widget.ImageView

fun ImageView.loadFromResources(imageName: String)
{
    val imageID = this.resources.getIdentifier(imageName, "drawable", this.context.packageName)
    this.setImageResource(imageID)
}