
package com.animecompanion.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AnimeCompanionApp()
            }
        }
    }
}

data class Character(
    val name: String,
    val description: String
)

@Composable
fun AnimeCompanionApp() {
    val characters = listOf(
        Character("Анфалт", "Спокойный и заботливый"),
        Character("Умалат", "Весёлый и энергичный"),
        Character("Сеймур", "Умный и немного застенчивый"),
        Character("Киро", "Милый и дружелюбный")
    )

    var selected by remember { mutableStateOf(characters[0]) }
    var message by remember { mutableStateOf("") }
    var chat by remember { mutableStateOf(listOf("Привет! Рад тебя видеть 💜")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Companion 🌸") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Выбери своего компаньона", style = MaterialTheme.typography.titleMedium)

            characters.forEach { character ->
                OutlinedButton(
                    onClick = {
                        selected = character
                        chat = listOf("Привет! Я ${character.name}. Рад тебя видеть 💜")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(character.name)
                }
            }

            Text(
                text = selected.description,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            HorizontalDivider()

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                items(chat) { item ->
                    Text(item, modifier = Modifier.padding(8.dp))
                }
            }

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Напиши сообщение") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (message.isNotBlank()) {
                        val sent = message
                        chat = chat + "Ты: $sent" +
                            "Я: Спасибо, что написал мне! 💜"
                        message = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Отправить")
            }
        }
    }
}
