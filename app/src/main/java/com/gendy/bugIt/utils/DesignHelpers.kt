package com.gendy.bugIt.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.gendy.bugIt.R
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