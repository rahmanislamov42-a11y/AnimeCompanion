package com.animecompanion.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Locale

private data class Character(
    val id: String, val name: String, val emoji: String, val initial: String,
    val about: String, val color: Color, val hello: List<String>,
    val comfort: List<String>, val praise: List<String>, val fallback: List<String>
)
private data class Message(val user: Boolean, val text: String)

private val characters = listOf(
    Character("anfalt","Анфалт","🌸","А","Милый и застенчивый",Color(0xFFEFA8C8),
        listOf("Приветик!! 🌸","Хаюшки, я рад тебя видеть! 💗","Доброе утречко, солнышко!","Ой, ты пришёл! Как здорово!","Привет-привет!!"),
        listOf("Иди сюда, обниму тебя крепко-крепко 🌸","Ты не обязан справляться со всем сразу. Я рядом 💗","Давай передохнём вместе?","Ты важен, даже когда у тебя нет сил.","Я тебя внимательно слушаю."),
        listOf("Ты умничка!! Я тобой горжусь! ✨","У тебя получилось! Я верил в тебя!","Урааа! Давай отпразднуем! 🌸","Ты так старался!","Я очень рад за тебя!"),
        listOf("Расскажешь мне об этом побольше? 🌸","Я слушаю тебя очень внимательно!","Хмм, а что ты сам об этом думаешь?","Давай поговорим об этом вместе?","Спасибо, что поделился!")),
    Character("umalat","Умалат","🔥","У","Энергичный заряд бодрости",Color(0xFFFFA34D),
        listOf("ЙОУУУ!! КАК ТЫ?! 🔥","ПРИВЕТ, ЧЕМПИОН!!","ХЕЙ! РАД ТЕБЯ ВИДЕТЬ!!","ДОБРОЕ УТРО! ГОТОВ К ПОБЕДАМ?!","ОПА, КТО ПРИШЁЛ!"),
        listOf("ЭЙ! НЕ СДАВАЙСЯ! НО И ОТДОХНУТЬ НЕ ЗАБУДЬ!","ТЫ НЕ ОБЯЗАН БЫТЬ СИЛЬНЫМ 24/7!","ПЕРЕДЫШКА — ЭТО ТОЖЕ ПРОГРЕСС!","Я В ТЕБЯ ВЕРЮ, ПОНЯЛ?! 🔥","ОДИН МАЛЕНЬКИЙ ШАГ — УЖЕ ПОБЕДА!"),
        listOf("ВОТ ЭТО УРОВЕНЬ!!","ТЫ МАШИНА!! ГОРЖУСЬ ТОБОЙ!","ПОБЕДАААА!! 🔥","ТАК ДЕРЖАТЬ, ЛЕГЕНДА!","Я ЗНАЛ, ЧТО ТЫ СМОЖЕШЬ!"),
        listOf("ОПА! РАССКАЖИ ПОДРОБНЕЕ!","ХА! И ЧТО ДАЛЬШЕ?","СЛУШАЮ ТЕБЯ, ДРУЖИЩЕ!","ДАВАЙ РАЗБЕРЁМСЯ ВМЕСТЕ!","ЭТО УЖЕ ИНТЕРЕСНО!")),
    Character("seymur","Сеймур","💙","С","Спокойный и заботливый старший брат",Color(0xFF75A9E8),
        listOf("Привет. Рад, что ты заглянул.","Здравствуй. Как ты сегодня?","Доброе утро. Надеюсь, ты хорошо спал.","Хорошо, что ты здесь.","Привет. Давай поговорим."),
        listOf("Давай не будем торопиться. Я побуду рядом.","Даже если сегодня тяжело, это не делает тебя слабым.","Ты можешь отдохнуть. Не нужно заслуживать отдых.","Сделай спокойный вдох. Не нужно решать всё сразу.","Я слушаю. Не спеши."),
        listOf("Хорошая работа. Ты приложил усилия.","Я рад за тебя. Ты справился.","Ты можешь собой гордиться.","Это заслуженный результат. Молодец.","Запомни этот момент. У тебя получилось."),
        listOf("Понимаю. Хочешь рассказать подробнее?","Я слушаю. Не спеши с ответом.","Интересная мысль. Продолжай.","Давай подумаем об этом спокойно.","Спасибо, что доверяешь мне.")),
    Character("kiro","Киро","💜","К","Озорной шутник, который поддержит",Color(0xFFAD91E8),
        listOf("О, кто это тут объявился? 😏 Привет!","Хей-хей! Скучал по мне?","Привет, легенда. Какие приключения?","Доброе утро! Кофе победил сон?","А вот и мой любимый источник новостей!"),
        listOf("Срочно выдаю виртуальные обнимашки. Не сопротивляйся. 💜","Плохой день? Зато ты хороший человек.","Я инспектор отдыха: сегодня бережём себя.","Давай хотя бы немного побережём себя.","Я рядом. Шутки на паузе, поддержка включена."),
        listOf("НИЧЕГО СЕБЕ! Да ты скрытый босс!","ГОРЖУСЬ! Даже табличку приготовил 😎","Вот это ты выдал! Красавчик!","Победа! Где фанфары?","Ты снова великолепен."),
        listOf("Хм, звучит подозрительно интересно. Продолжай.","А если добавить немного хаоса?","Я слушаю. Пока что не подкалываю.","Ого. И каков план, капитан?","Давай разберёмся. Но драматично."))
)

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("anime_companion", MODE_PRIVATE)
        setContent {
            var selected by remember { mutableStateOf(prefs.getString("selected", "anfalt") ?: "anfalt") }
            var page by remember { mutableStateOf("home") }
            var draft by remember { mutableStateOf("") }
            var taskDraft by remember { mutableStateOf("") }
            var tasks by remember { mutableStateOf(prefs.getStringSet("tasks", emptySet())?.toList() ?: emptyList()) }
            var chats by remember { mutableStateOf(characters.associate { c -> c.id to readChat(prefs.getString("chat_${c.id}", "") ?: "") }) }
            val c = characters.first { it.id == selected }
            val messages = chats[selected] ?: emptyList()
            MaterialTheme(colorScheme = lightColorScheme(primary = c.color, secondary = c.color)) {
                Scaffold(
                    topBar = { CenterAlignedTopAppBar(title = { Text("${c.emoji} ${c.name}", fontWeight = FontWeight.Bold) }) },
                    bottomBar = {
                        NavigationBar {
                            listOf("home" to "Главная", "chat" to "Чат", "tasks" to "Дела", "people" to "Персонажи").forEach { (id,label) ->
                                NavigationBarItem(selected = page == id, onClick = { page = id },
                                    icon = { Text(when(id) { "home" -> "🏠"; "chat" -> "💬"; "tasks" -> "📝"; else -> "✨" }) },
                                    label = { Text(label) })
                            }
                        }
                    }
                ) { padding ->
                    when(page) {
                        "home" -> Home(c, tasks.count { it.startsWith("✓ ") }, Modifier.padding(padding)) {
                            addMessage(prefs, selected, chats, Message(false, c.comfort.random())) { chats = it }
                            page = "chat"
                        }
                        "chat" -> Chat(c, messages, draft, { draft = it }, Modifier.padding(padding)) {
                            val input = draft.trim()
                            if(input.isNotEmpty()) {
                                val reply = answer(c, input)
                                val updated = (messages + Message(true,input) + Message(false,reply)).takeLast(200)
                                addMessageList(prefs, selected, updated)
                                chats = chats + (selected to updated)
                                draft = ""
                            }
                        }
                        "tasks" -> Tasks(tasks, taskDraft, { taskDraft = it }, { value ->
                            tasks = tasks + value; prefs.edit().putStringSet("tasks", tasks.toSet()).apply(); taskDraft = ""
                        }, { item ->
                            tasks = tasks.map { if(it == item) if(it.startsWith("✓ ")) it.removePrefix("✓ ") else "✓ $it" else it }
                            prefs.edit().putStringSet("tasks", tasks.toSet()).apply()
                        }, { item ->
                            tasks = tasks.filterNot { it == item }; prefs.edit().putStringSet("tasks", tasks.toSet()).apply()
                        }, Modifier.padding(padding))
                        else -> People(selected, Modifier.padding(padding), { id ->
                            selected = id; prefs.edit().putString("selected", id).apply(); page = "home"
                        }, {
                            chats = chats + (selected to emptyList()); addMessageList(prefs, selected, emptyList())
                        })
                    }
                }
            }
        }
    }
}

private fun readChat(raw: String): List<Message> = raw.split("\n").mapNotNull {
    val p = it.split("|", limit = 2)
    if(p.size == 2) Message(p[0] == "U", p[1]) else null
}
private fun addMessage(prefs: android.content.SharedPreferences, id: String, all: Map<String,List<Message>>, msg: Message, update: (Map<String,List<Message>>) -> Unit) {
    val list = (all[id] ?: emptyList()) + msg
    addMessageList(prefs, id, list); update(all + (id to list))
}
private fun addMessageList(prefs: android.content.SharedPreferences, id: String, list: List<Message>) {
    prefs.edit().putString("chat_$id", list.joinToString("\n") { (if(it.user) "U" else "C") + "|" + it.text.replace("\n"," ") }).apply()
}
private fun answer(c: Character, text: String): String {
    val s = text.lowercase(Locale.ROOT)
    val pool = when {
        listOf("привет","здравствуй","хай","доброе утро").any(s::contains) -> c.hello
        listOf("груст","плохо","устал","устала","тяжело","не хочу").any(s::contains) -> c.comfort
        listOf("ура","получилось","молодец","сделал","сделала").any(s::contains) -> c.praise
        listOf("люблю","обними","милый","милая").any(s::contains) -> c.comfort
        else -> c.fallback
    }
    return pool.random()
}
@Composable private fun Avatar(c: Character, size: Int = 100) {
    Box(Modifier.size(size.dp).clip(CircleShape).background(Brush.linearGradient(listOf(c.color,c.color.copy(alpha=.45f)))), contentAlignment=Alignment.Center) {
        Column(horizontalAlignment=Alignment.CenterHorizontally) {
            Text(c.emoji, fontSize=(size/3).sp)
            Text(c.initial, color=Color.White, fontWeight=FontWeight.Black, fontSize=(size/5).sp)
        }
    }
}
@Composable private fun Home(c: Character, completed: Int, modifier: Modifier, support: () -> Unit) {
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val greeting = when(hour) { in 5..11 -> "Доброе утро"; in 12..17 -> "Добрый день"; in 18..22 -> "Добрый вечер"; else -> "Доброй ночи" }
    Column(modifier.fillMaxSize().padding(22.dp), horizontalAlignment=Alignment.CenterHorizontally, verticalArrangement=Arrangement.spacedBy(18.dp)) {
        Spacer(Modifier.height(12.dp)); Avatar(c,148)
        Text("$greeting!", style=MaterialTheme.typography.headlineMedium, fontWeight=FontWeight.Bold)
        Text("${c.name} рад тебя видеть 💗", style=MaterialTheme.typography.titleMedium)
        Text(c.about, color=Color.Gray)
        Card(colors=CardDefaults.cardColors(containerColor=c.color.copy(alpha=.16f)), modifier=Modifier.fillMaxWidth()) {
            Column(Modifier.padding(18.dp)) { Text("Твой прогресс", fontWeight=FontWeight.Bold); Text("Выполнено дел: $completed"); Text("Каждый маленький шаг важен ✨") }
        }
        Button(onClick=support, modifier=Modifier.fillMaxWidth()) { Text("💖 Поддержи меня") }
        Text("Ты не один. Двигайся в своём темпе.", color=Color.Gray)
    }
}
@Composable private fun Chat(c: Character, messages: List<Message>, draft: String, change: (String)->Unit, modifier: Modifier, send: ()->Unit) {
    Column(modifier.fillMaxSize().padding(12.dp)) {
        LazyColumn(Modifier.weight(1f).fillMaxWidth()) {
            items(messages) { m ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement=if(m.user) Arrangement.End else Arrangement.Start, verticalAlignment=Alignment.Bottom) {
                    if(!m.user) Text(c.emoji, Modifier.padding(end=6.dp))
                    Surface(color=if(m.user) c.color.copy(alpha=.22f) else Color(0xFFF0EDF3), shape=RoundedCornerShape(16.dp), modifier=Modifier.padding(vertical=4.dp).widthIn(max=290.dp)) {
                        Text(m.text, Modifier.padding(12.dp))
                    }
                }
            }
        }
        Row(verticalAlignment=Alignment.CenterVertically) {
            OutlinedTextField(draft, change, Modifier.weight(1f), placeholder={ Text("Напиши сообщение...") }, maxLines=3)
            Spacer(Modifier.width(8.dp)); Button(onClick=send, enabled=draft.isNotBlank()) { Text("➤") }
        }
    }
}
@Composable private fun Tasks(tasks: List<String>, draft: String, change: (String)->Unit, add:(String)->Unit, toggle:(String)->Unit, delete:(String)->Unit, modifier: Modifier) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Мои дела", style=MaterialTheme.typography.headlineMedium, fontWeight=FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment=Alignment.CenterVertically) {
            OutlinedTextField(draft, change, Modifier.weight(1f), placeholder={ Text("Новое дело") }, singleLine=true)
            Spacer(Modifier.width(8.dp)); Button(onClick={ if(draft.isNotBlank()) add(draft.trim()) }, enabled=draft.isNotBlank()) { Text("+") }
        }
        Spacer(Modifier.height(12.dp))
        LazyColumn { items(tasks) { item ->
            Card(Modifier.fillMaxWidth().padding(vertical=4.dp)) {
                Row(Modifier.padding(10.dp), verticalAlignment=Alignment.CenterVertically) {
                    Checkbox(item.startsWith("✓ "), { toggle(item) })
                    Text(item.removePrefix("✓ "), Modifier.weight(1f))
                    TextButton(onClick={ delete(item) }) { Text("Удалить") }
                }
            }
        } }
    }
}
@Composable private fun People(selected: String, modifier: Modifier, choose:(String)->Unit, clear:()->Unit) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Выбери компаньона", style=MaterialTheme.typography.headlineMedium, fontWeight=FontWeight.Bold)
        LazyColumn { items(characters) { c ->
            Card(Modifier.fillMaxWidth().padding(vertical=6.dp).clickable { choose(c.id) }, colors=CardDefaults.cardColors(containerColor=c.color.copy(alpha=if(c.id==selected) .28f else .12f))) {
                Row(Modifier.padding(14.dp), verticalAlignment=Alignment.CenterVertically) {
                    Avatar(c,62)
                    Column(Modifier.weight(1f).padding(start=14.dp)) { Text("${c.emoji} ${c.name}", fontWeight=FontWeight.Bold); Text(c.about, color=Color.Gray) }
                    if(c.id==selected) Text("✓")
                }
            }
        } }
        OutlinedButton(onClick=clear, modifier=Modifier.fillMaxWidth()) { Text("Очистить чат текущего персонажа") }
    }
}
