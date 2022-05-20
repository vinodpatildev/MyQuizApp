package com.example.myquizapp

object Constants {

    fun getQuestions():ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val que1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "India",
            "UAE",
            "argentina",
            "Canada",
        2,
        )
        questionList.add(que1)

        val que2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Australia",
            "UAE",
            "argentina",
            "Canada",
            0,
        )
        questionList.add(que2)

        val que3 = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "India",
            "Katar",
            "argentina",
            "Belgium",
            3,
        )
        questionList.add(que3)

        val que4 = Question(
            4,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "India",
            "Fiji",
            "Brazil",
            "Canada",
            2,
        )
        questionList.add(que4)

        val que5 = Question(
            5,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "China",
            "Denmark",
            "Argentina",
            "Japan",
            1,
        )
        questionList.add(que5)

        val que6 = Question(
            6,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "USA",
            "UAE",
            "Canada",
            "fiji",
            3,
        )
        questionList.add(que6)

        val que7 = Question(
            7,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Germany",
            "Inda",
            "Japan",
            "Republic of Kongo",
            0,
        )
        questionList.add(que7)

        val que8 = Question(
            8,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "India",
            "Pakistan",
            "China",
            "Nepal",
            0,
        )
        questionList.add(que8)

        val que9 = Question(
            9,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "UAE",
            "Kuwait",
            "France",
            "Russia",
            1,
        )
        questionList.add(que9)

        val que10 = Question(
            10,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "France",
            "UAE",
            "Australia",
            "New Zealand",
            2,
        )
        questionList.add(que10)

        return questionList
    }
}