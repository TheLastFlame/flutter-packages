import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:material_color_utilities/material_color_utilities.dart';

/// Plugin for obtaining dynamic colors defined by the Android OS.
class DynamicColorPlugin {
  /// Optional method channel so that it returns null on non-Android platforms.
  static const channel = OptionalMethodChannel(
    'io.material.plugins/dynamic_color',
  );

  /// A method name that the Kotlin plugin listens for.
  static const methodName = 'getCorePalette';

  /// A method name for getting system colors directly from Android.
  static const systemColorsMethodName = 'getSystemColors';

  /// A method name that the macOS plugin listens for.
  static const accentColorMethodName = 'getAccentColor';

  /// Returns the Android OS' dynamic colors asynchronously in a [CorePalette].
  ///
  /// Completes with null on pre-Android S and non-Android platforms.
  ///
  /// See also:
  ///
  ///  * [getCorePalette() example](https://github.com/material-foundation/flutter-packages/tree/main/packages/dynamic_color/example/lib/get_core_palette_example.dart)
  ///  * [DynamicColorBuilder] a convenience stateful builder widget that
  ///  provides the dynamic colors
  static Future<CorePalette?> getCorePalette() async {
    final result = await channel.invokeMethod(methodName);
    return result == null ? null : CorePalette.fromList(result.toList());
  }

  /// Returns the Android OS' dynamic colors asynchronously as a [ColorScheme].
  ///
  /// This method gets the colors directly from the Android system instead of
  /// generating them from a core palette. This ensures perfect color accuracy
  /// with the native Material You implementation.
  ///
  /// Completes with null on pre-Android S and non-Android platforms.
  static Future<ColorScheme?> getSystemColorScheme({
    required Brightness brightness,
  }) async {
    final brightnessString = brightness == Brightness.light ? 'light' : 'dark';
    final result = await channel.invokeMethod(
      systemColorsMethodName,
      {'brightness': brightnessString},
    );

    if (result == null) return null;

    return _createColorSchemeFromSystemColors(result, brightness);
  }

  /// Creates a [ColorScheme] from the system colors map returned by Android.
  static ColorScheme _createColorSchemeFromSystemColors(
    Map<dynamic, dynamic> colors,
    Brightness brightness,
  ) {
    // Debug logging to compare with native app
    if (kDebugMode) {
      debugPrint('dynamic_color: System error color: #${colors['error']?.toRadixString(16).padLeft(8, '0')}');
      debugPrint('dynamic_color: System errorContainer color: #${colors['errorContainer']?.toRadixString(16).padLeft(8, '0')}');
    }
    
    return ColorScheme(
      brightness: brightness,
      primary: Color(colors['primary'] ?? 0xFF6750A4),
      onPrimary: Color(colors['onPrimary'] ?? 0xFFFFFFFF),
      primaryContainer: Color(colors['primaryContainer'] ?? 0xFFEADDFF),
      onPrimaryContainer: Color(colors['onPrimaryContainer'] ?? 0xFF21005D),
      primaryFixed:
          colors['primaryFixed'] != null ? Color(colors['primaryFixed']) : null,
      primaryFixedDim: colors['primaryFixedDim'] != null
          ? Color(colors['primaryFixedDim'])
          : null,
      onPrimaryFixed: colors['onPrimaryFixed'] != null
          ? Color(colors['onPrimaryFixed'])
          : null,
      onPrimaryFixedVariant: colors['onPrimaryFixedVariant'] != null
          ? Color(colors['onPrimaryFixedVariant'])
          : null,
      secondary: Color(colors['secondary'] ?? 0xFF625B71),
      onSecondary: Color(colors['onSecondary'] ?? 0xFFFFFFFF),
      secondaryContainer: Color(colors['secondaryContainer'] ?? 0xFFE8DEF8),
      onSecondaryContainer: Color(colors['onSecondaryContainer'] ?? 0xFF1D192B),
      secondaryFixed: colors['secondaryFixed'] != null
          ? Color(colors['secondaryFixed'])
          : null,
      secondaryFixedDim: colors['secondaryFixedDim'] != null
          ? Color(colors['secondaryFixedDim'])
          : null,
      onSecondaryFixed: colors['onSecondaryFixed'] != null
          ? Color(colors['onSecondaryFixed'])
          : null,
      onSecondaryFixedVariant: colors['onSecondaryFixedVariant'] != null
          ? Color(colors['onSecondaryFixedVariant'])
          : null,
      tertiary: Color(colors['tertiary'] ?? 0xFF7D5260),
      onTertiary: Color(colors['onTertiary'] ?? 0xFFFFFFFF),
      tertiaryContainer: Color(colors['tertiaryContainer'] ?? 0xFFFFD8E4),
      onTertiaryContainer: Color(colors['onTertiaryContainer'] ?? 0xFF31111D),
      tertiaryFixed: colors['tertiaryFixed'] != null
          ? Color(colors['tertiaryFixed'])
          : null,
      tertiaryFixedDim: colors['tertiaryFixedDim'] != null
          ? Color(colors['tertiaryFixedDim'])
          : null,
      onTertiaryFixed: colors['onTertiaryFixed'] != null
          ? Color(colors['onTertiaryFixed'])
          : null,
      onTertiaryFixedVariant: colors['onTertiaryFixedVariant'] != null
          ? Color(colors['onTertiaryFixedVariant'])
          : null,
      error: Color(colors['error'] ?? 0xFFBA1A1A),
      onError: Color(colors['onError'] ?? 0xFFFFFFFF),
      errorContainer: Color(colors['errorContainer'] ?? 0xFFFFDAD6),
      onErrorContainer: Color(colors['onErrorContainer'] ?? 0xFF410002),
      outline: Color(colors['outline'] ?? 0xFF79747E),
      outlineVariant: Color(colors['outlineVariant'] ?? 0xFFCAC4D0),
      surface: Color(colors['surface'] ?? 0xFFFEF7FF),
      onSurface: Color(colors['onSurface'] ?? 0xFF1C1B1F),
      surfaceVariant: Color(colors['surfaceVariant'] ?? 0xFFE7E0EC),
      onSurfaceVariant: Color(colors['onSurfaceVariant'] ?? 0xFF49454F),
      surfaceDim:
          colors['surfaceDim'] != null ? Color(colors['surfaceDim']) : null,
      surfaceBright: colors['surfaceBright'] != null
          ? Color(colors['surfaceBright'])
          : null,
      surfaceContainerLowest: colors['surfaceContainerLowest'] != null
          ? Color(colors['surfaceContainerLowest'])
          : null,
      surfaceContainerLow: colors['surfaceContainerLow'] != null
          ? Color(colors['surfaceContainerLow'])
          : null,
      surfaceContainer: colors['surfaceContainer'] != null
          ? Color(colors['surfaceContainer'])
          : null,
      surfaceContainerHigh: colors['surfaceContainerHigh'] != null
          ? Color(colors['surfaceContainerHigh'])
          : null,
      surfaceContainerHighest: colors['surfaceContainerHighest'] != null
          ? Color(colors['surfaceContainerHighest'])
          : null,
      background: Color(colors['background'] ?? 0xFFFEF7FF),
      onBackground: Color(colors['onBackground'] ?? 0xFF1C1B1F),
      inverseSurface: Color(colors['inverseSurface'] ?? 0xFF313033),
      onInverseSurface: Color(colors['onInverseSurface'] ?? 0xFFF4EFF4),
      inversePrimary: Color(colors['inversePrimary'] ?? 0xFFD0BCFF),
      shadow: Color(colors['shadow'] ?? 0xFF000000),
      scrim: Color(colors['scrim'] ?? 0xFF000000),
      surfaceTint: Color(colors['surfaceTint'] ?? 0xFF6750A4),
    );
  }

  /// Returns the OS' accent color asynchronously as a [Color].
  ///
  /// Supported on macOS starting with 10.14 (Mojave), on Windows starting with
  /// Vista, and on GTK-based Linux desktops.
  ///
  /// See also:
  ///
  /// * [Apple's introduction to macOS accent color](https://developer.apple.com/design/human-interface-guidelines/macos/overview/whats-new-in-macos/#app-accent-colors)
  /// * [macOS's NSColor.controlAccentColor documentation](https://developer.apple.com/documentation/appkit/nscolor/3000782-controlaccentcolor)
  /// * [Windows' accent color](https://docs.microsoft.com/en-us/windows/apps/design/style/color#accent-color)
  /// * [Windows Aero](https://web.archive.org/web/20080812195923/http://www.microsoft.com/windows/windows-vista/features/aero.aspx?tabid=2&catid=4)
  /// * [Change colors in Windows](https://support.microsoft.com/en-us/windows/change-colors-in-windows-d26ef4d6-819a-581c-1581-493cfcc005fe)
  static Future<Color?> getAccentColor() async {
    final result = await channel.invokeMethod(accentColorMethodName);
    return result == null ? null : Color(result);
  }
}
