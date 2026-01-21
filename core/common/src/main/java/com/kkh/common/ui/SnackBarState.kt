package com.kkh.common.ui

sealed class SnackBarState {
    abstract val message: String

    data class TextOnly(override val message: String) : SnackBarState()
    data class Info(override val message: String) : SnackBarState()

    companion object {
        private const val INFO_TYPE = "Info"
        private const val TEXT_ONLY_TYPE = "TextOnly"
        private const val DELIMITERS = "/"

        fun fromString(raw: String): SnackBarState {
            val (msg, typeStr) = raw.split(DELIMITERS).let {
                if (it.size >= 2) it[0] to it[1] else raw to TEXT_ONLY_TYPE
            }

            return when (typeStr) {
                INFO_TYPE -> Info(msg)
                else -> TextOnly(msg)
            }
        }

        fun toRaw(state: SnackBarState): String {
            val type = when (state) {
                is TextOnly -> TEXT_ONLY_TYPE
                is Info -> INFO_TYPE
            }

            return "${state.message}${DELIMITERS}${type}"
        }
    }
}