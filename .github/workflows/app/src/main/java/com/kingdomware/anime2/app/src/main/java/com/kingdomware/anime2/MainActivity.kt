package com.kingdomware.anime2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Color(0xFFBB86FC),
                    background = Color(0xFF121212),
                    surface = Color(0xFF1E1E1E)
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

@Composable
fun GameScreen() {
    var gameState by remember { mutableStateOf(ResourceState()) }
    var currentCardIndex by remember { mutableStateOf(0) }
    val cards = remember { GameRepository.sampleCards.shuffled() }

    val currentCard = cards[currentCardIndex % cards.size]

    fun makeChoice(choice: Choice) {
        gameState = ResourceState(
            mana = (gameState.mana + choice.manaEffect).coerceIn(0, 100),
            loyalty = (gameState.loyalty + choice.loyaltyEffect).coerceIn(0, 100),
            gold = (gameState.gold + choice.goldEffect).coerceIn(0, 100),
            power = (gameState.power + choice.powerEffect).coerceIn(0, 100)
        )
        currentCardIndex++
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Kingdom Ware 2: Anime", fontSize = 24.sp, fontWeight = Bold, color = Color(0xFFBB86FC))

        if (gameState.isGameOver()) {
            Text(text = "💀 ${gameState.getDeathReason()}", color = Color.Red, fontSize = 18.sp, textAlign = TextAlign.Center)
        } else {
            Card(modifier = Modifier.fillMaxWidth().weight(1f).padding(vertical = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "${currentCard.characterName}: ${currentCard.text}", color = Color.White, fontSize = 18.sp, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = { makeChoice(currentCard.rightChoice) }, modifier = Modifier.fillMaxWidth()) {
                        Text(currentCard.rightChoice.text)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { makeChoice(currentCard.leftChoice) }, modifier = Modifier.fillMaxWidth()) {
                        Text(currentCard.leftChoice.text)
                    }
                }
            }
        }
    }
}
