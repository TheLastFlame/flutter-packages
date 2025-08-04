package io.material.plugins.dynamic_color

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.material.color.DynamicColors
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding

class DynamicColorPlugin : FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel: MethodChannel

  private lateinit var binding: FlutterPluginBinding

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "io.material.plugins/dynamic_color")
    channel.setMethodCallHandler(this)
    this.binding = flutterPluginBinding
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    when (call.method) {
      "getCorePalette" -> {
        if (DynamicColors.isDynamicColorAvailable()) {
          val resources: Resources = binding.applicationContext.resources
          result.success(getCorePalette(resources))
        } else {
          result.success(null)
        }
      }
      "getSystemColors" -> {
        if (DynamicColors.isDynamicColorAvailable()) {
          val resources: Resources = binding.applicationContext.resources
          val brightness = call.argument<String>("brightness") ?: "light"
          result.success(getSystemColors(resources, brightness))
        } else {
          result.success(null)
        }
      }

      else -> result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  @RequiresApi(Build.VERSION_CODES.S)
  private fun getCorePalette(resources: Resources): IntArray {
    return intArrayOf(
      // Primary tonal palette.
      resources.getColor(android.R.color.system_accent1_1000, null),
      resources.getColor(android.R.color.system_accent1_900, null),
      resources.getColor(android.R.color.system_accent1_800, null),
      resources.getColor(android.R.color.system_accent1_700, null),
      resources.getColor(android.R.color.system_accent1_600, null),
      resources.getColor(android.R.color.system_accent1_500, null),
      resources.getColor(android.R.color.system_accent1_400, null),
      resources.getColor(android.R.color.system_accent1_300, null),
      resources.getColor(android.R.color.system_accent1_200, null),
      resources.getColor(android.R.color.system_accent1_100, null),
      resources.getColor(android.R.color.system_accent1_50, null),
      resources.getColor(android.R.color.system_accent1_10, null),
      resources.getColor(android.R.color.system_accent1_0, null),
      // Secondary tonal palette.
      resources.getColor(android.R.color.system_accent2_1000, null),
      resources.getColor(android.R.color.system_accent2_900, null),
      resources.getColor(android.R.color.system_accent2_800, null),
      resources.getColor(android.R.color.system_accent2_700, null),
      resources.getColor(android.R.color.system_accent2_600, null),
      resources.getColor(android.R.color.system_accent2_500, null),
      resources.getColor(android.R.color.system_accent2_400, null),
      resources.getColor(android.R.color.system_accent2_300, null),
      resources.getColor(android.R.color.system_accent2_200, null),
      resources.getColor(android.R.color.system_accent2_100, null),
      resources.getColor(android.R.color.system_accent2_50, null),
      resources.getColor(android.R.color.system_accent2_10, null),
      resources.getColor(android.R.color.system_accent2_0, null),
      // Tertiary tonal palette.
      resources.getColor(android.R.color.system_accent3_1000, null),
      resources.getColor(android.R.color.system_accent3_900, null),
      resources.getColor(android.R.color.system_accent3_800, null),
      resources.getColor(android.R.color.system_accent3_700, null),
      resources.getColor(android.R.color.system_accent3_600, null),
      resources.getColor(android.R.color.system_accent3_500, null),
      resources.getColor(android.R.color.system_accent3_400, null),
      resources.getColor(android.R.color.system_accent3_300, null),
      resources.getColor(android.R.color.system_accent3_200, null),
      resources.getColor(android.R.color.system_accent3_100, null),
      resources.getColor(android.R.color.system_accent3_50, null),
      resources.getColor(android.R.color.system_accent3_10, null),
      resources.getColor(android.R.color.system_accent3_0, null),
      // Neutral tonal palette.
      resources.getColor(android.R.color.system_neutral1_1000, null),
      resources.getColor(android.R.color.system_neutral1_900, null),
      resources.getColor(android.R.color.system_neutral1_800, null),
      resources.getColor(android.R.color.system_neutral1_700, null),
      resources.getColor(android.R.color.system_neutral1_600, null),
      resources.getColor(android.R.color.system_neutral1_500, null),
      resources.getColor(android.R.color.system_neutral1_400, null),
      resources.getColor(android.R.color.system_neutral1_300, null),
      resources.getColor(android.R.color.system_neutral1_200, null),
      resources.getColor(android.R.color.system_neutral1_100, null),
      resources.getColor(android.R.color.system_neutral1_50, null),
      resources.getColor(android.R.color.system_neutral1_10, null),
      resources.getColor(android.R.color.system_neutral1_0, null),
      // Neutral variant tonal palette.
      resources.getColor(android.R.color.system_neutral2_1000, null),
      resources.getColor(android.R.color.system_neutral2_900, null),
      resources.getColor(android.R.color.system_neutral2_800, null),
      resources.getColor(android.R.color.system_neutral2_700, null),
      resources.getColor(android.R.color.system_neutral2_600, null),
      resources.getColor(android.R.color.system_neutral2_500, null),
      resources.getColor(android.R.color.system_neutral2_400, null),
      resources.getColor(android.R.color.system_neutral2_300, null),
      resources.getColor(android.R.color.system_neutral2_200, null),
      resources.getColor(android.R.color.system_neutral2_100, null),
      resources.getColor(android.R.color.system_neutral2_50, null),
      resources.getColor(android.R.color.system_neutral2_10, null),
      resources.getColor(android.R.color.system_neutral2_0, null),
    );
  }

  @RequiresApi(Build.VERSION_CODES.S)
  private fun getSystemColors(resources: Resources, brightness: String): Map<String, Int>? {
    return try {
      if (brightness == "dark") {
        val baseColors = mapOf(
          // Primary colors
          "primary" to resources.getColor(android.R.color.system_primary_dark, null),
          "onPrimary" to resources.getColor(android.R.color.system_on_primary_dark, null),
          "primaryContainer" to resources.getColor(android.R.color.system_primary_container_dark, null),
          "onPrimaryContainer" to resources.getColor(android.R.color.system_on_primary_container_dark, null),

          // Secondary colors
          "secondary" to resources.getColor(android.R.color.system_secondary_dark, null),
          "onSecondary" to resources.getColor(android.R.color.system_on_secondary_dark, null),
          "secondaryContainer" to resources.getColor(android.R.color.system_secondary_container_dark, null),
          "onSecondaryContainer" to resources.getColor(android.R.color.system_on_secondary_container_dark, null),

          // Tertiary colors
          "tertiary" to resources.getColor(android.R.color.system_tertiary_dark, null),
          "onTertiary" to resources.getColor(android.R.color.system_on_tertiary_dark, null),
          "tertiaryContainer" to resources.getColor(android.R.color.system_tertiary_container_dark, null),
          "onTertiaryContainer" to resources.getColor(android.R.color.system_on_tertiary_container_dark, null),

          // Error colors
          "error" to resources.getColor(android.R.color.system_error_dark, null),
          "onError" to resources.getColor(android.R.color.system_on_error_dark, null),
          "errorContainer" to resources.getColor(android.R.color.system_error_container_dark, null),
          "onErrorContainer" to resources.getColor(android.R.color.system_on_error_container_dark, null),

          // Surface colors
          "surface" to resources.getColor(android.R.color.system_surface_dark, null),
          "onSurface" to resources.getColor(android.R.color.system_on_surface_dark, null),
          "surfaceVariant" to resources.getColor(android.R.color.system_surface_variant_dark, null),
          "onSurfaceVariant" to resources.getColor(android.R.color.system_on_surface_variant_dark, null),
          "surfaceBright" to resources.getColor(android.R.color.system_surface_bright_dark, null),
          "surfaceDim" to resources.getColor(android.R.color.system_surface_dim_dark, null),
          "surfaceContainer" to resources.getColor(android.R.color.system_surface_container_dark, null),
          "surfaceContainerHigh" to resources.getColor(android.R.color.system_surface_container_high_dark, null),
          "surfaceContainerHighest" to resources.getColor(android.R.color.system_surface_container_highest_dark, null),
          "surfaceContainerLow" to resources.getColor(android.R.color.system_surface_container_low_dark, null),
          "surfaceContainerLowest" to resources.getColor(android.R.color.system_surface_container_lowest_dark, null),

          // Background colors
          "background" to resources.getColor(android.R.color.system_background_dark, null),
          "onBackground" to resources.getColor(android.R.color.system_on_background_dark, null),

          // Inverse colors
          "inverseSurface" to resources.getColor(android.R.color.system_surface_light, null),
          "onInverseSurface" to resources.getColor(android.R.color.system_on_surface_light, null),
          "inversePrimary" to resources.getColor(android.R.color.system_primary_light, null),

          // Outline colors
          "outline" to resources.getColor(android.R.color.system_outline_dark, null),
          "outlineVariant" to resources.getColor(android.R.color.system_outline_variant_dark, null),

          // Shadow and scrim
          "shadow" to resources.getColor(android.R.color.system_neutral1_1000, null),
          "scrim" to resources.getColor(android.R.color.system_neutral1_1000, null),

          // Surface tint
          "surfaceTint" to resources.getColor(android.R.color.system_primary_dark, null)
        )

        // Add fixed colors if available (API 34+)
        val fixedColors = try {
          mapOf(
            "primaryFixed" to resources.getColor(android.R.color.system_primary_fixed, null),
            "primaryFixedDim" to resources.getColor(android.R.color.system_primary_fixed_dim, null),
            "onPrimaryFixed" to resources.getColor(android.R.color.system_on_primary_fixed, null),
            "onPrimaryFixedVariant" to resources.getColor(android.R.color.system_on_primary_fixed_variant, null),
            "secondaryFixed" to resources.getColor(android.R.color.system_secondary_fixed, null),
            "secondaryFixedDim" to resources.getColor(android.R.color.system_secondary_fixed_dim, null),
            "onSecondaryFixed" to resources.getColor(android.R.color.system_on_secondary_fixed, null),
            "onSecondaryFixedVariant" to resources.getColor(android.R.color.system_on_secondary_fixed_variant, null),
            "tertiaryFixed" to resources.getColor(android.R.color.system_tertiary_fixed, null),
            "tertiaryFixedDim" to resources.getColor(android.R.color.system_tertiary_fixed_dim, null),
            "onTertiaryFixed" to resources.getColor(android.R.color.system_on_tertiary_fixed, null),
            "onTertiaryFixedVariant" to resources.getColor(android.R.color.system_on_tertiary_fixed_variant, null)
          )
        } catch (e: Exception) {
          emptyMap()
        }

        baseColors + fixedColors
      } else {
        val baseColors = mapOf(
          // Primary colors
          "primary" to resources.getColor(android.R.color.system_primary_light, null),
          "onPrimary" to resources.getColor(android.R.color.system_on_primary_light, null),
          "primaryContainer" to resources.getColor(android.R.color.system_primary_container_light, null),
          "onPrimaryContainer" to resources.getColor(android.R.color.system_on_primary_container_light, null),

          // Secondary colors
          "secondary" to resources.getColor(android.R.color.system_secondary_light, null),
          "onSecondary" to resources.getColor(android.R.color.system_on_secondary_light, null),
          "secondaryContainer" to resources.getColor(android.R.color.system_secondary_container_light, null),
          "onSecondaryContainer" to resources.getColor(android.R.color.system_on_secondary_container_light, null),

          // Tertiary colors
          "tertiary" to resources.getColor(android.R.color.system_tertiary_light, null),
          "onTertiary" to resources.getColor(android.R.color.system_on_tertiary_light, null),
          "tertiaryContainer" to resources.getColor(android.R.color.system_tertiary_container_light, null),
          "onTertiaryContainer" to resources.getColor(android.R.color.system_on_tertiary_container_light, null),

          // Error colors
          "error" to resources.getColor(android.R.color.system_error_light, null),
          "onError" to resources.getColor(android.R.color.system_on_error_light, null),
          "errorContainer" to resources.getColor(android.R.color.system_error_container_light, null),
          "onErrorContainer" to resources.getColor(android.R.color.system_on_error_container_light, null),

          // Surface colors
          "surface" to resources.getColor(android.R.color.system_surface_light, null),
          "onSurface" to resources.getColor(android.R.color.system_on_surface_light, null),
          "surfaceVariant" to resources.getColor(android.R.color.system_surface_variant_light, null),
          "onSurfaceVariant" to resources.getColor(android.R.color.system_on_surface_variant_light, null),
          "surfaceBright" to resources.getColor(android.R.color.system_surface_bright_light, null),
          "surfaceDim" to resources.getColor(android.R.color.system_surface_dim_light, null),
          "surfaceContainer" to resources.getColor(android.R.color.system_surface_container_light, null),
          "surfaceContainerHigh" to resources.getColor(android.R.color.system_surface_container_high_light, null),
          "surfaceContainerHighest" to resources.getColor(android.R.color.system_surface_container_highest_light, null),
          "surfaceContainerLow" to resources.getColor(android.R.color.system_surface_container_low_light, null),
          "surfaceContainerLowest" to resources.getColor(android.R.color.system_surface_container_lowest_light, null),

          // Background colors
          "background" to resources.getColor(android.R.color.system_background_light, null),
          "onBackground" to resources.getColor(android.R.color.system_on_background_light, null),

          // Inverse colors
          "inverseSurface" to resources.getColor(android.R.color.system_surface_dark, null),
          "onInverseSurface" to resources.getColor(android.R.color.system_on_surface_dark, null),
          "inversePrimary" to resources.getColor(android.R.color.system_primary_dark, null),

          // Outline colors
          "outline" to resources.getColor(android.R.color.system_outline_light, null),
          "outlineVariant" to resources.getColor(android.R.color.system_outline_variant_light, null),

          // Shadow and scrim
          "shadow" to resources.getColor(android.R.color.system_neutral1_1000, null),
          "scrim" to resources.getColor(android.R.color.system_neutral1_1000, null),

          // Surface tint
          "surfaceTint" to resources.getColor(android.R.color.system_primary_light, null)
        )

        // Add fixed colors if available (API 34+)
        val fixedColors = try {
          mapOf(
            "primaryFixed" to resources.getColor(android.R.color.system_primary_fixed, null),
            "primaryFixedDim" to resources.getColor(android.R.color.system_primary_fixed_dim, null),
            "onPrimaryFixed" to resources.getColor(android.R.color.system_on_primary_fixed, null),
            "onPrimaryFixedVariant" to resources.getColor(android.R.color.system_on_primary_fixed_variant, null),
            "secondaryFixed" to resources.getColor(android.R.color.system_secondary_fixed, null),
            "secondaryFixedDim" to resources.getColor(android.R.color.system_secondary_fixed_dim, null),
            "onSecondaryFixed" to resources.getColor(android.R.color.system_on_secondary_fixed, null),
            "onSecondaryFixedVariant" to resources.getColor(android.R.color.system_on_secondary_fixed_variant, null),
            "tertiaryFixed" to resources.getColor(android.R.color.system_tertiary_fixed, null),
            "tertiaryFixedDim" to resources.getColor(android.R.color.system_tertiary_fixed_dim, null),
            "onTertiaryFixed" to resources.getColor(android.R.color.system_on_tertiary_fixed, null),
            "onTertiaryFixedVariant" to resources.getColor(android.R.color.system_on_tertiary_fixed_variant, null)
          )
        } catch (e: Exception) {
          emptyMap()
        }

        baseColors + fixedColors
      }
    } catch (e: Exception) {
      null
    }
  }
}
