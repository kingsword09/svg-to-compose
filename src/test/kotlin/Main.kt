package io.github.kingsword09.svg2compose

import io.github.kingsword09.svg2compose.Svg2Compose
import java.io.File

fun main() {
    val iconTest = File("raw-icons4")
    val src = File("build/generated-icons").apply { mkdirs() }

    Svg2Compose.parse(
        applicationIconPackage = "br.com.compose.icons",
        accessorName = "EvaIcons",
        outputSourceDirectory = src,
        vectorsDirectory = iconTest,
        iconNameTransformer = { name, group ->
            name.removeSuffix(group, ignoreCase = true)
        },
        generatePreview = true,
        generateStringAccessor = true,
    )
}

private fun String.removeSuffix(suffix: String, ignoreCase: Boolean): String {
    return if (ignoreCase) {
        val index = lastIndexOf(suffix, ignoreCase = true)

        if (index > -1) substring(0, index) else this
    } else {
        removeSuffix(suffix)
    }
}