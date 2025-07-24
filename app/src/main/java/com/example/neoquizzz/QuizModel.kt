package com.example.neoquiz

data class QuizModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val time: String,
    val questions: List<QuestionModel>
) {
    constructor() : this(id = "", title = "", subtitle = "", time = "", questions = emptyList())

    fun totalQuestions(): Int = questions.size
}

data class QuestionModel(
    val question: String,
    val options: List<String>,
    val correct: String
) {
    constructor() : this(question = "", options = emptyList(), correct = "")

    fun isCorrectAnswer(userAnswer: String?): Boolean {
        return userAnswer != null && userAnswer == correct
    }
}
