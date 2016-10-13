package com.github.fabriciofx.dw;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Locale;

public final class Browser {
	private static final String H2_BROWSER = "h2.browser";

	public Browser() {
	}

	public void open(final String url) throws IOException {
		try {
			String osName = toLowerEnglish(getProperty("os.name", "linux"));
			Runtime rt = Runtime.getRuntime();
			String browser = getProperty(H2_BROWSER, null);
			if (browser == null) {
				// under Linux, this will point to the default system browser
				try {
					browser = System.getenv("BROWSER");
				} catch (SecurityException se) {
					// ignore
				}
			}
			if (browser != null) {
				if (browser.startsWith("call:")) {
					browser = browser.substring("call:".length());
					callStaticMethod(browser, url);
				} else if (browser.contains("%url")) {
					String[] args = arraySplit(browser, ',', false);
					for (int i = 0; i < args.length; i++) {
						args[i] = replaceAll(args[i], "%url", url);
					}
					rt.exec(args);
				} else if (osName.contains("windows")) {
					rt.exec(new String[] { "cmd.exe", "/C", browser, url });
				} else {
					rt.exec(new String[] { browser, url });
				}
				return;
			}
			try {
				Class<?> desktopClass = Class.forName("java.awt.Desktop");
				// Desktop.isDesktopSupported()
				Boolean supported = (Boolean) desktopClass.getMethod("isDesktopSupported").invoke(null, new Object[0]);
				URI uri = new URI(url);
				if (supported) {
					// Desktop.getDesktop();
					Object desktop = desktopClass.getMethod("getDesktop").invoke(null, new Object[0]);
					// desktop.browse(uri);
					desktopClass.getMethod("browse", URI.class).invoke(desktop, uri);
					return;
				}
			} catch (Exception e) {
				// ignore
			}
			if (osName.contains("windows")) {
				rt.exec(new String[] { "rundll32", "url.dll,FileProtocolHandler", url });
			} else if (osName.contains("mac") || osName.contains("darwin")) {
				// Mac OS: to open a page with Safari, use "open -a Safari"
				Runtime.getRuntime().exec(new String[] { "open", url });
			} else {
				String[] browsers = { "xdg-open", "chromium", "google-chrome", "firefox", "mozilla-firefox", "mozilla",
						"konqueror", "netscape", "opera", "midori" };
				boolean ok = false;
				for (String b : browsers) {
					try {
						rt.exec(new String[] { b, url });
						ok = true;
						break;
					} catch (Exception e) {
						// ignore and try the next
					}
				}
				if (!ok) {
					// No success in detection.
					throw new IOException("Browser detection failed and system property " + H2_BROWSER + " not set");
				}
			}
		} catch (Exception e) {
			throw new IOException("Failed to start a browser to open the URL " + url + ": " + e.getMessage());
		}
	}

	private static String getProperty(String key, String defaultValue) {
		try {
			return System.getProperty(key, defaultValue);
		} catch (SecurityException se) {
			return defaultValue;
		}
	}

	private static String toLowerEnglish(String s) {
		return s.toLowerCase(Locale.ENGLISH);
	}

	private static Object callStaticMethod(String classAndMethod, Object... params) throws Exception {
		int lastDot = classAndMethod.lastIndexOf('.');
		String className = classAndMethod.substring(0, lastDot);
		String methodName = classAndMethod.substring(lastDot + 1);
		return callMethod(null, Class.forName(className), methodName, params);
	}

	private static Object callMethod(Object instance, Class<?> clazz, String methodName, Object... params)
			throws Exception {
		Method best = null;
		int bestMatch = 0;
		boolean isStatic = instance == null;
		for (Method m : clazz.getMethods()) {
			if (Modifier.isStatic(m.getModifiers()) == isStatic && m.getName().equals(methodName)) {
				int p = match(m.getParameterTypes(), params);
				if (p > bestMatch) {
					bestMatch = p;
					best = m;
				}
			}
		}
		if (best == null) {
			throw new NoSuchMethodException(methodName);
		}
		return best.invoke(instance, params);
	}

	private static int match(Class<?>[] params, Object[] values) {
		int len = params.length;
		if (len == values.length) {
			int points = 1;
			for (int i = 0; i < len; i++) {
				Class<?> pc = getNonPrimitiveClass(params[i]);
				Object v = values[i];
				Class<?> vc = v == null ? null : v.getClass();
				if (pc == vc) {
					points++;
				} else if (vc == null) {
					// can't verify
				} else if (!pc.isAssignableFrom(vc)) {
					return 0;
				}
			}
			return points;
		}
		return 0;
	}

	private static Class<?> getNonPrimitiveClass(Class<?> clazz) {
		if (!clazz.isPrimitive()) {
			return clazz;
		} else if (clazz == boolean.class) {
			return Boolean.class;
		} else if (clazz == byte.class) {
			return Byte.class;
		} else if (clazz == char.class) {
			return Character.class;
		} else if (clazz == double.class) {
			return Double.class;
		} else if (clazz == float.class) {
			return Float.class;
		} else if (clazz == int.class) {
			return Integer.class;
		} else if (clazz == long.class) {
			return Long.class;
		} else if (clazz == short.class) {
			return Short.class;
		} else if (clazz == void.class) {
			return Void.class;
		}
		return clazz;
	}

	private static String[] arraySplit(String s, char separatorChar, boolean trim) {
		if (s == null) {
			return null;
		}
		int length = s.length();
		if (length == 0) {
			return new String[0];
		}
		ArrayList<String> list = new ArrayList<>(4);
		StringBuilder buff = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			char c = s.charAt(i);
			if (c == separatorChar) {
				String e = buff.toString();
				list.add(trim ? e.trim() : e);
				buff.setLength(0);
			} else if (c == '\\' && i < length - 1) {
				buff.append(s.charAt(++i));
			} else {
				buff.append(c);
			}
		}
		String e = buff.toString();
		list.add(trim ? e.trim() : e);
		String[] array = new String[list.size()];
		list.toArray(array);
		return array;
	}

	private static String replaceAll(String s, String before, String after) {
		int next = s.indexOf(before);
		if (next < 0) {
			return s;
		}
		StringBuilder buff = new StringBuilder(s.length() - before.length() + after.length());
		int index = 0;
		while (true) {
			buff.append(s.substring(index, next)).append(after);
			index = next + before.length();
			next = s.indexOf(before, index);
			if (next < 0) {
				buff.append(s.substring(index));
				break;
			}
		}
		return buff.toString();
	}
}
