package com.example.matru_snehhealth.utils

object BabyGrowthData {
    private val data = mapOf(
        4 to "Your baby is the size of a poppy seed.",
        5 to "Your baby is the size of an orange seed.",
        6 to "Your baby is the size of a sweet pea.",
        7 to "Your baby is the size of a blueberry.",
        8 to "Your baby is the size of a raspberry.",
        12 to "Your baby is the size of a lime.",
        16 to "Your baby is the size of an avocado.",
        20 to "Your baby is the size of a banana.",
        24 to "Your baby is the size of a corn.",
        28 to "Your baby is the size of an eggplant.",
        32 to "Your baby is the size of a squash.",
        36 to "Your baby is the size of a papaya.",
        40 to "Your baby is the size of a small pumpkin."
    )

    fun getDescription(week: Int): String {
        return data[week] ?: "Your baby is growing and getting stronger every day!"
    }
}
