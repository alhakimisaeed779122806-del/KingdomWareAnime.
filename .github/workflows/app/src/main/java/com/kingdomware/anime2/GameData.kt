package com.kingdomware.anime2

data class ResourceState(
    val mana: Int = 50,
    val loyalty: Int = 50,
    val gold: Int = 50,
    val power: Int = 50
) {
    fun isGameOver(): Boolean {
        return mana <= 0 || mana >= 100 ||
               loyalty <= 0 || loyalty >= 100 ||
               gold <= 0 || gold >= 100 ||
               power <= 0 || power >= 100
    }

    fun getDeathReason(): String {
        return when {
            mana <= 0 -> "نفدت طاقة المانا، وانهار الحاجز السحري للمملكة!"
            mana >= 100 -> "انفجرت طاقة المانا في القلعة ودمرت العاصمة!"
            loyalty <= 0 -> "ثار الشعب والمغامرون وقاموا بالإطاحة بعرشك!"
            loyalty >= 100 -> "أصبح الشعب متعجرفاً لدرجة رفض السلطة الملكية!"
            gold <= 0 -> "أفلست الخزينة وهرب المرتزقة والجيش!"
            gold >= 100 -> "فطست الخزينة بالذهب لدرجة انهيار اقتصاد القلعة!"
            power <= 0 -> "تعرضت المملكة للغزو السريع بسبب ضعف قوتك!"
            power >= 100 -> "انقلب الجيش عليك خوفاً من بطشك وطغيانك!"
            else -> "انتهت اللعبة بطريقة غامضة!"
        }
    }
}

data class Choice(
    val text: String,
    val manaEffect: Int = 0,
    val loyaltyEffect: Int = 0,
    val goldEffect: Int = 0,
    val powerEffect: Int = 0
)

data class Card(
    val characterName: String,
    val characterRole: String,
    val text: String,
    val leftChoice: Choice,
    val rightChoice: Choice
)

object GameRepository {
    val sampleCards = listOf(
        Card(
            characterName = "إيلينا",
            characterRole = "المستشارة السحرية (Tsundere)",
            text = "هجوم لورق الغابة السوداء يقترب من الأسوار! هل نستخدم تعويذة الحظر المكلفة؟",
            leftChoice = Choice("نعم، أطلقوا التعويذة!", manaEffect = 15, goldEffect = -20, powerEffect = 10),
            rightChoice = Choice("لا، اعتمدوا على الفرسان!", manaEffect = -10, loyaltyEffect = -10, powerEffect = 15)
        ),
        Card(
            characterName = "ريتو",
            characterRole = "البطل الأسطوري المغرور",
            text = "أحتاج إلى تمويل ضخم لرحلة استكشاف زنزانة التنانير (Dungeon)!",
            leftChoice = Choice("خذ ما تكفي وزيادة يا بطل!", goldEffect = -25, powerEffect = 20, loyaltyEffect = 10),
            rightChoice = Choice("اعتمد على مهاراتك وحدك بدون ذهب!", goldEffect = 10, powerEffect = -15, loyaltyEffect = -10)
        )
    )
}
