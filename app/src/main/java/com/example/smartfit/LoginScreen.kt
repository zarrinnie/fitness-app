package com.example.smartfit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Hey there,", fontSize = 16.sp, color = Color.Black)
        Text(text = "Welcome Back", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        Spacer(modifier = Modifier.height(30.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email", color = Color(0xFFADA4A5)) },
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null, tint = Color(0xFF7B6F72)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF92A3FD),
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color(0xFFF7F8F8),
                unfocusedContainerColor = Color(0xFFF7F8F8),
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password", color = Color(0xFFADA4A5)) },
            leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = null, tint = Color(0xFF7B6F72)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_view),
                    contentDescription = null,
                    tint = Color(0xFFADA4A5)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF92A3FD),
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color(0xFFF7F8F8),
                unfocusedContainerColor = Color(0xFFF7F8F8),
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Forgot your password?",
            fontSize = 12.sp,
            color = Color(0xFFADA4A5),
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = Modifier.weight(1f))

        // Gradient Login Button
        Button(
            onClick = { /* Handle Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF92A3FD), Color(0xFF9DCEFF))
                    ),
                    shape = RoundedCornerShape(30.dp)
                ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_media_play),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Divider
        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Color(0xFFDDDADA))
            Text(text = " Or ", fontSize = 12.sp, modifier = Modifier.padding(horizontal = 10.dp))
            HorizontalDivider(modifier = Modifier.weight(1f), thickness = 1.dp, color = Color(0xFFDDDADA))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Social Login Buttons
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            SocialBox(android.R.drawable.ic_menu_info_details)
            SocialBox(android.R.drawable.ic_menu_share)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Registration Link
        Row {
            Text(text = "Don't have an account yet? ", fontSize = 14.sp)
            Text(
                text = "Register",
                fontSize = 14.sp,
                color = Color(0xFFC58BF2),
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun SocialBox(iconRes: Int) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .border(1.dp, Color(0xFFDDDADA), RoundedCornerShape(14.dp))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource(id = iconRes), contentDescription = null, tint = Color.Unspecified)
    }
}