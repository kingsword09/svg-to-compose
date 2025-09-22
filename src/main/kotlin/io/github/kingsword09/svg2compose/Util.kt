package io.github.kingsword09.svg2compose

import java.util.*

fun <T> Stack<T>.peekOrNull(): T? = runCatching { peek() }.getOrNull()