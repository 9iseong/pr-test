package com.proj.prserver.common

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

inline fun getStrURLDecode(value: String): String = URLDecoder.decode(value, StandardCharsets.UTF_8.toString())

