package com.kkh.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkh.common.ui.TestBottomSheetScaffoldState
import com.kkh.common.ui.rememberTestBottomSheetScaffoldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestBottomSheet(
    bottomSheetState: TestBottomSheetScaffoldState,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    BottomSheetScaffold(
        scaffoldState = bottomSheetState.sheetState,
        sheetDragHandle = null,
        sheetContent = {
            Column(modifier = Modifier.navigationBarsPadding()) {
                Spacer(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(28.dp),
                )

                bottomSheetState.content.invoke()
            }
        },
        snackbarHost = { SnackbarHost(bottomSheetState.sheetState.snackbarHostState) },
    ) { paddingValues ->
        content(paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun TestBottomSheetPreview() {
    val testBottomSheetState = rememberTestBottomSheetScaffoldState()

    LaunchedEffect(Unit) {
        testBottomSheetState.sheetState.bottomSheetState.expand()
        testBottomSheetState.updateContent {
            Column(
                Modifier
                    .fillMaxWidth(),
            ) {
                Text("Bottom Sheet Preview", color = Color.Black)
                Spacer(Modifier.height(148.dp))
            }
        }
    }

    TestBottomSheet(
        bottomSheetState = testBottomSheetState,
        content = { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFF000000)),
            ) {
                Text(
                    text = "Main Content",
                    color = Color.White,
                    modifier = Modifier.padding(24.dp),
                )
            }
        },
    )
}