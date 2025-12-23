package com.kkh.multimodule.designsystem.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkh.multimodule.designsystem.R
import com.kkh.multimodule.ui.SnackBarState
import kotlinx.coroutines.delay

@Composable
fun TestSnackBar(snackBarData: SnackbarData) {
    val state = SnackBarState.fromString(snackBarData.visuals.message)
    val (imageRes, bgColor) = when (state) {
        is SnackBarState.TextOnly -> null to Color.Gray
        is SnackBarState.Info -> R.drawable.ic_alarm to Color.LightGray
    }

    val (topPadding, bottomPadding, alignment) = when (state) {
        is SnackBarState.TextOnly, is SnackBarState.Info  ->
            Triple(0.dp, 82.dp, Alignment.BottomCenter)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(top = topPadding, bottom = bottomPadding, start = 20.dp, end = 20.dp),
        contentAlignment = alignment
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(bgColor)
                .padding(horizontal = 20.dp, vertical = 8.dp),
        ) {
            imageRes?.let {
                Image(
                    painter = painterResource(it),
                    colorFilter = if (state is SnackBarState.Info) ColorFilter.tint(Color.White) else null,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp),
                )
            }

            Text(
                text = state.message,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun PieceSnackBarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { Snackbar(it) }
) {
    val currentSnackbarData = hostState.currentSnackbarData
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            delay(2000L)
            currentSnackbarData.dismiss()
        }
    }

    Crossfade(
        targetState = hostState.currentSnackbarData,
        modifier = modifier,
        label = "",
        content = { current -> if (current != null) snackbar(current) },
    )
}

@Preview
@Composable
private fun PieceSnackBarPreview() = MaterialTheme {
    TestSnackBar(
        snackBarData = object : SnackbarData {
            override val visuals = object : SnackbarVisuals {
                override val actionLabel = null
                override val duration = SnackbarDuration.Short
                override val message = "텍스트만 있는 스낵바입니다"
                override val withDismissAction = false
            }

            override fun dismiss() {}
            override fun performAction() {}
        },
    )

    TestSnackBar(
        snackBarData = object : SnackbarData {
            override val visuals = object : SnackbarVisuals {
                override val actionLabel = null
                override val duration = SnackbarDuration.Short
                override val message = "매칭과 관련된 스낵바입니다/Matching"
                override val withDismissAction = false
            }

            override fun dismiss() {}
            override fun performAction() {}
        },
    )
}


@Preview
@Composable
private fun PieceInfoSnackBarPreview() = MaterialTheme {
    TestSnackBar(
        snackBarData = object : SnackbarData {
            override val visuals = object : SnackbarVisuals {
                override val actionLabel = null
                override val duration = SnackbarDuration.Short
                override val message = "정보를 알려주는 스낵바입니다/Info"
                override val withDismissAction = false
            }

            override fun dismiss() {}
            override fun performAction() {}
        },
    )
}
