import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val messageLike = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Andrei",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val messageNewPost = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
            "postId": 1,
            "postAuthor": "Netology",
            "postContent": "Из-за больших размеров кратера его существование невозможно было определить на глаз. Учёные открыли его только в 1978 году, что произошло совершенно случайно при проведении геофизических исследований на дне Мексиканского залива.В ходе исследований была обнаружена большая подводная дуга протяжённостью около 70 км, имеющая форму полукольца. По данным гравитационного поля учёные нашли продолжение этой дуги на суше, на северо-западе полуострова Юкатан. Сомкнувшись, дуги формируют окружность, диаметр которой составляет приблизительно 180 км.Ударное происхождение кратера было доказано по гравитационной аномалии внутри кольцеобразной структуры, а также по присутствию горных пород, характерных только для ударно-взрывного породообразования, этот вывод подтвердили также химические исследования грунтов и детальная космическая съёмка местности."
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(messageLike)
    FirebaseMessaging.getInstance().send(messageNewPost)
}
