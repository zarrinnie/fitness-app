package com.example.smartfit

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartfit.ui.theme.SmartFitTheme
import java.util.Calendar


class RegisterProfile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartFitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompleteProfileScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteProfileScreen() {

    // STATES
    var gender by remember { mutableStateOf("") }
    var genderExpanded by remember { mutableStateOf(false) }
    val genderOptions = listOf("Male", "Female", "Other")

    var dateOfBirth by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var isWeightError by remember { mutableStateOf(false) }
    var isHeightError by remember { mutableStateOf(false) }
    var showErrorMessage by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // DATE PICKER DIALOG
    val datePicker = DatePickerDialog(
        context,
        { _, year, month, day ->
            dateOfBirth = "$day/${month + 1}/$year"
            showErrorMessage = false
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8F9))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.register_page_2),
                contentDescription = "Profile Illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Let's complete your profile",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "It will help us to know more about you!",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))


            // -----------------------------------------
            // GENDER DROPDOWN
            // -----------------------------------------
            ExposedDropdownMenuBox(
                expanded = genderExpanded,
                onExpandedChange = { genderExpanded = !genderExpanded }
            ) {
                OutlinedTextField(
                    value = gender,
                    onValueChange = { },
                    readOnly = true,
                    leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Gender") },
                    label = { Text("Choose Gender") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
                ExposedDropdownMenu(
                    expanded = genderExpanded,
                    onDismissRequest = { genderExpanded = false }
                ) {
                    genderOptions.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                gender = it
                                genderExpanded = false
                                showErrorMessage = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            // -----------------------------------------
            // DATE PICKER (TAP FIELD TO OPEN)
            // -----------------------------------------
            Box {
                OutlinedTextField(
                    value = dateOfBirth,
                    onValueChange = { },
                    enabled = false,
                    label = { Text("Date of Birth") },
                    leadingIcon = { Icon(Icons.Filled.DateRange, contentDescription = "Date") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledContainerColor = Color.White,
                        disabledBorderColor = Color.Transparent,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { datePicker.show() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            // -----------------------------------------
            // WEIGHT FIELD + KG BUTTON
            // -----------------------------------------
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                OutlinedTextField(
                    value = weight,
                    onValueChange = {
                        weight = it
                        isWeightError = it.any { char -> !char.isDigit() }
                        showErrorMessage = false
                    },
                    label = { Text("Your Weight") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.MonitorWeight,
                            contentDescription = "Weight"
                        )
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        errorContainerColor = Color.White,
                        errorLeadingIconColor = MaterialTheme.colorScheme.error
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = isWeightError,
                    supportingText = {
                        if (isWeightError) {
                            Text(
                                text = "Please enter only numbers",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .width(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFFC58BF2), Color(0xFF9DCEFF))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("KG", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            // -----------------------------------------
            // HEIGHT FIELD + CM BUTTON
            // -----------------------------------------
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                OutlinedTextField(
                    value = height,
                    onValueChange = {
                        height = it
                        isHeightError = it.any { char -> !char.isDigit() }
                        showErrorMessage = false
                    },
                    label = { Text("Your Height") },
                    leadingIcon = { Icon(Icons.Filled.Straighten, contentDescription = "Height") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        errorContainerColor = Color.White,
                        errorLeadingIconColor = MaterialTheme.colorScheme.error
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = isHeightError,
                    supportingText = {
                        if (isHeightError) {
                            Text(
                                text = "Please enter only numbers",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .width(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFFC58BF2), Color(0xFF9DCEFF))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("CM", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }


            Spacer(modifier = Modifier.weight(1f))

            if (showErrorMessage) {
                Text(
                    text = "Please fill in all fields correctly",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    if (gender.isBlank() || dateOfBirth.isBlank() || weight.isBlank() || height.isBlank() || isWeightError || isHeightError) {
                        showErrorMessage = true
                    } else {
                        showErrorMessage = false
                        // Handle next
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF92A3FD), Color(0xFF9DCEFF))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Next",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                        Icon(Icons.Filled.ArrowForward, contentDescription = "Next", tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompleteProfileScreenPreview() {
    SmartFitTheme {
        CompleteProfileScreen()
    }
}
