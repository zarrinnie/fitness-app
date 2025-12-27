package com.example.smartfit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartfit.ui.theme.SmartFitTheme

class Register3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartFitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Register3Screen()
                }
            }
        }
    }
}

@Composable
fun Register3Screen() {

    val context = LocalContext.current

    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text("Hey there,", fontSize = 16.sp, color = Color(0xFF1D1617))

        Text(
            "Create an Account",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D1617)
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = "First Name",
            icon = Icons.Filled.Person
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = "Last Name",
            icon = Icons.Filled.Person
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            icon = Icons.Filled.Email
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            icon = Icons.Filled.Lock,
            isPassword = true,
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        TermsAndConditionsCheckbox(
            isChecked = isChecked,
            onCheckedChange = { isChecked = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                context.startActivity(
                    Intent(context, LoginScreen::class.java)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF9DCEFF),
                                Color(0xFF92A3FD)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Register",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        LoginRedirect()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordVisibilityChange: (Boolean) -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = { Text(label, color = Color.Gray) },
        leadingIcon = { Icon(icon, contentDescription = label) },
        trailingIcon = {
            if (isPassword) {
                IconButton(
                    onClick = {
                        onPasswordVisibilityChange(!passwordVisible)
                    }
                ) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        },
        visualTransformation =
        if (isPassword && !passwordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF7F8F8),
            unfocusedContainerColor = Color(0xFFF7F8F8),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun TermsAndConditionsCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )

        Spacer(modifier = Modifier.width(8.dp))

        val text = buildAnnotatedString {
            append("By continuing you accept our ")
            pushStyle(SpanStyle(color = Color(0xFF92A3FD)))
            append("Privacy Policy")
            pop()
            append(" and ")
            pushStyle(SpanStyle(color = Color(0xFF92A3FD)))
            append("Terms of Use")
            pop()
        }

        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}

@Composable
fun LoginRedirect() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Already have an account? ")
        Text(
            text = "Login",
            color = Color(0xFF92A3FD),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                // TODO: navigate to Login
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Register3Preview() {
    SmartFitTheme {
        Register3Screen()
    }
}
