package com.example.octopoints.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AlertDialog
import com.example.octopoints.databinding.CommonItemBinding
import com.example.octopoints.model.data.match.Match

abstract class CommonAdapter(val context: Context, private val list: List<Any>): BaseAdapter() {

    lateinit var binding: CommonItemBinding

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        binding = CommonItemBinding.inflate(LayoutInflater.from(context), p2, false)
        binding.root.setCardBackgroundColor(ColorPalette.getColor())
        setView(list[p0])
        return binding.root
    }

    abstract fun setView(item: Any)

    fun buildDialog(title: String, func: ()->Unit){
        AlertDialog.Builder(context)
            .setMessage(title)
            .setCancelable(false)
            .setPositiveButton("Elimina") { dialog, it ->
                func()
            }.setNegativeButton("No") { dialog, it ->
                dialog.dismiss()
            }.create().show()
    }
}