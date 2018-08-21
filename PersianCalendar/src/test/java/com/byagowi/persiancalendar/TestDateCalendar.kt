package com.byagowi.persiancalendar

import calendar.DateConverter
import calendar.IslamicDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TestDateCalendar {
  @Test
  fun islamic_converter_test() {
    arrayOf(
        intArrayOf(2453767, 1427, 1, 1),
        intArrayOf(2455658, 1432, 5, 2),
        intArrayOf(2458579, 1440, 7, 29),
        // Note the Jdn, this shouldn't be like this
        intArrayOf(2458581, 1440, 8, 1)
    ).iterator().forEachRemaining {
      val reference = IslamicDate(it[1], it[2], it[3])
      assertEquals(it[0].toLong(), DateConverter.islamicToJdn(reference))

      assertEquals(it[1].toLong(), DateConverter.jdnToIslamic(it[0].toLong()).year.toLong())
      assertEquals(it[2].toLong(), DateConverter.jdnToIslamic(it[0].toLong()).month.toLong())
      assertEquals(it[3].toLong(), DateConverter.jdnToIslamic(it[0].toLong()).dayOfMonth.toLong())

      assertEquals(it[0].toLong(), DateConverter.islamicToJdn(DateConverter.jdnToIslamic(it[0].toLong())))
      assertTrue(reference.equals(
          DateConverter.jdnToIslamic(DateConverter.islamicToJdn(reference))))
    }
  }

  @Test
  fun practice_persian_converting_back_and_forth() {
    val startJdn = DateConverter.civilToJdn(1950, 1, 1)
    val endJdn = DateConverter.civilToJdn(2050, 1, 1)
    for (jdn in startJdn..endJdn) {
      val result = DateConverter.civilToJdn(DateConverter.jdnToCivil(jdn))
      assertEquals(jdn, result)
    }
  }

  @Test
  fun practice_islamic_converting_back_and_forth() {
    val startJdn = DateConverter.civilToJdn(1950, 1, 1)
    val endJdn = DateConverter.civilToJdn(2050, 1, 1)
    for (jdn in startJdn..endJdn) {
      val result = DateConverter.islamicToJdn(DateConverter.jdnToIslamic(jdn))
      assertEquals(jdn, result)
    }
  }

  @Test
  fun practice_civil_converting_back_and_forth() {
    val startJdn = DateConverter.civilToJdn(1950, 1, 1)
    val endJdn = DateConverter.civilToJdn(2050, 1, 1)
    for (jdn in startJdn..endJdn) {
      val result = DateConverter.persianToJdn(DateConverter.jdnToPersian(jdn))
      assertEquals(jdn, result)
    }
  }
}