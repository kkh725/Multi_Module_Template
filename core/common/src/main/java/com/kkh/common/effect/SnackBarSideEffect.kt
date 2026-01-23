package com.kkh.common.effect

import android.content.Context
import androidx.annotation.StringRes
import com.kkh.common.base.UiEffect

sealed interface SnackBarSideEffect : UiEffect {
    val resId: Int

    data class TextOnly(
        @param:StringRes override val resId: Int,
    ) : SnackBarSideEffect

    data class Info(
        @param:StringRes override val resId: Int,
    ) : SnackBarSideEffect

    data class Matching(
        @param:StringRes override val resId: Int,
        val count: Int? = null,
    ) : SnackBarSideEffect
}

fun SnackBarSideEffect.handle(
    onInfo: (resId: Int) -> Unit = {},
    onTextOnly: (resId: Int) -> Unit = {},
    onMatching: (resId: Int, count: Int?) -> Unit = { _, _ -> },
) {
    when (this) {
        is SnackBarSideEffect.Info -> onInfo(resId)
        is SnackBarSideEffect.TextOnly -> onTextOnly(resId)
        is SnackBarSideEffect.Matching -> onMatching(resId, count)
    }
}

fun SnackBarSideEffect.getMessage(context: Context): String =
    when (this) {
        is SnackBarSideEffect.Matching -> {
            if (count != null) {
                context.getString(resId, count)
            } else {
                context.getString(resId)
            }
        }
        else -> context.getString(resId)
    }