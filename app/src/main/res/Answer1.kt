import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e2400069.ui.theme.E2400069Theme

class Answer1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            E2400069Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    GreetScreen()
                }
            }
        }
    }
}

@Composable
fun GreetScreen() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var message by remember { mutableStateOf("") }

    val hangedLettersFamily = FontFamily(Font(R.font.hanged_letters))
    val anniversaryFamily = FontFamily(Font(R.font.anniversary))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB24592),
                        Color(0xFFF15F79)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "WELCOME",
                fontFamily = hangedLettersFamily,
                fontSize = 48.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "to my",
                fontFamily = anniversaryFamily,
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "Identifier",
                fontFamily = anniversaryFamily,
                fontSize = 32.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter Name Please", fontFamily = anniversaryFamily) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedContainerColor = Color.Black.copy(alpha = 0.3f),
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Enter Your Age Please", fontFamily = anniversaryFamily) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedContainerColor = Color.Black.copy(alpha = 0.3f),
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White.copy(alpha = 0.7f),
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White.copy(alpha = 0.7f)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val nameText = name.text.trim()
                    val ageText = age.text.trim()

                    message = when {
                        nameText.isEmpty() || ageText.isEmpty() -> "Please enter both your name and age."
                        ageText.toIntOrNull() == null -> "Please enter a valid number for your age."
                        ageText.toInt() < 0 -> "Age cannot be a negative number."
                        else -> {
                            val ageValue = ageText.toInt()
                            val status = if (ageValue < 18) "you are a minor." else "you are an adult."
                            "Hello, $nameText! $status"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.3f)
                )
            ) {
                Text(
                    text = "Greet Me",
                    fontFamily = anniversaryFamily,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedVisibility(visible = message.isNotEmpty()) {
                Text(
                    text = message,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontFamily = anniversaryFamily,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetScreenPreview() {
    E2400069Theme {
        GreetScreen()
    }
}
