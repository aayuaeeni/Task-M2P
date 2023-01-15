package com.raju.um.presentation.jokes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.raju.um.bean.order.JokesBean
import com.raju.um.databinding.JokesItemBinding

class JokesListAdapter(
    var itemList: List<JokesBean>
) : RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {
    private lateinit var binding: JokesItemBinding

    inner class ViewHolder(binding: JokesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = JokesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        binding.tvJokes.text = item.joke

        binding.rlContainer.setOnClickListener {
            // Just for info
            Toast.makeText(binding.rlContainer.context, "${item.joke}", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
