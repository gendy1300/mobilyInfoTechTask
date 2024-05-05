package com.gendy.bugIt.utils

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gendy.bugIt.R
import com.gendy.bugIt.utils.theme.BlueColor
import com.gendy.bugIt.utils.theme.ErrorRedColor
import com.gendy.bugIt.utils.theme.poppinsFonts

@Composable
fun SnackBarLayout(message: String) {
    Box(
        modifier = Modifier
            .background(color = ErrorRedColor, shape = RoundedCornerShape(10.dp))
            .defaultMinSize(minHeight = 50.dp)
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BugItText(text = message, color = Color.White)
    }
}


@Composable
fun BugItText(
    modifier: Modifier = Modifier,
    text: String,
    fontFamily: androidx.compose.ui.text.font.FontFamily = poppinsFonts,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 13.sp,
    style: TextStyle = TextStyle(
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    color: Color = Color.Black,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = text,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        modifier = modifier,
        style = style,
        color = color,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        lineHeight = lineHeight,
        minLines = minLines,
        maxLines = maxLines
    )

}


@Composable
fun LoadingComponent() {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.5f))
            .clickable(interactionSource = interactionSource, indication = null) {

            },
        contentAlignment = Alignment.Center
    ) {
        LoadingAnimation()
    }
}


@Composable
fun LoadingAnimation(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_mint))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier,
        contentScale = ContentScale.FillBounds,
        clipToCompositionBounds = true
    )
}


@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    title: String,
    isElevated: Boolean = true,
    onBackClicked: (() -> Unit)? = null,
) {

    ElevatedCard(
        modifier = modifier
            .height(108.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isElevated) 3.dp else 0.dp),
        shape = RoundedCornerShape(0),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Margin(57.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            val centerModifier =
                Modifier.align(if (onBackClicked != null) Alignment.Center else Alignment.CenterStart)
            onBackClicked?.let {
                BackIcon(
                    onClick = it,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }


            BugItText(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = centerModifier
            )

        }
    }
}


@Composable
fun BackIcon(modifier: Modifier = Modifier, iconTint: Color = Color.Black, onClick: () -> Unit) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Icon(
        painter = painterResource(id = R.drawable.back_icon),
        contentDescription = null,
        modifier = modifier
            .size(32.dp)
            .noRippleClickable {
                onBackPressedDispatcher?.onBackPressed()
                onClick()
            },
        tint = iconTint
    )
}


@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
    ) {
        onClick()
    }

@Composable
fun Margin(height: Dp = 0.dp, width: Dp = 0.dp) {
    Spacer(
        modifier = Modifier
            .height(height)
            .width(width)
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BugItTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isInInFocus: ((isInFocus: Boolean) -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    minLines: Int = 1,
    isEnabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,

        ),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    textStyle: TextStyle = TextStyle(
        fontSize = 12.sp,
    ),
    backgroundColor: Color = Color.White,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp)
) {

    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        minLines = minLines,
        modifier = modifier
            .background(
                shape = shape,
                color = backgroundColor
            )
            .border(
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp),
                width = 1.dp
            )
            .height(44.dp)
            .onFocusChanged { isInFocused ->
                isInInFocus?.let {
                    isInInFocus(isInFocused.isFocused)
                }

            },
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        enabled = isEnabled,
        singleLine = singleLine,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
    ) { innerTextField ->

        TextFieldDefaults.TextFieldDecorationBox(
            value = value,
            visualTransformation = visualTransformation,
            innerTextField = innerTextField,
            singleLine = singleLine,
            enabled = isEnabled,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 13.dp),
            label = label,
            trailingIcon = trailingIcon,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            colors = colors,
        )
    }
}


@Composable
fun LabelText(text: String) {
    BugItText(
        text = text,
        fontSize = 10.sp,
        color = Color.Gray,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BugItButton(modifier: Modifier = Modifier,enabled:Boolean = true, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = BlueColor),
        onClick = onClick,
        enabled = enabled
    ) {
        BugItText(text = text, color = Color.White)
    }
}
