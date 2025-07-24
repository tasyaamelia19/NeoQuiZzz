package com.example.neoquizzz

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neoquiz.QuizModel
import com.example.neoquizzz.databinding.QuizItemRecyclerRowBinding

class QuizListAdapter(private var quizModelList: List<QuizModel>) :
    RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {

    // ✅ Fungsi ini digunakan untuk memperbarui daftar saat search
    fun filterList(filteredList: List<QuizModel>) {
        quizModelList = filteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuizItemRecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }

    override fun getItemCount(): Int = quizModelList.size

    class MyViewHolder(private val binding: QuizItemRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: QuizModel) {
            binding.apply {
                quizTitleText.text = model.title
                quizSubtitleText.text = model.subtitle
                quizTimeText.text = "${model.time} min"

                root.setOnClickListener {
                    val intent = Intent(root.context, QuizActivity::class.java).apply {
                        putExtra("quizTitle", model.title)
                        putExtra("quizTime", model.time)

                        // ✅ Pastikan data soal dimasukkan via companion object
                        QuizActivity.questionModelList = model.questions
                        QuizActivity.timer = model.time
                    }
                    root.context.startActivity(intent)
                }
            }
        }
    }
}
