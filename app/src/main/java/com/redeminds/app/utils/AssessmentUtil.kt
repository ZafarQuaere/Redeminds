package com.redeminds.app.utils

import android.content.Context
import com.redeminds.app.R

object AssessmentUtil {

    fun getAssessment31Data(): List<Assessment31> {
        val asst31List = ArrayList<Assessment31> ()
        asst31List.add(Assessment31("Check the town map for directions.",4))
        asst31List.add(Assessment31("Enter the town and check for signs of a fair.",3))
        asst31List.add(Assessment31("Ask a passerby about the fair and take further directions.",5))
        asst31List.add(Assessment31("Pick a direction and run because you don’t want to wait.",1))
        asst31List.add(Assessment31("Wait near the entrance of the town, hoping to see the Lord Acheron.",2))
        return asst31List;
    }

    fun getAssessment21Data( context: Context): List<Assessment21> {
        val questionArray:Array<String> = context.resources.getStringArray(R.array.assessment_chapter2_one)
        val asst21List = ArrayList<Assessment21> ()
        for(i in questionArray.indices){
            val asst21 = Assessment21()
                asst21.id = i
                asst21.question = questionArray[i]
            asst21List.add(asst21)
        }
        return asst21List;
    }
}