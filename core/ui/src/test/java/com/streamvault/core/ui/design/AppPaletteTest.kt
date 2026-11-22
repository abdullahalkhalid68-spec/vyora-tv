package com.streamvault.core.ui.design

import androidx.compose.ui.graphics.Color
import com.google.common.truth.Truth.assertThat
import com.streamvault.core.ui.theme.standardMaterialColorScheme
import org.junit.Test

class AppPaletteTest {

    @Test
    fun `classic blue preserves the existing palette`() {
        val palette = AppPalette.forTheme("classic_blue")

        assertThat(palette.brand).isEqualTo(Color(0xFF9B8CFF))
        assertThat(palette.canvas).isEqualTo(Color(0xFF0A0B14))
        assertThat(palette.surface).isEqualTo(Color(0xFF151729))
        assertThat(palette.onPrimary).isEqualTo(Color(0xFF0A0B14))
    }

    @Test
    fun `m3 purple uses a distinct primary and neutral surface palette`() {
        val palette = AppPalette.forTheme("m3_purple")

        assertThat(palette.brand).isEqualTo(Color(0xFFD0BCFF))
        assertThat(palette.canvas).isEqualTo(Color(0xFF0A0A0D))
        assertThat(palette.surface).isEqualTo(Color(0xFF141419))
        assertThat(palette.onPrimary).isEqualTo(Color(0xFF381E72))
    }

    @Test
    fun `light theme uses a light surface palette and readable dark text`() {
        val palette = AppPalette.forTheme("light")

        assertThat(palette.canvas).isEqualTo(Color(0xFFF4F7FB))
        assertThat(palette.surface).isEqualTo(Color.White)
        assertThat(palette.brand).isEqualTo(Color(0xFF185FB4))
        assertThat(palette.textPrimary).isEqualTo(Color(0xFF132033))
        assertThat(palette.isLight).isTrue()
    }

    @Test
    fun `unknown theme IDs resolve to classic blue`() {
        assertThat(AppPalette.forTheme("unknown")).isEqualTo(AppPalette.forTheme("classic_blue"))
        assertThat(AppPalette.forTheme(null)).isEqualTo(AppPalette.forTheme("classic_blue"))
    }

    @Test
    fun `standard material controls receive the same semantic palette`() {
        listOf("classic_blue", "m3_purple", "light").forEach { themeId ->
            val palette = AppPalette.forTheme(themeId)
            val scheme = standardMaterialColorScheme(palette)

            assertThat(scheme.primary).isEqualTo(palette.brand)
            assertThat(scheme.surface).isEqualTo(palette.surface)
            assertThat(scheme.onSurface).isEqualTo(palette.textPrimary)
            assertThat(scheme.surfaceVariant).isEqualTo(palette.surfaceElevated)
            assertThat(scheme.onSurfaceVariant).isEqualTo(palette.textSecondary)
        }
    }
}
