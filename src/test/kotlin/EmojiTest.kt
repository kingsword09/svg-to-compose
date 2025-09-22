package io.github.kingsword09.svg2compose

import io.github.kingsword09.svg2compose.Svg2Compose
import io.github.kingsword09.svg2compose.VectorType
import java.io.File


fun main(){
    val iconTest = File("/Users/marioferreiravilanova/Documents/Workspace/kotlin/svg-to-compose/src/test/icons")
    val src = File("/Users/marioferreiravilanova/Documents/Workspace/kotlin/svg-to-compose/src/test/results").apply { mkdirs() }

    Svg2Compose.parse(
        applicationIconPackage = "com.test",
        accessorName = "Icons",
        outputSourceDirectory = src,
        vectorsDirectory = iconTest,
        type = VectorType.SVG,
        iconNameTransformer = { name, group ->
            name.split("-").joinToString(separator = "").removePrefix(group)
        },
        allAssetsPropertyName = "AllIcons",
        generatePreview = true,
    )
}
